package top.lanscarlos.saury.entity

/**
 * Saury
 * top.lanscarlos.saury.entity
 *
 * 收藏夹
 *
 * @author Lanscarlos
 * @since 2024-02-20 13:05
 */
interface Collection {

    /**
     * 唯一标识 (数据库自增主键)
     */
    val id: Long

    /**
     * 用户
     */
    val user: User

    /**
     * 收藏夹名称
     */
    val name: String

    /**
     * 收藏夹描述
     */
    val description: String

    /**
     * 创建时间
     */
    val time: Long

    /**
     * 收藏的笔记
     */
    val content: Set<Note>

}