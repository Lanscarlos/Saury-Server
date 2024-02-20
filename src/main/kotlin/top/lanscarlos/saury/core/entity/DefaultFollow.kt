package top.lanscarlos.saury.core.entity

import jakarta.persistence.*
import top.lanscarlos.saury.entity.Follow

/**
 * Saury
 * top.lanscarlos.saury.core.entity
 *
 * @author Lanscarlos
 * @since 2024-02-20 13:36
 */
@Entity
@Table(name = "follow")
class DefaultFollow : Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long = 0

    @ManyToOne
    @JoinColumn(name = "follower")
    override lateinit var follower: DefaultUser

    @ManyToOne
    @JoinColumn(name = "following")
    override lateinit var following: DefaultUser

}