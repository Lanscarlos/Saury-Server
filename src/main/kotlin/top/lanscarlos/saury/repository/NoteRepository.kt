package top.lanscarlos.saury.repository

import org.springframework.data.jpa.repository.JpaRepository
import top.lanscarlos.saury.core.entity.DefaultNote
import top.lanscarlos.saury.core.entity.DefaultUser
import top.lanscarlos.saury.entity.User

/**
 * Saury
 * top.lanscarlos.saury.repository
 *
 * @author Lanscarlos
 * @since 2024-02-12 11:52
 */
interface NoteRepository : JpaRepository<DefaultNote, Long> {

    fun findAllByUserId(userId: Long): List<DefaultNote>

}