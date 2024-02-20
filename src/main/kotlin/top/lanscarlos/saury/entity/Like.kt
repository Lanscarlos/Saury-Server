package top.lanscarlos.saury.entity

import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

/**
 * Saury
 * top.lanscarlos.saury.entity
 *
 * 用户点赞
 *
 * @author Lanscarlos
 * @since 2024-02-20 12:36
 */
interface Like {

    /**
     * 唯一标识 (数据库自增主键)
     */
    val id: Long

    /**
     * 点赞用户
     */
    val user: User

    /**
     * 点赞的笔记
     */
    val note: Note

    /**
     * 点赞时间
     * */
    val time: Long

}