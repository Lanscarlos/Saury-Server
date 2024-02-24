package top.lanscarlos.saury

import cn.dev33.satoken.secure.SaSecureUtil
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import top.lanscarlos.saury.core.entity.DefaultProfile
import top.lanscarlos.saury.core.entity.DefaultUser
import top.lanscarlos.saury.repository.ProfileRepository
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

    @Autowired
    lateinit var profileRepository: ProfileRepository

//    @Test
    fun testInsert() {
        val user = DefaultUser()
        user.email = "lanscarlos@hotmail.com"
        user.password = SaSecureUtil.sha256("114514")
        user.registerTime = System.currentTimeMillis()
        user.isBanned = false
        repository.save(user)

        val profile = DefaultProfile()
        profile.id = user.id
        profile.username = "卡洛"
        profileRepository.save(profile)

    }

//    @Test
    fun testInsert2() {
        val user = DefaultUser()
        user.email = "aiurlanta@hotmail.com"
        user.password = SaSecureUtil.sha256("114514")
        user.registerTime = System.currentTimeMillis()
        user.isBanned = false
        repository.save(user)

        val profile = DefaultProfile()
        profile.id = user.id
        profile.username = "小兰"
        profileRepository.save(profile)
    }

//    @Test
    fun testSelect() {
        val password = SaSecureUtil.sha256("114514")
        val profile = repository.findByEmailAndPassword("lanscarlos@hotmail.com", password)
        println("############################################")
        println(profile)
        println("############################################")
    }

}