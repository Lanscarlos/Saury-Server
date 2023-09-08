package top.lanscarlos.saury

import cn.dev33.satoken.secure.SaSecureUtil
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import top.lanscarlos.saury.core.entity.DefaultUserProfile
import top.lanscarlos.saury.repository.UserRepository

/**
 * Saury
 * top.lanscarlos.saury
 *
 * @author Lanscarlos
 * @since 2023-09-08 16:39
 */
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    lateinit var repository: UserRepository

    @Test
    fun testInsert() {
        val profile = DefaultUserProfile()
        profile.email = "lanscarlos@hotmail.com"
        profile.username = "Lanscarlos"
        profile.password = SaSecureUtil.sha256("114514")
        profile.avatar = "https://avatars.githubusercontent.com/u/32390930?v=4"
        profile.registerTime = System.currentTimeMillis()
        profile.isBanned = false
        repository.save(profile)
    }

    @Test
    fun testSelect() {
        val password = SaSecureUtil.sha256("114514")
        val profile = repository.findByEmailAndPassword("lanscarlos@hotmail.com", password)
        println("############################################")
        println(profile)
        println("############################################")
    }

}