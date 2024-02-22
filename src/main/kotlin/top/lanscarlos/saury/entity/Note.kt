package top.lanscarlos.saury.entity

/**
 * Saury
 * top.lanscarlos.saury.entity
 *
 * 笔记
 *
 * @author Lanscarlos
 * @since 2024-02-11 00:11
 */
interface Note {

    /**
     * 唯一标识 (数据库自增主键)
     */
    val id: Long

    /**
     * 笔记作者
     */
    val user: User

    /**
     * 笔记标题
     */
    val title: String

    /**
     * 笔记描述
     */
    val description: String

    /**
     * 创建时间
     */
    val createTime: Long

    /**
     * 更新时间
     */
    val updateTime: Long

}