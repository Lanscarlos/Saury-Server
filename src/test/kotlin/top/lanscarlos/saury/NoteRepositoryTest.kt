package top.lanscarlos.saury

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import top.lanscarlos.saury.core.entity.DefaultImageNote
import top.lanscarlos.saury.core.entity.DefaultTextNote
import top.lanscarlos.saury.repository.NoteRepository
import top.lanscarlos.saury.repository.UserRepository

/**
 * Saury
 * top.lanscarlos.saury
 *
 * @author Lanscarlos
 * @since 2024-02-12 11:33
 */
@SpringBootTest
class NoteRepositoryTest {

    @Autowired
    lateinit var repository: NoteRepository

    @Autowired
    lateinit var userRepository: UserRepository

//    @Test
    fun testInsert() {
        val note = DefaultTextNote()
        note.title = "测试标题"
        note.description = "测试描述"
        note.createTime = System.currentTimeMillis()
        note.updateTime = System.currentTimeMillis()
        note.content = "测试内容"
        note.user = userRepository.getReferenceById(1)
        repository.save(note)
    }

//    @Test
    fun testInsert2() {
        val note = DefaultImageNote()
        note.title = "测试图片笔记"
        note.description = "测试描述"
        note.createTime = System.currentTimeMillis()
        note.updateTime = System.currentTimeMillis()
        note.content = "https://smashinglogo.com/v3/envision/image.jpg?id=50fc6d7a-bd10-4fcd-842b-39405e27eaea&format=desktop"
        note.user = userRepository.getReferenceById(1)
        repository.save(note)
    }

//    @Test
    fun testGet() {
        val note = repository.findById(2).orElseThrow { IllegalStateException("Note by id \"1\" not exists.") }
        println("user.id => ${note.user.id}")
    }

}