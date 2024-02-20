package top.lanscarlos.saury

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import top.lanscarlos.saury.core.entity.DefaultFollow
import top.lanscarlos.saury.repository.FollowRepository
import top.lanscarlos.saury.repository.UserRepository

/**
 * Saury
 * top.lanscarlos.saury
 *
 * @author Lanscarlos
 * @since 2024-02-20 13:48
 */
@SpringBootTest
class FollowRepositoryTest {

    @Autowired
    lateinit var repository: FollowRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun testInsert() {
        val follow = DefaultFollow()
        follow.follower = userRepository.getReferenceById(1)
        follow.following = userRepository.getReferenceById(2)
        repository.save(follow)
    }

    @Test
    fun testFindByFollower() {
        val user = userRepository.getReferenceById(1)
        val list = repository.findAllByFollower(user)
        println("list.size = ${list.size}")
        println("first.id = ${list.firstOrNull()?.id}")
        println("first.following = ${list.firstOrNull()?.following?.id}")
    }

    @Test
    fun testCountByFollower() {
        val user = userRepository.getReferenceById(1)
        val count = repository.countByFollower(user)
        println("count = $count")
    }

}