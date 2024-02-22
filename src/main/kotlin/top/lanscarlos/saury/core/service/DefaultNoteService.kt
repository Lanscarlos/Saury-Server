package top.lanscarlos.saury.core.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.lanscarlos.saury.core.entity.*
import top.lanscarlos.saury.entity.Comment
import top.lanscarlos.saury.entity.Note
import top.lanscarlos.saury.repository.*
import top.lanscarlos.saury.service.NoteService
import top.lanscarlos.saury.service.UserService
import kotlin.jvm.optionals.getOrNull

/**
 * Saury
 * top.lanscarlos.saury.core.service
 *
 * @author Lanscarlos
 * @since 2024-02-20 16:50
 */
@Service
class DefaultNoteService : NoteService {

    @Autowired
    private lateinit var noteRepository: NoteRepository

    @Autowired
    private lateinit var likeRepository: LikeRepository

    @Autowired
    private lateinit var starRepository: StarRepository

    @Autowired
    private lateinit var commentRepository: CommentRepository

    @Autowired
    private lateinit var userService: UserService


    override fun getById(id: Long): Note {
        return noteRepository.findById(id).orElseThrow { IllegalStateException("Note by id \"$id\" not exists.") }
    }

    override fun getNotesByUser(userId: Long): List<Note> {
        return noteRepository.findAllByUserId(userId)
    }

    override fun publishNote(
        userId: Long,
        title: String,
        description: String,
        type: String,
        content: String,
        tags: List<String>
    ): Note {
        val note = when (type) {
            "text" -> DefaultTextNote().also { it.content = content }
            "image" -> DefaultImageNote().also { it.content = content }
            "video" -> DefaultVideoNote().also { it.content = content }
            else -> throw IllegalArgumentException("Invalid note type \"$type\".")
        }

        note.user = userService.getById(userId) as DefaultUser
        note.title = title
        note.description = description
        note.createTime = System.currentTimeMillis()
        note.updateTime = note.createTime

        TODO("标签")

        return noteRepository.save(note)
    }

    override fun updateNote(
        userId: Long,
        title: String,
        description: String,
        type: String,
        content: String,
        tags: List<String>
    ): Note {
        val note = getById(userId) as DefaultNote

        note.title = title
        note.description = description
        note.updateTime = System.currentTimeMillis()

        when (note) {
            is DefaultTextNote -> note.content = content
            is DefaultImageNote -> note.content = content
            is DefaultVideoNote -> note.content = content
        }

        return noteRepository.save(note)
    }

    override fun deleteNoteById(noteId: Long) {
        noteRepository.deleteById(noteId)
    }

    @Transactional
    override fun likeNote(userId: Long, noteId: Long) {

        if (likeRepository.findByUserIdAndNoteId(userId, noteId) != null) {
            throw IllegalStateException("User \"$userId\" has liked note \"$noteId\".")
        }

        val like = DefaultLike().also {
            it.user = userService.getById(userId) as DefaultUser
            it.note = getById(noteId) as DefaultNote
            it.time = System.currentTimeMillis()
        }

        likeRepository.save(like)
    }

    override fun unlikeNote(userId: Long, noteId: Long) {
        likeRepository.deleteByUserIdAndNoteId(userId, noteId)
    }

    override fun getLikeCount(noteId: Long): Long {
        return likeRepository.countAllByNoteId(noteId)
    }

    override fun starNote(userId: Long, noteId: Long) {

        val star = DefaultStar().also {
            it.user = userService.getById(userId) as DefaultUser
            it.note = getById(noteId) as DefaultNote
            it.time = System.currentTimeMillis()
        }

        starRepository.save(star)
    }

    @Transactional
    override fun unstarNote(userId: Long, noteId: Long) {
        starRepository.deleteByUserIdAndNoteId(userId, noteId)
    }

    override fun getStarCount(noteId: Long): Long {
        return starRepository.countAllByNoteId(noteId)
    }

    override fun commentNote(userId: Long, noteId: Long, content: String, parentId: Long) {
        val user = userService.getById(userId) as DefaultUser
        val note = getById(noteId) as DefaultNote
        val parent = commentRepository.findById(parentId).getOrNull()

        val comment = DefaultComment().also {
            it.user = user
            it.note = note
            it.content = content
            it.parent = parent
            it.time = System.currentTimeMillis()
        }

        commentRepository.save(comment)
    }

    override fun deleteComment(commentId: Long) {
        commentRepository.deleteById(commentId)
    }

    override fun getCommentCount(noteId: Long): Long {
        return commentRepository.countAllByNoteId(noteId)
    }

    override fun getComments(noteId: Long): List<Comment> {
        return commentRepository.findAllByNoteId(noteId)
    }

}