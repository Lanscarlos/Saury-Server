package top.lanscarlos.saury.core.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.lanscarlos.saury.core.entity.DefaultProfile
import top.lanscarlos.saury.core.entity.DefaultUser
import top.lanscarlos.saury.entity.User
import top.lanscarlos.saury.repository.ProfileRepository
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

    @Autowired
    private lateinit var profileRepository: ProfileRepository

    override fun exists(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    @Transactional
    override fun register(email: String, password: String) {
        if (exists(email)) {
            throw IllegalStateException("User \"$email\" already exists.")
        }
        val user = DefaultUser()
        user.email = email
        user.password = password
        user.registerTime = System.currentTimeMillis()
        userRepository.save(user)

        val profile = DefaultProfile()
        profile.id = user.id
        profileRepository.save(profile)
    }

    override fun matches(email: String, password: String): User? {
        return userRepository.findByEmailAndPassword(email, password)
    }

    override fun getById(id: Long): DefaultUser {
        return userRepository.findById(id)
            .orElseThrow { IllegalStateException("User by id \"$id\" not exists.") }
    }

    override fun getByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun getUsers(): List<User> {
        return userRepository.findAll()
    }

    @Transactional
    override fun banUser(userId: Long) {
        val user = getById(userId)
        user.isBanned = true
        userRepository.save(user)
    }

    @Transactional
    override fun unbanUser(userId: Long) {
        val user = getById(userId)
        user.isBanned = false
        userRepository.save(user)
    }

    @Transactional
    override fun promoteUser(userId: Long) {
        val user = getById(userId)
        user.isAdmin = true
        userRepository.save(user)
    }

    @Transactional
    override fun unpromoteUser(userId: Long) {
        val user = getById(userId)
        user.isAdmin = false
        userRepository.save(user)
    }

}