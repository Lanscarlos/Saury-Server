package top.lanscarlos.saury.controller

import cn.dev33.satoken.util.SaResult

/**
 * Saury
 * top.lanscarlos.saury.controller
 *
 * @author Lanscarlos
 * @since 2024-02-24 11:53
 */
interface NoteController {

    fun getNotes(noteId: Long): SaResult

    fun getNote(noteId: Long): SaResult

    fun getNotesByUserId(userId: Long): SaResult

    fun searchByKeyword(keyword: String): SaResult

    fun publishNote(title: String, description: String, type: String, content: String, tags: String): SaResult

    fun updateNote(noteId: Long, title: String? = null, description: String? = null, content: String? = null, tags: String? = null): SaResult

    fun deleteNote(noteId: Long): SaResult

    fun approve(noteId: Long): SaResult
    fun reject(noteId: Long): SaResult


    fun isLike(noteId: Long): SaResult

    fun likeNote(noteId: Long): SaResult

    fun unlikeNote(noteId: Long): SaResult

    fun isStar(noteId: Long): SaResult

    fun starNote(noteId: Long): SaResult

    fun unstarNote(noteId: Long): SaResult

    fun commentNote(noteId: Long, content: String, parentId: Long? = null): SaResult

    fun uncommentNote(commentId: Long): SaResult

    fun getComments(noteId: Long): SaResult

    fun countLikes(noteId: Long): SaResult

    fun countStars(noteId: Long): SaResult

    fun countComments(noteId: Long): SaResult

    fun purchaseNote(noteId: Long): SaResult

}