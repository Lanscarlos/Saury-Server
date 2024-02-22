package top.lanscarlos.saury.repository

import org.springframework.data.jpa.repository.JpaRepository
import top.lanscarlos.saury.core.entity.DefaultLike

/**
 * Saury
 * top.lanscarlos.saury.repository
 *
 * @author Lanscarlos
 * @since 2024-02-20 12:42
 */
interface LikeRepository : JpaRepository<DefaultLike, Long> {

    fun findByUserIdAndNoteId(userId: Long, noteId: Long): DefaultLike?

    fun countAllByNoteId(noteId: Long): Long

    fun deleteByUserIdAndNoteId(userId: Long, noteId: Long)

}