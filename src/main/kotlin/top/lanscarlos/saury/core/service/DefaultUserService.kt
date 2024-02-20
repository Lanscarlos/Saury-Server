package top.lanscarlos.saury.core.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import top.lanscarlos.saury.core.entity.DefaultUser
import top.lanscarlos.saury.entity.User
import top.lanscarlos.saury.repository.UserRepository
import top.lanscarlos.saury.service.UserService
import java.lang.IllegalStateException

/**
 * Saury
 * top.lanscarlos.saury.core.service
 *
 * @author Lanscarlos
 * @since 2023-09-08 23:28
 */
@Service
class DefaultUserService : UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun exists(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    override fun register(email: String, password: String, username: String) {
        if (exists(email)) {
            throw IllegalStateException("User \"$email\" already exists.")
        }
        val profile = DefaultUser()
        profile.email = email
        profile.password = password
        profile.registerTime = System.currentTimeMillis()
        userRepository.save(profile)
    }

    override fun matches(email: String, password: String): User? {
        return userRepository.findByEmailAndPassword(email, password)
    }

    override fun changePassword(id: Long, oldPassword: String, newPassword: String) {
        val user = getById(id) as? DefaultUser
            ?: throw IllegalStateException("Unsupported user profile type.")

        if (user.password != oldPassword) {
            throw IllegalStateException("Incorrect old password.")
        }

        user.password = newPassword
        userRepository.save(user)
    }

    override fun getById(id: Long): User {
        return userRepository.findById(id)
            .orElseThrow { IllegalStateException("User by id \"$id\" not exists.") }
    }

    override fun getByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

}