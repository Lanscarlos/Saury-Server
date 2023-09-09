package top.lanscarlos.saury.core.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import top.lanscarlos.saury.core.entity.DefaultUserProfile
import top.lanscarlos.saury.entity.UserProfile
import top.lanscarlos.saury.repository.UserProfileRepository
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
    private lateinit var userProfileRepository: UserProfileRepository

    override fun hasUserProfile(email: String): Boolean {
        return userProfileRepository.existsByEmail(email)
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
        userProfileRepository.save(profile)
    }

    override fun matches(email: String, password: String): UserProfile? {
        return userProfileRepository.findByEmailAndPassword(email, password)
    }

    override fun changePassword(id: Long, oldPassword: String, newPassword: String) {
        val user = getUserProfileById(id) as? DefaultUserProfile
            ?: throw IllegalStateException("Unsupported user profile type.")

        if (user.password != oldPassword) {
            throw IllegalStateException("Incorrect old password.")
        }

        user.password = newPassword
        userProfileRepository.save(user)
    }

    override fun changeUsername(id: Long, username: String) {
        val user = getUserProfileById(id) as? DefaultUserProfile
            ?: throw IllegalStateException("Unsupported user profile type.")

        user.username = username
        userProfileRepository.save(user)
    }

    override fun changeAvatar(id: Long, avatar: String) {
        val user = getUserProfileById(id) as? DefaultUserProfile
            ?: throw IllegalStateException("Unsupported user profile type.")

        user.avatar = avatar
        userProfileRepository.save(user)
    }

    override fun getUserProfileById(id: Long): UserProfile {
        return userProfileRepository.findById(id)
            .orElseThrow { IllegalStateException("User by id \"$id\" not exists.") }
    }

    override fun getUserProfileByEmail(email: String): UserProfile? {
        return userProfileRepository.findByEmail(email)
    }

    override fun getUserProfileByUsername(username: String): UserProfile? {
        return userProfileRepository.findByUsername(username)
    }

}