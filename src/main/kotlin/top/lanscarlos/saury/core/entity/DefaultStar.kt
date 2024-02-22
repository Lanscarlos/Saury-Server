package top.lanscarlos.saury.core.entity

import jakarta.persistence.*
import top.lanscarlos.saury.entity.Star

/**
 * Saury
 * top.lanscarlos.saury.core.entity
 *
 * @author Lanscarlos
 * @since 2024-02-22 11:18
 */
@Entity
@Table(name = "star")
class DefaultStar : Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long = 0

    @ManyToOne
    @JoinColumn(name = "user")
    override lateinit var user: DefaultUser

    @ManyToOne
    @JoinColumn(name = "note")
    override lateinit var note: DefaultNote

    override var time: Long = -1

}