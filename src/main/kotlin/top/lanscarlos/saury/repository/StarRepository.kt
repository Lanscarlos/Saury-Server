package top.lanscarlos.saury.repository

import org.springframework.data.jpa.repository.JpaRepository
import top.lanscarlos.saury.core.entity.DefaultStar

/**
 * Saury
 * top.lanscarlos.saury.repository
 *
 * @author Lanscarlos
 * @since 2024-02-22 11:19
 */
interface StarRepository : JpaRepository<DefaultStar, Long> {

    fun findAllByUserId(userId: Long): List<DefaultStar>

    fun countAllByNoteId(noteId: Long): Long

    fun deleteByUserIdAndNoteId(userId: Long, noteId: Long)

}