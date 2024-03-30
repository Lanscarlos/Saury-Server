package top.lanscarlos.saury.core.service

import com.alibaba.fastjson.JSONObject
import com.alipay.api.DefaultAlipayClient
import com.alipay.api.request.AlipayTradePagePayRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import top.lanscarlos.saury.core.entity.DefaultOrder
import top.lanscarlos.saury.core.entity.DefaultUser
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

    override fun pay(userId: Long, subject: String, amount: Double): String {
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

        // 调用支付宝接口
        val client = DefaultAlipayClient(gatewayUrl, appId, privateKey, "json", charset, publicKey, signType)

        val request = AlipayTradePagePayRequest()
        request.notifyUrl = notifyUrl
        request.returnUrl = returnUrl
        val content = JSONObject()
        content["out_trade_no"] = order.orderNo
        content["product_code"] = "FAST_INSTANT_TRADE_PAY"
        content["total_amount"] = amount.toString()
        content["subject"] = subject

        request.bizContent = content.toString()

        return client.pageExecute(request).body
    }

}