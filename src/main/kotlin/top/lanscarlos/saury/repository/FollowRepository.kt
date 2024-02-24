package top.lanscarlos.saury.repository

import org.springframework.data.jpa.repository.JpaRepository
import top.lanscarlos.saury.core.entity.DefaultFollow

/**
 * Saury
 * top.lanscarlos.saury.repository
 *
 * @author Lanscarlos
 * @since 2024-02-20 13:39
 */
interface FollowRepository : JpaRepository<DefaultFollow, Long> {

    /**
     * 获取关注列表
     * */
    fun findAllByFollowerId(followerId: Long): List<DefaultFollow>

    /**
     * 获取粉丝列表
     * */
    fun findAllByFollowingId(followingId: Long): List<DefaultFollow>

    /**
     * 获取关注数
     * */
    fun countByFollowerId(userId: Long): Long

    /**
     * 获取粉丝数
     * */
    fun countByFollowingId(userId: Long): Long

    /**
     * 取消关注
     * */
    fun deleteByFollowerIdAndFollowingId(followerId: Long, followingId: Long)

}