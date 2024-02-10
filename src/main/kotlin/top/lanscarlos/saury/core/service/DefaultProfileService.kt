package top.lanscarlos.saury.core.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import top.lanscarlos.saury.core.entity.DefaultProfile
import top.lanscarlos.saury.entity.Profile
import top.lanscarlos.saury.repository.ProfileRepository
import top.lanscarlos.saury.service.ProfileService
import java.lang.IllegalStateException

/**
 * Saury
 * top.lanscarlos.saury.core.service
 *
 * @author Lanscarlos
 * @since 2023-09-08 23:28
 */
@Service
class DefaultProfileService : ProfileService {

    @Autowired
    private lateinit var profileRepository: ProfileRepository

    override fun hasUserProfile(email: String): Boolean {
        return profileRepository.existsByEmail(email)
    }

    override fun register(email: String, password: String, username: String) {
        if (hasUserProfile(email)) {
            throw IllegalStateException("User \"$email\" already exists.")
        }
        val profile = DefaultProfile()
        profile.email = email
        profile.password = password
        profile.username = username
        profile.avatar = "https://avatars.githubusercontent.com/u/32390930?v=4"
        profile.registerTime = System.currentTimeMillis()
        profileRepository.save(profile)
    }

    override fun matches(email: String, password: String): Profile? {
        return profileRepository.findByEmailAndPassword(email, password)
    }

    override fun changePassword(id: Long, oldPassword: String, newPassword: String) {
        val user = getUserProfileById(id) as? DefaultProfile
            ?: throw IllegalStateException("Unsupported user profile type.")

        if (user.password != oldPassword) {
            throw IllegalStateException("Incorrect old password.")
        }

        user.password = newPassword
        profileRepository.save(user)
    }

    override fun changeUsername(id: Long, username: String) {
        val user = getUserProfileById(id) as? DefaultProfile
            ?: throw IllegalStateException("Unsupported user profile type.")

        user.username = username
        profileRepository.save(user)
    }

    override fun changeAvatar(id: Long, avatar: String) {
        val user = getUserProfileById(id) as? DefaultProfile
            ?: throw IllegalStateException("Unsupported user profile type.")

        user.avatar = avatar
        profileRepository.save(user)
    }

    override fun getUserProfileById(id: Long): Profile {
        return profileRepository.findById(id)
            .orElseThrow { IllegalStateException("User by id \"$id\" not exists.") }
    }

    override fun getUserProfileByEmail(email: String): Profile? {
        return profileRepository.findByEmail(email)
    }

    override fun getUserProfileByUsername(username: String): Profile? {
        return profileRepository.findByUsername(username)
    }

}