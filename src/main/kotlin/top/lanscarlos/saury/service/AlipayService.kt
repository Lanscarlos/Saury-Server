package top.lanscarlos.saury.service

import top.lanscarlos.saury.entity.Order

/**
 * Saury
 * top.lanscarlos.saury.service
 *
 * @author Lanscarlos
 * @since 2024-03-03 12:35
 */
interface AlipayService {

    fun getOrder(orderId: Long): Order

    fun approveOrder(orderId: Long): Order

    fun submit(userId: Long, subject: String, amount: Double)

    fun pay(orderId: Long): String

}