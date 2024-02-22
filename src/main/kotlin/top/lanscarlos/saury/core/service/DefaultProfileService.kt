package top.lanscarlos.saury.core.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.lanscarlos.saury.core.entity.DefaultFollow
import top.lanscarlos.saury.core.entity.DefaultUser
import top.lanscarlos.saury.entity.Profile
import top.lanscarlos.saury.entity.User
import top.lanscarlos.saury.repository.FollowRepository
import top.lanscarlos.saury.repository.ProfileRepository
import top.lanscarlos.saury.repository.UserRepository
import top.lanscarlos.saury.service.ProfileService
import java.lang.IllegalStateException

/**
 * Saury
 * top.lanscarlos.saury.core.service
 *
 * @author Lanscarlos
 * @since 2024-02-20 15:24
 */
@Service
class DefaultProfileService : ProfileService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var profileRepository: ProfileRepository

    @Autowired
    private lateinit var followRepository: FollowRepository

    override fun getById(id: Long): Profile {
        return profileRepository.findById(id).orElseThrow { IllegalStateException("User by id \"$id\" not exists.") }
    }

    override fun changePassword(id: Long, oldPassword: String, newPassword: String) {
        val user = getUserById(id)

        if (user.password != oldPassword) {
            throw IllegalStateException("Incorrect old password.")
        }

        user.password = newPassword
        userRepository.save(user)
    }

    @Transactional
    override fun updateProfile(
        id: Long,
        username: String,
        signature: String,
        avatar: String,
        gender: Int,
        birthday: Long
    ) {
        val profile = profileRepository.findById(id).orElseThrow { IllegalStateException("User by id \"$id\" not exists.") }

        profile.username = username
        profile.signature = signature
        profile.avatar = avatar
        profile.gender = gender
        profile.birthday = birthday

        profileRepository.save(profile)
    }

    override fun getFollowings(id: Long): List<User> {
        val user = getUserById(id)
        return followRepository.findAllByFollower(user).map { it.following }
    }

    override fun getFollowingsCount(id: Long): Long {
        val user = getUserById(id)
        return followRepository.countByFollower(user)
    }

    override fun getFollowers(id: Long): List<User> {
        val user = getUserById(id)
        return followRepository.findAllByFollower(user).map { it.follower }
    }

    override fun getFollowersCount(id: Long): Long {
        val user = getUserById(id)
        return followRepository.countByFollowing(user)
    }

    override fun follow(id: Long, targetId: Long) {
        val user = getUserById(id)
        val target = getUserById(targetId)
        val follow = DefaultFollow()
        follow.follower = user
        follow.following = target
        followRepository.save(follow)
    }

    override fun unfollow(id: Long, targetId: Long) {
        val user = getUserById(id)
        val target = getUserById(targetId)
        followRepository.deleteByFollowerAndFollowing(user, target)
    }

    fun getUserById(id: Long): DefaultUser {
        return userRepository.findById(id).orElseThrow { IllegalStateException("User by id \"$id\" not exists.") }
    }

}