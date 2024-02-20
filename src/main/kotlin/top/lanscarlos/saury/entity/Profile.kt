package top.lanscarlos.saury.entity

/**
 * Saury
 * top.lanscarlos.saury.entity
 *
 * 用户资料
 * 可以修改
 *
 * @author Lanscarlos
 * @since 2024-02-10 22:42
 */
interface Profile {

    /**
     * 唯一标识 必须与 User.id 一致
     */
    val id: Long

    /**
     * 用户名
     */
    val username: String

    /**
     * 个性签名
     */
    val signature: String

    /**
     * 头像 (url / bitmap base64)
     */
    val avatar: String

    /**
     * 性别
     * -1 未知
     * 0 女
     * 1 男
     */
    val gender: Int

    /**
     * 生日
     */
    val birthday: Long

}