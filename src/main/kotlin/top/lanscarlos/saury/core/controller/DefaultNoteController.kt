package top.lanscarlos.saury.core.controller

import cn.dev33.satoken.util.SaResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.lanscarlos.saury.controller.NoteController
import top.lanscarlos.saury.service.AuthService
import top.lanscarlos.saury.service.NoteService
import top.lanscarlos.saury.service.UserService

/**
 * Saury
 * top.lanscarlos.saury.core.controller
 *
 * @author Lanscarlos
 * @since 2024-02-24 12:17
 */
@RestController
@RequestMapping("/note")
class DefaultNoteController : NoteController {

    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var noteService: NoteService

    @RequestMapping("/detail/{noteId}")
    override fun getNote(@PathVariable noteId: Long): SaResult {
        return try {
            val note = noteService.getById(noteId)
            SaResult.data(note)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/user/{userId}")
    override fun getNotesByUserId(@PathVariable userId: Long): SaResult {
        return try {
            val notes = noteService.getNotesByUserId(userId)
            SaResult.data(notes)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/publish")
    override fun publishNote(title: String, description: String, type: String, content: String, tags: String): SaResult {
        return try {
            val userId = authService.getLoginId()
            val note = noteService.publishNote(userId, title, description, type, content, tags.split(","))
            SaResult.data(note)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/update/{noteId}")
    override fun updateNote(@PathVariable noteId: Long, title: String?, description: String?, content: String?, tags: String?): SaResult {
        return try {
            val note = noteService.updateNote(noteId, title, description, content, tags?.split(","))
            SaResult.data(note)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/delete/{noteId}")
    override fun deleteNote(@PathVariable noteId: Long): SaResult {
        return try {
            noteService.deleteNoteById(noteId)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/like/{noteId}")
    override fun likeNote(@PathVariable noteId: Long): SaResult {
        return try {
            val userId = authService.getLoginId()
            noteService.likeNote(userId, noteId)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/unlike/{noteId}")
    override fun unlikeNote(@PathVariable noteId: Long): SaResult {
        return try {
            val userId = authService.getLoginId()
            noteService.unlikeNote(userId, noteId)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/star/{noteId}")
    override fun starNote(@PathVariable noteId: Long): SaResult {
        return try {
            val userId = authService.getLoginId()
            noteService.starNote(userId, noteId)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/unstar/{noteId}")
    override fun unstarNote(@PathVariable noteId: Long): SaResult {
        return try {
            val userId = authService.getLoginId()
            noteService.unstarNote(userId, noteId)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/comment/{noteId}")
    override fun commentNote(@PathVariable noteId: Long, content: String, parentId: Long?): SaResult {
        return try {
            val userId = authService.getLoginId()
            noteService.commentNote(userId, noteId, content, parentId ?: -1)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/uncomment/{commentId}")
    override fun uncommentNote(@PathVariable commentId: Long): SaResult {
        return try {
            noteService.deleteComment(commentId)
            SaResult.ok()
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/comments/{noteId}")
    override fun getComments(@PathVariable noteId: Long): SaResult {
        return try {
            val comments = noteService.getComments(noteId)
            SaResult.data(comments)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/count/likes/{noteId}")
    override fun countLikes(@PathVariable noteId: Long): SaResult {
        return try {
            val count = noteService.getLikeCount(noteId)
            SaResult.data(count)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/count/stars/{noteId}")
    override fun countStars(@PathVariable noteId: Long): SaResult {
        return try {
            val count = noteService.getStarCount(noteId)
            SaResult.data(count)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/count/comments/{noteId}")
    override fun countComments(@PathVariable noteId: Long): SaResult {
        return try {
            val count = noteService.getCommentCount(noteId)
            SaResult.data(count)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }
}