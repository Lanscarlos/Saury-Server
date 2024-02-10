package top.lanscarlos.saury.service

import top.lanscarlos.saury.entity.Profile

/**
 * Saury
 * top.lanscarlos.saury.service
 *
 * 用户信息服务
 *
 * @author Lanscarlos
 * @since 2023-09-08 13:30
 */
interface ProfileService {

    /**
     * 是否存在用户信息
     *
     * @param email 邮箱
     *
     * @return 是否存在
     */
    fun hasUserProfile(email: String): Boolean

    /**
     * 注册用户信息
     *
     * @param email 邮箱
     * @param password 密码 (sha256)
     * @param username 用户名
     */
    fun register(email: String, password: String, username: String)

    /**
     * 匹配用户信息
     *
     * @param email 邮箱
     * @param password 密码 (sha256)
     *
     * @return 匹配成功返回用户信息，失败返回 null
     */
    fun matches(email: String, password: String): Profile?

    /**
     * 修改密码
     *
     * @param id 用户唯一标识
     * @param oldPassword 旧密码 (sha256)
     * @param newPassword 新密码 (sha256)
     */
    fun changePassword(id: Long, oldPassword: String, newPassword: String)

    /**
     * 修改用户名
     *
     * @param id 用户唯一标识
     * @param username 用户名
     */
    fun changeUsername(id: Long, username: String)

    /**
     * 修改头像
     *
     * @param id 用户唯一标识
     * @param avatar 头像
     */
    fun changeAvatar(id: Long, avatar: String)

    /**
     * 获取用户信息
     *
     * @param id 用户唯一标识
     *
     * @return 用户信息
     */
    fun getUserProfileById(id: Long): Profile

    /**
     * 获取用户信息
     *
     * @param email 邮箱
     *
     * @return 用户信息
     */
    fun getUserProfileByEmail(email: String): Profile?

    /**
     * 获取用户信息
     *
     * @param username 用户名
     *
     * @return 用户信息
     */
    fun getUserProfileByUsername(username: String): Profile?
}