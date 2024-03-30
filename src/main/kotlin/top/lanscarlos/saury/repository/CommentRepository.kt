package top.lanscarlos.saury.repository

import org.springframework.data.jpa.repository.JpaRepository
import top.lanscarlos.saury.core.entity.DefaultComment

/**
 * Saury
 * top.lanscarlos.saury.repository
 *
 * @author Lanscarlos
 * @since 2024-02-20 14:08
 */
interface CommentRepository : JpaRepository<DefaultComment, Long> {

    fun findAllByNoteId(noteId: Long): List<DefaultComment>

    fun countAllByNoteId(noteId: Long): Long

    fun deleteAllByNoteId(noteId: Long)

}