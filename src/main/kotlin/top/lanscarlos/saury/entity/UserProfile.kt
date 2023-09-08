package top.lanscarlos.saury.entity

/**
 * Saury
 * top.lanscarlos.saury.entity
 *
 * 用户信息
 *
 * @author Lanscarlos
 * @since 2023-09-08 13:48
 */
interface UserProfile {

    /**
     * 唯一标识 (数据库自增主键)
     */
    val id: Long

    /**
     * 邮箱
     */
    val email: String

    /**
     * 密码 (sha256)
     */
    val password: String

    /**
     * 用户名
     */
    val username: String

    /**
     * 头像 (url / bitmap base64)
     */
    val avatar: String

    /**
     * 注册时间
     */
    val registerTime: Long

    /**
     * 是否被封禁
     */
    val isBanned: Boolean

}