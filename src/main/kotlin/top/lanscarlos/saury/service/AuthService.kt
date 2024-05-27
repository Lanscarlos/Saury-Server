package top.lanscarlos.saury.service

import cn.dev33.satoken.stp.SaTokenInfo

/**
 * Saury
 * top.lanscarlos.saury.service
 *
 * 鉴权服务
 *
 * @author Lanscarlos
 * @since 2023-09-08 16:00
 */
interface AuthService {

    /**
     * 发送验证码
     *
     * @param email 邮箱
     * @param duration 有效时间 ( 单位：秒 默认 10 分钟 )
     */
    fun sendVerificationCode(email: String, duration: Long = 600)

    /**
     * 验证验证码
     *
     * @param email 邮箱
     * @param code 验证码
     *
     * @throws IllegalArgumentException 验证码错误
     */
    fun verifyCode(email: String, code: String)

    /**
     * 注册用户信息
     *
     * @param email 邮箱
     * @param password 密码 (sha256)
     * @param code 验证码
     */
    fun register(email: String, password: String, code: String): SaTokenInfo

    /**
     * 登录
     *
     * @param email 邮箱
     * @param password 密码 (sha256)
     *
     * @return token 信息
     */
    fun login(email: String, password: String, code: String): SaTokenInfo

    /**
     * 退出登录
     */
    fun logout()

    /**
     * 是否已登录
     */
    fun isLogin(): Boolean

    /**
     * 获取 token 信息
     */
    fun getLoginId(): Long
}