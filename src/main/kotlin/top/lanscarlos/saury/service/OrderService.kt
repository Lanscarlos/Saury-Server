package top.lanscarlos.saury.service

import top.lanscarlos.saury.entity.Order

/**
 * Saury
 * top.lanscarlos.saury.service
 *
 * @author Lanscarlos
 * @since 2024-03-31 01:42
 */
interface OrderService {

    fun getOrders(): List<Order>

    fun getOrdersByUserId(userId: Long): List<Order>

}