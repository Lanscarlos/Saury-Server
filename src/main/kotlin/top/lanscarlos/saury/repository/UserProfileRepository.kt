package top.lanscarlos.saury.repository

import org.springframework.data.jpa.repository.JpaRepository
import top.lanscarlos.saury.core.entity.DefaultUserProfile

/**
 * Saury
 * top.lanscarlos.saury.repository
 *
 * 用户仓库
 * https://juejin.cn/post/7057323492856823816
 * https://blog.csdn.net/cmx1060220219/article/details/106259423
 *
 * @author Lanscarlos
 * @since 2023-09-08 13:55
 */
interface UserProfileRepository : JpaRepository<DefaultUserProfile, Long> {

    /**
     * 根据邮箱判断用户是否存在
     *
     * @param email 邮箱
     *
     * @return 是否存在
     */
    fun existsByEmail(email: String): Boolean

    /**
     * 根据邮箱获取用户信息
     *
     * @param email 邮箱
     *
     * @return 用户信息
     */
    fun findByEmail(email: String): DefaultUserProfile?

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     *
     * @return 用户信息
     */
    fun findByUsername(username: String): DefaultUserProfile?

    /**
     * 根据邮箱和密码获取用户信息
     *
     * @param email 邮箱
     * @param password 密码
     *
     * @return 用户信息
     */
    fun findByEmailAndPassword(email: String, password: String): DefaultUserProfile?

}