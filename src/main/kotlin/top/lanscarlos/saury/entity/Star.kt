package top.lanscarlos.saury.entity

/**
 * Saury
 * top.lanscarlos.saury.entity
 *
 * 收藏
 *
 * @author Lanscarlos
 * @since 2024-02-22 11:14
 */
interface Star {

    /**
     * 唯一标识 (数据库自增主键)
     */
    val id: Long

    /**
     * 用户
     */
    val user: User

    /**
     * 笔记
     */
    val note: Note

    /**
     * 收藏时间
     */
    val time: Long

}