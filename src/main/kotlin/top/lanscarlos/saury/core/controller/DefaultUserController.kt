package top.lanscarlos.saury.core.controller

import cn.dev33.satoken.util.SaResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.lanscarlos.saury.controller.UserController
import top.lanscarlos.saury.service.UserService

/**
 * Saury
 * top.lanscarlos.saury.core.controller
 *
 * @author Lanscarlos
 * @since 2024-03-31 01:33
 */
@CrossOrigin
@RestController
class DefaultUserController : UserController {

    @Autowired
    private lateinit var userService: UserService

    @RequestMapping("/users")
    override fun getUsers(): SaResult {
        return try {
            val users = userService.getUsers()
            SaResult.data(users)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/user/{userId}")
    override fun getUser(@PathVariable userId: Long): SaResult {
        return try {
            val user = userService.getById(userId)
            SaResult.data(user)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/banUser/{userId}")
    override fun banUser(@PathVariable userId: Long): SaResult {
        return try {
            userService.banUser(userId)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/unbanUser/{userId}")
    override fun unbanUser(@PathVariable userId: Long): SaResult {
        return try {
            userService.unbanUser(userId)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/promoteUser/{userId}")
    override fun promoteUser(@PathVariable userId: Long): SaResult {
        return try {
            userService.promoteUser(userId)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/unpromoteUser/{userId}")
    override fun unpromoteUser(@PathVariable userId: Long): SaResult {
        return try {
            userService.unpromoteUser(userId)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }
}