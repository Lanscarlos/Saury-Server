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

    /**
     * 笔记类型
     */
    val type: String

    /**
     * 笔记价格
     * */
    val price: Double

    /**
     * 笔记状态
     * 0: 审核状态
     * 1: 审核通过
     * 2: 审核不通过，仅作者可见
     * */
    val state: Int

}