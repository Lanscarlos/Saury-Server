package top.lanscarlos.saury.entity

/**
 * Saury
 * top.lanscarlos.saury.entity
 *
 * 用户关注
 *
 * @author Lanscarlos
 * @since 2024-02-20 13:33
 */
interface Follow {

    /**
     * 唯一标识 (数据库自增主键)
     */
    val id: Long

    /**
     * 关注者
     */
    val follower: User

    /**
     * 被关注者
     */
    val following: User

}