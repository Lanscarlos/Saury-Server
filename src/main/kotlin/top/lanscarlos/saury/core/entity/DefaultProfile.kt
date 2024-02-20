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

    override val username: String = "用户${System.currentTimeMillis()}"

    override val signature: String = "这个人很懒，什么都没留下"

    override val avatar: String = "https://avatars.githubusercontent.com/u/32390930?v=4"

    override val gender: Int = -1

    override val birthday: Long = -1L

}