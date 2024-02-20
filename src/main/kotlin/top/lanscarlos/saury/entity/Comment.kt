package top.lanscarlos.saury.entity

/**
 * Saury
 * top.lanscarlos.saury.entity
 *
 * 笔记评论
 *
 * @author Lanscarlos
 * @since 2024-02-20 13:31
 */
interface Comment {

    /**
     * 唯一标识 (数据库自增主键)
     */
    val id: Long

    /**
     * 评论者
     */
    val user: User

    /**
     * 评论的笔记
     */
    val note: Note

    /**
     * 评论时间
     * */
    val time: Long

    /**
     * 评论内容
     * */
    val content: String

    /**
     * 父评论
     * */
    val parent: Comment?

}