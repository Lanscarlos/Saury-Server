package top.lanscarlos.saury

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import top.lanscarlos.saury.core.entity.DefaultLike
import top.lanscarlos.saury.repository.LikeRepository
import top.lanscarlos.saury.repository.NoteRepository
import top.lanscarlos.saury.repository.UserRepository

/**
 * Saury
 * top.lanscarlos.saury
 *
 * @author Lanscarlos
 * @since 2024-02-20 12:44
 */
@SpringBootTest
class LikeRepositoryTest {

    @Autowired
    lateinit var repository: LikeRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var noteRepository: NoteRepository

    @Test
    fun testInsert() {
        val like = DefaultLike()
        like.user = userRepository.getReferenceById(1)
        like.note = noteRepository.getReferenceById(1)
        repository.save(like)
    }

}