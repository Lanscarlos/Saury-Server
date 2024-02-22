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

    override var avatar: String = "https://avatars.githubusercontent.com/u/32390930?v=4"

    override var gender: Int = -1

    override var birthday: Long = -1L

}