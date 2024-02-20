package top.lanscarlos.saury

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import top.lanscarlos.saury.core.entity.DefaultCollection
import top.lanscarlos.saury.repository.CollectionRepository
import top.lanscarlos.saury.repository.NoteRepository
import top.lanscarlos.saury.repository.UserRepository
import kotlin.jvm.optionals.getOrNull

/**
 * Saury
 * top.lanscarlos.saury
 *
 * @author Lanscarlos
 * @since 2024-02-20 13:18
 */
@SpringBootTest
class CollectionRepositoryTest {

    @Autowired
    lateinit var repository: CollectionRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var noteRepository: NoteRepository

    @Test
    fun testInsert() {
        val collection = DefaultCollection()
        collection.user = userRepository.getReferenceById(1)
        collection.content += noteRepository.getReferenceById(1)
        repository.save(collection)
    }

    @Test
    fun testFind() {
        val collection = repository.findById(1).getOrNull()
        println("collection?.id = ${collection?.id}")
        println("collection?.user?.id = ${collection?.user?.id}")
        println("collection?.content?.size = ${collection?.content?.size}")
        println("collection?.content?.firstOrNull()?.id = ${collection?.content?.firstOrNull()?.id}")
    }
}