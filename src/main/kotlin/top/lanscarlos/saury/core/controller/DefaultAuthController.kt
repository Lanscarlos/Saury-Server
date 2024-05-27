package top.lanscarlos.saury.core.controller

import cn.dev33.satoken.util.SaResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.lanscarlos.saury.controller.AuthController
import top.lanscarlos.saury.service.AuthService

/**
 * Saury
 * top.lanscarlos.saury.core.controller
 *
 * @author Lanscarlos
 * @since 2023-09-08 21:39
 */
@CrossOrigin
@RestController
@RequestMapping("/auth")
class DefaultAuthController : AuthController {

    @Autowired
    lateinit var authService: AuthService

    @RequestMapping("/requestVerificationCode")
    override fun requestVerificationCode(email: String): SaResult {
        return try {
            authService.sendVerificationCode(email)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.localizedMessage)
        }
    }

    @RequestMapping("/register")
    override fun register(email: String, password: String, code: String): SaResult {
        return try {
            SaResult.data(authService.register(email, password, code))
        } catch (ex: Exception) {
            SaResult.error(ex.localizedMessage)
        }
    }

    @RequestMapping("/login")
    override fun login(email: String, password: String, code: String): SaResult {
        return try {
            SaResult.data(authService.login(email, password, code))
        } catch (ex: Exception) {
            SaResult.error(ex.localizedMessage)
        }
    }

    @RequestMapping("/logout")
    override fun logout(): SaResult {
        return try {
            authService.logout()
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.localizedMessage)
        }
    }

    @RequestMapping("/isLogin")
    override fun isLogin(): SaResult {
        return SaResult.data(authService.isLogin())
    }

    @RequestMapping("/token")
    override fun token(): SaResult {
        return try {
            SaResult.data(authService.getLoginId())
        } catch (ex: Exception) {
            SaResult.error(ex.localizedMessage)
        }
    }
}