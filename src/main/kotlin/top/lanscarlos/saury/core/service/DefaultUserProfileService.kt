package top.lanscarlos.saury.core.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import top.lanscarlos.saury.core.entity.DefaultUserProfile
import top.lanscarlos.saury.entity.UserProfile
import top.lanscarlos.saury.repository.UserRepository
import top.lanscarlos.saury.service.UserProfileService
import java.lang.IllegalStateException

/**
 * Saury
 * top.lanscarlos.saury.core.service
 *
 * @author Lanscarlos
 * @since 2023-09-08 23:28
 */
@Service
class DefaultUserProfileService : UserProfileService {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun hasUserProfile(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    override fun register(email: String, password: String, username: String) {
        if (hasUserProfile(email)) {
            throw IllegalStateException("User \"$email\" already exists.")
        }
        val profile = DefaultUserProfile()
        profile.email = email
        profile.password = password
        profile.username = username
        profile.avatar = "https://avatars.githubusercontent.com/u/32390930?v=4"
        profile.registerTime = System.currentTimeMillis()
        userRepository.save(profile)
    }

    override fun matches(email: String, password: String): UserProfile? {
        return userRepository.findByEmailAndPassword(email, password)
    }

    override fun changePassword(email: String, oldPassword: String, newPassword: String) {
        TODO("Not yet implemented")
    }

    override fun changeUsername(email: String, username: String) {
        TODO("Not yet implemented")
    }

    override fun changeAvatar(email: String, avatar: String) {
        TODO("Not yet implemented")
    }

    override fun getUserProfileById(id: Long): UserProfile? {
        return userRepository.findById(id).orElse(null)
    }

    override fun getUserProfileByEmail(email: String): UserProfile? {
        return userRepository.findByEmail(email)
    }

    override fun getUserProfileByUsername(username: String): UserProfile? {
        return userRepository.findByUsername(username)
    }

}