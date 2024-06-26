package top.lanscarlos.saury.core.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.lanscarlos.saury.core.entity.DefaultFollow
import top.lanscarlos.saury.core.entity.DefaultUser
import top.lanscarlos.saury.entity.Note
import top.lanscarlos.saury.entity.Profile
import top.lanscarlos.saury.entity.User
import top.lanscarlos.saury.repository.FollowRepository
import top.lanscarlos.saury.repository.ProfileRepository
import top.lanscarlos.saury.repository.StarRepository
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

    @Autowired
    private lateinit var starRepository: StarRepository

    override fun getById(userId: Long): Profile {
        return profileRepository.findById(userId).orElseThrow { IllegalStateException("User by id \"$userId\" not exists.") }
    }

    override fun changePassword(userId: Long, password: String) {
        val user = getUserById(userId)
        user.password = password
        userRepository.save(user)
    }

    @Transactional
    override fun updateProfile(
        userId: Long,
        username: String,
        signature: String,
        avatar: String,
        gender: Int,
        birthday: Long
    ): Profile {
        val profile = profileRepository.findById(userId).orElseThrow { IllegalStateException("User by id \"$userId\" not exists.") }

        profile.username = username
        profile.signature = signature
        profile.avatar = avatar
        profile.gender = gender
        profile.birthday = birthday

        profileRepository.save(profile)

        return profile
    }

    override fun getFollowings(userId: Long): List<User> {
        return followRepository.findAllByFollowerId(userId).map { it.following }
    }

    override fun getFollowingsCount(userId: Long): Long {
        return followRepository.countByFollowerId(userId)
    }

    override fun getFollowers(userId: Long): List<User> {
        return followRepository.findAllByFollowingId(userId).map { it.follower }
    }

    override fun getFollowersCount(userId: Long): Long {
        return followRepository.countByFollowingId(userId)
    }

    override fun isFollowed(userId: Long, targetId: Long): Boolean {
        return followRepository.existsByFollowerIdAndFollowingId(userId, targetId)
    }


    override fun follow(userId: Long, targetId: Long) {

        if (isFollowed(userId, targetId)) {
            throw IllegalStateException("User \"$userId\" has followed user \"$targetId\".")
        }

        val user = getUserById(userId)
        val target = getUserById(targetId)
        val follow = DefaultFollow()
        follow.follower = user
        follow.following = target
        followRepository.save(follow)
    }

    @Transactional
    override fun unfollow(userId: Long, targetId: Long) {
        followRepository.deleteByFollowerIdAndFollowingId(userId, targetId)
    }

    override fun getStars(userId: Long): List<Note> {
        return starRepository.findAllByUserId(userId).map { it.note }
    }

    fun getUserById(id: Long): DefaultUser {
        return userRepository.findById(id).orElseThrow { IllegalStateException("User by id \"$id\" not exists.") }
    }

}