package top.lanscarlos.saury.controller

import cn.dev33.satoken.util.SaResult

/**
 * Saury
 * top.lanscarlos.saury.controller
 *
 * @author Lanscarlos
 * @since 2024-03-31 01:27
 */
interface UserController {

    fun getUsers(): SaResult

    fun getUser(userId: Long): SaResult

    fun banUser(userId: Long): SaResult

    fun unbanUser(userId: Long): SaResult

    fun promoteUser(userId: Long): SaResult

    fun unpromoteUser(userId: Long): SaResult

}