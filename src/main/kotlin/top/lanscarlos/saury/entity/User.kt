package top.lanscarlos.saury.entity

/**
 * Saury
 * top.lanscarlos.saury.entity
 *
 * 用户信息
 * 注册之后不可更改
 *
 * @author Lanscarlos
 * @since 2023-09-08 13:48
 */
interface User {

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
     * 注册时间
     */
    val registerTime: Long

    /**
     * 是否为管理员
     * */
    val isAdmin: Boolean

    /**
     * 是否被封禁
     */
    val isBanned: Boolean

}