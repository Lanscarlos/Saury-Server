package top.lanscarlos.saury.repository

import org.springframework.data.jpa.repository.JpaRepository
import top.lanscarlos.saury.core.entity.DefaultOrder

/**
 * Saury
 * top.lanscarlos.saury.repository
 *
 * @author Lanscarlos
 * @since 2024-03-06 17:49
 */
interface OrderRepository : JpaRepository<DefaultOrder, Long> {

    fun findByUserIdAndNoteId(userId: Long, noteId: Long): DefaultOrder?

    fun findAllByUserId(userId: Long): List<DefaultOrder>

    fun findByOrderNo(orderNo: String): DefaultOrder

}