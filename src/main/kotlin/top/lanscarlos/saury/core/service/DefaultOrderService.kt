package top.lanscarlos.saury.core.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import top.lanscarlos.saury.entity.Order
import top.lanscarlos.saury.repository.OrderRepository
import top.lanscarlos.saury.service.OrderService

/**
 * Saury
 * top.lanscarlos.saury.core.service
 *
 * @author Lanscarlos
 * @since 2024-03-31 01:43
 */
@Service
class DefaultOrderService : OrderService {

    @Autowired
    private lateinit var orderRepository: OrderRepository

    override fun getOrders(): List<Order> {
        return orderRepository.findAll()
    }

    override fun getOrdersByUserId(userId: Long): List<Order> {
        return orderRepository.findAllByUserId(userId)
    }
}