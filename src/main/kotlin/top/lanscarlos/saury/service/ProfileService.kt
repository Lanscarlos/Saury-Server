package top.lanscarlos.saury.service

import top.lanscarlos.saury.entity.Profile
import top.lanscarlos.saury.entity.User

/**
 * Saury
 * top.lanscarlos.saury.service
 *
 * @author Lanscarlos
 * @since 2024-02-20 14:54
 */
interface ProfileService {

    /**
     * 获取用户资料
     */
    fun getById(userId: Long): Profile

    /**
     * 修改密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    fun changePassword(userId: Long, oldPassword: String, newPassword: String)

    /**
     * 修改资料
     * @param username 用户名
     * @param signature 个性签名
     * @param avatar 头像
     * @param gender 性别
     * @param birthday 生日
     */
    fun updateProfile(userId: Long, username: String, signature: String, avatar: String, gender: Int, birthday: Long)

    /**
     * 获取关注列表
     */
    fun getFollowings(userId: Long): List<User>

    /**
     * 获取关注数
     */
    fun getFollowingsCount(userId: Long): Long

    /**
     * 获取粉丝列表
     */
    fun getFollowers(userId: Long): List<User>

    /**
     * 获取粉丝数
     */
    fun getFollowersCount(userId: Long): Long

    /**
     * 关注
     * @param targetId 目标用户 id
     */
    fun follow(userId: Long, targetId: Long)

    /**
     * 取消关注
     * @param targetId 目标用户 id
     */
    fun unfollow(userId: Long, targetId: Long)

}