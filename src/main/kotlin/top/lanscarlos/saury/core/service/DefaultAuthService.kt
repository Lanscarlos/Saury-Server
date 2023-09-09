package top.lanscarlos.saury.core.service

import cn.dev33.satoken.stp.SaTokenInfo
import cn.dev33.satoken.stp.StpUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import top.lanscarlos.saury.service.AuthService
import top.lanscarlos.saury.service.MailService
import top.lanscarlos.saury.service.UserProfileService

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
    private lateinit var userProfileService: UserProfileService

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
        val (verifyCode, expireTime) = cache[email] ?: throw IllegalArgumentException("Invalid verify code.")
        when {
            System.currentTimeMillis() > expireTime -> {
                // Code is expired.
                cache.remove(email)
                throw IllegalArgumentException("Invalid verify code.")
            }
            verifyCode == code -> {
                cache.remove(email)
            }
            else -> {
                throw IllegalArgumentException("Invalid verify code.")
            }
        }
    }

    override fun register(email: String, password: String, username: String, code: String): SaTokenInfo {
        verifyCode(email, code)
        userProfileService.register(email, password, username)
        return login(email, password)
    }

    override fun login(email: String, password: String): SaTokenInfo {
        if (isLogin()) {
            throw IllegalStateException("User \"$email\" Already logged in.")
        }
        val profile = userProfileService.matches(email, password) ?: throw IllegalArgumentException("Incorrect email or password.")
        StpUtil.login(profile.id)
        return StpUtil.getTokenInfo()
    }

    override fun logout() {
        if (!isLogin()) {
            throw IllegalStateException("User not logged in.")
        }
        StpUtil.logout()
    }

    override fun isLogin(): Boolean {
        return StpUtil.isLogin()
    }

    override fun getTokenInfo(): SaTokenInfo {
        return StpUtil.getTokenInfo()
    }
}