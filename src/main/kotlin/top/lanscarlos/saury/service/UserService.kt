package top.lanscarlos.saury.service

import top.lanscarlos.saury.entity.User

/**
 * Saury
 * top.lanscarlos.saury.service
 *
 * 用户信息服务
 *
 * @author Lanscarlos
 * @since 2023-09-08 13:30
 */
interface UserService {

    /**
     * 是否存在用户
     *
     * @param email 邮箱
     *
     * @return 是否存在
     */
    fun exists(email: String): Boolean

    /**
     * 注册用户
     *
     * @param email 邮箱
     * @param password 密码 (sha256)
     */
    fun register(email: String, password: String)

    /**
     * 匹配用户
     *
     * @param email 邮箱
     * @param password 密码 (sha256)
     *
     * @return 匹配成功返回用户信息，失败返回 null
     */
    fun matches(email: String, password: String): User?

    /**
     * 获取用户信息
     *
     * @param id 用户唯一标识
     *
     * @return 用户信息
     */
    fun getById(id: Long): User

    /**
     * 获取用户信息
     *
     * @param email 邮箱
     *
     * @return 用户信息
     */
    fun getByEmail(email: String): User?

}