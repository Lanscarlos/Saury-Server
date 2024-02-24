package top.lanscarlos.saury.core.controller

import cn.dev33.satoken.util.SaResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.lanscarlos.saury.controller.ProfileController
import top.lanscarlos.saury.service.AuthService
import top.lanscarlos.saury.service.ProfileService
import top.lanscarlos.saury.service.UserService

/**
 * Saury
 * top.lanscarlos.saury.core.controller
 *
 * @author Lanscarlos
 * @since 2024-02-20 15:23
 */
@RestController
@RequestMapping("/profile")
class DefaultProfileController : ProfileController {

    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var profileService: ProfileService

    @RequestMapping("/get")
    override fun getProfile(): SaResult {
        return try {
            val id = authService.getLoginId()
            val profile = profileService.getById(id)
            SaResult.data(profile)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @PostMapping("/change/password")
    override fun changePassword(password: String, code: String): SaResult {
        return try {
            val id = authService.getLoginId()
            val user = userService.getById(id)
            authService.verifyCode(user.email, code)
            profileService.changePassword(id, password)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @PostMapping("/update")
    override fun updateProfile(
        username: String,
        signature: String,
        avatar: String,
        gender: Int,
        birthday: Long
    ) : SaResult {
        return try {
            val id = authService.getLoginId()
            profileService.updateProfile(id, username, signature, avatar, gender, birthday)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/followings")
    override fun getFollowings(): SaResult {
        return try {
            val id = authService.getLoginId()
            val followings = profileService.getFollowings(id)
            SaResult.data(followings)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/count/followings")
    override fun getFollowingsCount(): SaResult {
        return try {
            val id = authService.getLoginId()
            val count = profileService.getFollowingsCount(id)
            SaResult.data(count)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/followers")
    override fun getFollowers(): SaResult {
        return try {
            val id = authService.getLoginId()
            val followers = profileService.getFollowers(id)
            SaResult.data(followers)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/count/followers")
    override fun getFollowersCount(): SaResult {
        return try {
            val id = authService.getLoginId()
            val count = profileService.getFollowersCount(id)
            SaResult.data(count)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/following/{targetId}")
    override fun follow(@PathVariable targetId: Long): SaResult {
        return try {
            val id = authService.getLoginId()
            profileService.follow(id, targetId)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/unfollow/{targetId}")
    override fun unfollow(@PathVariable targetId: Long): SaResult {
        return try {
            val id = authService.getLoginId()
            profileService.unfollow(id, targetId)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

}