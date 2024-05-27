package top.lanscarlos.saury

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
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

//    @Test
    fun testInsert() {
        val follow = DefaultFollow()
        follow.follower = userRepository.getReferenceById(1)
        follow.following = userRepository.getReferenceById(2)
        repository.save(follow)
    }

    @Test
    fun testInsert2() {
        for (id in 7..13) {
            val follow = DefaultFollow()
            follow.follower = userRepository.getReferenceById(2)
            follow.following = userRepository.getReferenceById(id.toLong())
            repository.save(follow)
        }
    }

//    @Test
    fun testFindByFollower() {
        val list = repository.findAllByFollowerId(1)
        println("list.size = ${list.size}")
        println("first.id = ${list.firstOrNull()?.id}")
        println("first.following = ${list.firstOrNull()?.following?.id}")
    }

//    @Test
    fun testCountByFollower() {
        val count = repository.countByFollowerId(1)
        println("count = $count")
    }

//    @Test
    @Transactional
    fun testDelete() {
        repository.deleteByFollowerIdAndFollowingId(1, 2)
    }

}