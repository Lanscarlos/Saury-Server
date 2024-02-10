package top.lanscarlos.saury.core.controller

import cn.dev33.satoken.util.SaResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.lanscarlos.saury.controller.ProfileController
import top.lanscarlos.saury.service.AuthService
import top.lanscarlos.saury.service.ProfileService

/**
 * Saury
 * top.lanscarlos.saury.core.controller
 *
 * @author Lanscarlos
 * @since 2023-09-08 00:38
 */
@RestController
@RequestMapping("/user/profile")
class DefaultProfileController : ProfileController {

    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var profileService: ProfileService

    @RequestMapping("/get")
    override fun getProfile(): SaResult {
        return try {
            val id = authService.getTokenInfo().getLoginId() as Long
            val user = profileService.getUserProfileById(id)
            SaResult.data(user)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @PostMapping("/change/password")
    override fun changePassword(oldPassword: String, newPassword: String, code: String): SaResult {
        return try {
            val id = authService.getTokenInfo().getLoginId() as Long
            val user = profileService.getUserProfileById(id)
            authService.verifyCode(user.email, code)
            profileService.changePassword(id, oldPassword, newPassword)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/change/username")
    override fun changeUsername(username: String): SaResult {
        return try {
            val id = authService.getTokenInfo().getLoginId() as Long
            profileService.changeUsername(id, username)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/change/avatar")
    override fun changeAvatar(avatar: String): SaResult {
        return try {
            val id = authService.getTokenInfo().getLoginId() as Long
            profileService.changeAvatar(id, avatar)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

}