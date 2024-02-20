package top.lanscarlos.saury.core.entity

import jakarta.persistence.*
import top.lanscarlos.saury.entity.Comment

/**
 * Saury
 * top.lanscarlos.saury.core.entity
 *
 * @author Lanscarlos
 * @since 2024-02-20 14:07
 */
@Entity
@Table(name = "comment")
class DefaultComment : Comment {

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

    override lateinit var content: String

    @ManyToOne
    @JoinColumn(name = "parent")
    override var parent: DefaultComment? = null
}