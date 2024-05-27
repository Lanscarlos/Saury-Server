package top.lanscarlos.saury.controller

import cn.dev33.satoken.util.SaResult

/**
 * Saury
 * top.lanscarlos.saury.controller
 *
 * @author Lanscarlos
 * @since 2024-03-31 01:41
 */
interface OrderController {

    fun getOrders(): SaResult

    fun getAllOrders(): SaResult

    fun deleteOrder(orderId: Long): SaResult

}