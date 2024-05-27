package top.lanscarlos.saury.core.entity

import jakarta.persistence.*
import top.lanscarlos.saury.entity.User

/**
 * Saury
 * top.lanscarlos.saury.core.entity
 *
 * @author Lanscarlos
 * @since 2023-09-08 13:53
 */
@Entity
@Table(name = "user")
class DefaultUser : User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long = 0

    override lateinit var email: String

    override lateinit var password: String

    override var registerTime: Long = 0

    override var isAdmin: Boolean = false

    override var isBanned: Boolean = false

    @OneToOne
    @JoinColumn(name = "id")
    lateinit var profile: DefaultProfile

    override fun toString(): String {
        return "DefaultUserProfile(id=$id, email='$email', password='$password', registerTime=$registerTime, isBanned=$isBanned)"
    }

}