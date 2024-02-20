package top.lanscarlos.saury.controller

import cn.dev33.satoken.util.SaResult

/**
 * Saury
 * top.lanscarlos.saury.controller
 *
 * @author Lanscarlos
 * @since 2023-09-08 00:39
 */
interface UserController {

    /**
     * 获取用户信息
     * */
    fun getProfile(): SaResult

    /**
     * 修改密码
     * */
    fun changePassword(oldPassword: String, newPassword: String, code: String): SaResult

}