package top.lanscarlos.saury.core.controller

import cn.dev33.satoken.util.SaResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.lanscarlos.saury.controller.OrderController
import top.lanscarlos.saury.repository.OrderRepository
import top.lanscarlos.saury.service.AuthService

/**
 * Saury
 * top.lanscarlos.saury.core.controller
 *
 * @author Lanscarlos
 * @since 2024-03-31 01:44
 */
@CrossOrigin
@RestController
class DefaultOrderController : OrderController {

    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @RequestMapping("/orders")
    override fun getOrders(): SaResult {
        return try {
            val userId = authService.getLoginId()
            val orders = orderRepository.findAllByUserId(userId)
            SaResult.data(orders)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/allOrders")
    override fun getAllOrders(): SaResult {
        return try {
            val orders = orderRepository.findAll()
            SaResult.data(orders)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/deleteOrder/{orderId}")
    override fun deleteOrder(@PathVariable orderId: Long): SaResult {
        return try {
            orderRepository.deleteById(orderId)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }
}