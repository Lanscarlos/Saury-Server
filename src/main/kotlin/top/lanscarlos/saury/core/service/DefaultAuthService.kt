package top.lanscarlos.saury.core.service

import cn.dev33.satoken.stp.SaTokenInfo
import cn.dev33.satoken.stp.StpUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import top.lanscarlos.saury.service.AuthService
import top.lanscarlos.saury.service.MailService
import top.lanscarlos.saury.service.UserService

/**
 * Saury
 * top.lanscarlos.saury.core.service
 *
 * @author Lanscarlos
 * @since 2023-09-08 16:09
 */
@Service
class DefaultAuthService : AuthService {

    @Autowired
    private lateinit var mailService: MailService

    @Autowired
    private lateinit var userService: UserService

    /**
     * email -> (verifyCode, expireTime)
     * */
    private val cache = mutableMapOf<String, Pair<String, Long>>()

    override fun sendVerificationCode(email: String, duration: Long) {
        val expireTime = System.currentTimeMillis() + duration * 1000
        val verifyCode = (Math.random() * 1000000).toInt().toString().padStart(6, '0')
        cache[email] = verifyCode to expireTime
        mailService.sendHtmlMail(email, "Saury Verify Code", "mail-verify-code", "verifyCode" to verifyCode)
    }

    override fun verifyCode(email: String, code: String) {
        val (verifyCode, expireTime) = cache[email] ?: throw IllegalArgumentException("验证码失效")
        when {
            System.currentTimeMillis() > expireTime -> {
                // Code is expired.
                cache.remove(email)
                throw IllegalArgumentException("验证码失效")
            }
            verifyCode == code -> {
                cache.remove(email)
            }
            else -> {
                throw IllegalArgumentException("验证码错误")
            }
        }
    }

    override fun register(email: String, password: String, code: String): SaTokenInfo {
        verifyCode(email, code)
        userService.register(email, password)
        return login(email, password, code)
    }

    override fun login(email: String, password: String, code: String): SaTokenInfo {
        if (isLogin()) {
            throw IllegalStateException("用户已登录")
        }
        verifyCode(email, code)
        if (userService.exists(email)) {
            val user = userService.matches(email, password) ?: throw IllegalArgumentException("邮箱或密码不正确")
            if (user.isBanned) {
                throw IllegalStateException("用户已被封禁")
            }
            StpUtil.login(user.id)
            return StpUtil.getTokenInfo()
        }

        // 注册
        return register(email, password, code)
    }

    override fun logout() {
        if (!isLogin()) {
            throw IllegalStateException("用户未登录")
        }
        StpUtil.logout()
    }

    override fun isLogin(): Boolean {
        return StpUtil.isLogin()
    }

    override fun getLoginId(): Long {
        return StpUtil.getTokenInfo().loginId.toString().toLong()
    }
}