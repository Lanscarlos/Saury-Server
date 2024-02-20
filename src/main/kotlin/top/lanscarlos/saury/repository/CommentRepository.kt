package top.lanscarlos.saury.repository

import org.springframework.data.jpa.repository.JpaRepository
import top.lanscarlos.saury.core.entity.DefaultComment
import top.lanscarlos.saury.core.entity.DefaultNote

/**
 * Saury
 * top.lanscarlos.saury.repository
 *
 * @author Lanscarlos
 * @since 2024-02-20 14:08
 */
interface CommentRepository : JpaRepository<DefaultComment, Long> {

    fun findAllByNote(note: DefaultNote): List<DefaultComment>

}