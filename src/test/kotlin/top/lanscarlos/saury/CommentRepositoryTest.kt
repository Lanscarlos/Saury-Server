package top.lanscarlos.saury

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import top.lanscarlos.saury.core.entity.DefaultComment
import top.lanscarlos.saury.repository.CommentRepository
import top.lanscarlos.saury.repository.NoteRepository
import top.lanscarlos.saury.repository.UserRepository

/**
 * Saury
 * top.lanscarlos.saury
 *
 * @author Lanscarlos
 * @since 2024-02-20 14:31
 */
@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    lateinit var repository: CommentRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var noteRepository: NoteRepository

//    @Test
    fun testInsert() {
        val note = noteRepository.getReferenceById(1)
        val user = userRepository.getReferenceById(1)
        val comment = DefaultComment()
        comment.note = note
        comment.user = user
        comment.content = "这是一条测试评论"

        repository.save(comment)
    }

//    @Test
    fun testInsert2() {
        val user = userRepository.getReferenceById(2)
        val note = noteRepository.getReferenceById(1)
        val comment = DefaultComment()
        comment.user = user
        comment.note = note
        comment.content = "这是一条回复评论"
        comment.parent = repository.getReferenceById(2)

        repository.save(comment)
    }

//    @Test
    fun testFind() {
        val comments = repository.findAllByNoteId(1)
        println("comment.size = ${comments.size}")
        val comment = comments.firstOrNull()
        println("comment?.user?.id = ${comment?.user?.id}")
        println("comment?.note?.id = ${comment?.note?.id}")
        println("comment?.content = ${comment?.content}")
    }

}