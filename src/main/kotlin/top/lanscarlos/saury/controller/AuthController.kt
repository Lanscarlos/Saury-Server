package top.lanscarlos.saury.controller

import cn.dev33.satoken.util.SaResult

/**
 * Saury
 * top.lanscarlos.saury.controller
 *
 * @author Lanscarlos
 * @since 2023-09-08 21:30
 */
interface AuthController {

    /**
     * 请求验证码
     *
     * @param email 邮箱
     * */
    fun requestVerificationCode(email: String): SaResult

    /**
     * 注册
     *
     * @param email 邮箱
     * @param password 密码
     * @param code 验证码
     * */
    fun register(email: String, password: String, code: String): SaResult

    /**
     * 登录
     *
     * @param email 邮箱
     * @param password 密码
     * */
    fun login(email: String, password: String): SaResult

    /**
     * 登出
     * */
    fun logout(): SaResult

    /**
     * 是否已登录
     * */
    fun isLogin(): SaResult

    /**
     * 获取 token 信息
     * */
    fun token(): SaResult

}