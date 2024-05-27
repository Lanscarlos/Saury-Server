package top.lanscarlos.saury.core.service

import com.alibaba.fastjson.JSONObject
import com.alipay.api.DefaultAlipayClient
import com.alipay.api.request.AlipayTradePagePayRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.lanscarlos.saury.core.entity.DefaultOrder
import top.lanscarlos.saury.core.entity.DefaultUser
import top.lanscarlos.saury.entity.Order
import top.lanscarlos.saury.repository.OrderRepository
import top.lanscarlos.saury.service.AlipayService
import top.lanscarlos.saury.service.UserService

/**
 * Saury
 * top.lanscarlos.saury.core.service
 *
 * @author Lanscarlos
 * @since 2024-03-03 12:35
 */
@Service
class DefaultAlipayService : AlipayService {

    @Value("\${alipay.app-id}")
    private lateinit var appId: String

    @Value("\${alipay.public-key}")
    private lateinit var publicKey: String

    @Value("\${alipay.private-key}")
    private lateinit var privateKey: String

    @Value("\${alipay.gateway-url}")
    private lateinit var gatewayUrl: String

    @Value("\${alipay.charset}")
    private lateinit var charset: String

    @Value("\${alipay.sign-type}")
    private lateinit var signType: String

    @Value("\${alipay.notify-url}")
    private lateinit var notifyUrl: String

    @Value("\${alipay.return-url}")
    private lateinit var returnUrl: String

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var orderRepository: OrderRepository

    override fun getOrder(orderId: Long): DefaultOrder {
        return orderRepository.findById(orderId).orElseThrow { IllegalStateException("Order by id \"$orderId\" not exists.") }
    }

    @Transactional
    override fun approveOrder(orderId: Long): Order {
        val order = getOrder(orderId)
        order.status = true
        orderRepository.save(order)
        return order
    }

    override fun submit(userId: Long, subject: String, amount: Double) {
        if (amount <= 0.0) {
            error("The amount must be greater than 0.")
        }

        // 生成订单
        val order = DefaultOrder()
        order.subject = subject
        order.amount = amount
        order.user = userService.getById(userId) as DefaultUser

        // 持久化
        orderRepository.save(order)
    }

    override fun pay(orderId: Long): String {
        // 获取订单信息
        val order = getOrder(orderId)

        // 调用支付宝接口
        val client = DefaultAlipayClient(gatewayUrl, appId, privateKey, "json", charset, publicKey, signType)

        val request = AlipayTradePagePayRequest()
        request.notifyUrl = notifyUrl
        request.returnUrl = returnUrl
        val content = JSONObject()
        content["out_trade_no"] = order.id
        content["product_code"] = "FAST_INSTANT_TRADE_PAY"
        content["total_amount"] = order.amount.toString()
        content["subject"] = order.subject

        request.bizContent = content.toString()

        return client.pageExecute(request).body
    }

}