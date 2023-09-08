package top.lanscarlos.saury.controller

import cn.dev33.satoken.util.SaResult

/**
 * Saury
 * top.lanscarlos.saury.controller
 *
 * @author Lanscarlos
 * @since 2023-09-08 00:39
 */
interface ProfileController {

    /**
     * 注册用户信息
     *
     * @param email 邮箱
     * @param password 密码 (sha256)
     * @param username 用户名
     * @param code 验证码
     */
    fun register(email: String, password: String, username: String, code: String): SaResult

    /**
     * 获取用户信息
     * */
    fun getProfile(): SaResult



}