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

    @Test
    fun testInsert3() {
        val data = listOf(
            Triple("spark@saury.com", "斯巴克", "https://pica.zhimg.com/v2-fac545e8b41f711577414f7a136954c8_r.jpg?source=1def8aca"),
            Triple("sky@saury.com", "怀黑", "https://picx.zhimg.com/v2-ad2cadbc3a9ece14612f96bd9ff9221f_r.jpg?source=1def8aca"),
            Triple("black@saury.com", "布莱克", "https://picx.zhimg.com/v2-13d9d3e944c4384d22eb848c386f73ab_r.jpg?source=1def8aca"),
            Triple("hailuo@saury.com", "海螺", "https://picx.zhimg.com/v2-b27d186c8fa50380b0333bb94a8408a3_r.jpg?source=1def8aca"),
            Triple("fengxi@saury.com", "枫溪", "https://picx.zhimg.com/v2-8537f89e6e5710bfa33c524920c81f6d_r.jpg?source=1def8aca"),
            Triple("fish@saury.com", "鱼片", "https://pica.zhimg.com/80/v2-7d40bd5139612537a88a0c33eafde615_720w.webp?source=1def8aca"),
            Triple("light@saury.com", "追光", "https://picx.zhimg.com/v2-fce14e4be9b6c3ec5c163d982dc61b07_r.jpg?source=1def8aca")
        )
        for (it in data) {
            val user = DefaultUser()
            user.email = it.first
            user.password = SaSecureUtil.sha256("114514")
            user.registerTime = System.currentTimeMillis()
            repository.save(user)

            val profile = DefaultProfile()
            profile.id = user.id
            profile.username = it.second
            profile.avatar = it.third
            profileRepository.save(profile)
        }
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