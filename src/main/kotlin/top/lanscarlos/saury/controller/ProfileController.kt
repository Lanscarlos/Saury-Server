package top.lanscarlos.saury.controller

import cn.dev33.satoken.util.SaResult

/**
 * Saury
 * top.lanscarlos.saury.controller
 *
 * @author Lanscarlos
 * @since 2024-02-20 15:18
 */
interface ProfileController {

    /**
     * 获取用户资料
     */
    fun getProfile(): SaResult

    /**
     * 修改密码
     *
     * @param password 旧密码
     * @param code 验证码
     * */
    fun changePassword(password: String, code: String): SaResult

    /**
     * 修改资料
     *
     * @param username 用户名
     * @param signature 个性签名
     * @param avatar 头像
     * @param gender 性别
     * @param birthday 生日
     */
    fun updateProfile(username: String, signature: String, avatar: String, gender: Int, birthday: Long): SaResult

    /**
     * 获取关注列表
     */
    fun getFollowings(): SaResult

    /**
     * 获取关注数
     */
    fun getFollowingsCount(): SaResult

    /**
     * 获取粉丝列表
     */
    fun getFollowers(): SaResult

    /**
     * 获取粉丝数
     */
    fun getFollowersCount(): SaResult

    /**
     * 关注
     *
     * @param targetId 目标用户id
     */
    fun follow(targetId: Long): SaResult

    /**
     * 取消关注
     *
     * @param targetId 目标用户id
     */
    fun unfollow(targetId: Long): SaResult

}