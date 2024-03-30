package top.lanscarlos.saury.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import top.lanscarlos.saury.core.entity.DefaultNote

/**
 * Saury
 * top.lanscarlos.saury.repository
 *
 * @author Lanscarlos
 * @since 2024-02-12 11:52
 */
interface NoteRepository : JpaRepository<DefaultNote, Long> {

    fun findAllByUserId(userId: Long): List<DefaultNote>

    @Query("SELECT n FROM DefaultNote n WHERE n.title LIKE %:keyword% OR n.description LIKE %:keyword% OR (n.type = 'text' AND EXISTS (SELECT 1 FROM DefaultTextNote t WHERE t.id = n.id AND t.content LIKE %:keyword%))")
    fun findByKeyword(@Param("keyword") keyword: String): List<DefaultNote>

}