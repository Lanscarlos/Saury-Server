package top.lanscarlos.saury.core.entity

import jakarta.persistence.*
import top.lanscarlos.saury.entity.Profile

/**
 * Saury
 * top.lanscarlos.saury.core.entity
 *
 * @author Lanscarlos
 * @since 2024-02-20 11:25
 */
@Entity
@Table(name = "profile")
class DefaultProfile : Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long = 0

    override var username: String = "用户${System.currentTimeMillis()}"

    override var signature: String = "这个人很懒，什么都没留下"

    override var avatar: String = listOf(
        "https://pica.zhimg.com/50/v2-62c0778dbc135799e34f93e2adc2bcb6_720w.jpg?source=1940ef5c",
        "https://pic1.zhimg.com/v2-fab7b09137ed3d64caa750aa0dd70621_r.jpg?source=1def8aca",
    ).random()

    override var gender: Int = -1

    override var birthday: Long = -1L

}