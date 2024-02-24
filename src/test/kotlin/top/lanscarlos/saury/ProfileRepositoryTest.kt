package top.lanscarlos.saury

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import top.lanscarlos.saury.core.entity.DefaultProfile
import top.lanscarlos.saury.repository.ProfileRepository

/**
 * Saury
 * top.lanscarlos.saury
 *
 * @author Lanscarlos
 * @since 2024-02-24 10:47
 */
@SpringBootTest
class ProfileRepositoryTest {

    @Autowired
    lateinit var profileRepository: ProfileRepository

//    @Test
    fun testInsert() {
        val profile = DefaultProfile()
        profile.username = "卡洛"
        profileRepository.save(profile)

        val profile2 = DefaultProfile()
        profile2.username = "小兰"
        profileRepository.save(profile2)
    }
}