package top.lanscarlos.saury.repository

import org.springframework.data.jpa.repository.JpaRepository
import top.lanscarlos.saury.core.entity.DefaultFollow
import top.lanscarlos.saury.core.entity.DefaultUser

/**
 * Saury
 * top.lanscarlos.saury.repository
 *
 * @author Lanscarlos
 * @since 2024-02-20 13:39
 */
interface FollowRepository : JpaRepository<DefaultFollow, Long> {

    fun findByFollower(follower: DefaultUser): List<DefaultFollow>

    /**
     * 获取关注数
     * */
    fun countByFollower(user: DefaultUser): Long

    /**
     * 获取粉丝数
     * */
    fun countByFollowing(user: DefaultUser): Long

}