package top.lanscarlos.saury

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import top.lanscarlos.saury.core.entity.DefaultTextNote
import top.lanscarlos.saury.repository.NoteRepository

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

    @Test
    fun testInsert() {
        val note = DefaultTextNote()
        note.title = "测试标题"
        note.description = "测试描述"
        note.createTime = System.currentTimeMillis()
        note.updateTime = System.currentTimeMillis()
        note.content = "测试内容"
        repository.save(note)
    }

}