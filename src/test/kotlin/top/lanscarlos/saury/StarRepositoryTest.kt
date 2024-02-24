package top.lanscarlos.saury

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import top.lanscarlos.saury.core.entity.DefaultStar
import top.lanscarlos.saury.repository.NoteRepository
import top.lanscarlos.saury.repository.StarRepository
import top.lanscarlos.saury.repository.UserRepository

/**
 * Saury
 * top.lanscarlos.saury
 *
 * @author Lanscarlos
 * @since 2024-02-20 13:18
 */
@SpringBootTest
class StarRepositoryTest {

    @Autowired
    lateinit var repository: StarRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var noteRepository: NoteRepository

//    @Test
    fun testInsert() {
        val star = DefaultStar()
        star.user = userRepository.getReferenceById(1)
        star.note = noteRepository.getReferenceById(1)
        repository.save(star)
    }
}