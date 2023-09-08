package top.lanscarlos.saury.core.entity

import jakarta.persistence.*
import top.lanscarlos.saury.entity.UserProfile

/**
 * Saury
 * top.lanscarlos.saury.core.entity
 *
 * @author Lanscarlos
 * @since 2023-09-08 13:53
 */
@Entity
@Table(name = "user_profile")
class DefaultUserProfile : UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long = 0

    override lateinit var email: String

    override lateinit var password: String

    override lateinit var username: String

    override lateinit var avatar: String

    override var registerTime: Long = 0

    override var isBanned: Boolean = false

    override fun toString(): String {
        return "DefaultUserProfile(id=$id, email='$email', password='$password', username='$username', avatar='$avatar', registerTime=$registerTime, isBanned=$isBanned)"
    }

}