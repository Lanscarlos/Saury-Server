package top.lanscarlos.saury.core.entity

import jakarta.persistence.*
import top.lanscarlos.saury.entity.Order

/**
 * Saury
 * top.lanscarlos.saury.core.entity
 *
 * @author Lanscarlos
 * @since 2024-03-06 17:25
 */
@Entity
@Table(name = "`order`")
class DefaultOrder : Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long = 0

    override var orderNo: String = System.currentTimeMillis().toString()

    @ManyToOne
    @JoinColumn(name = "user")
    override lateinit var user: DefaultUser

    override var subject: String = ""

    override var amount: Double = 1.0

    override var noteId: Long = 0

    override var status: Boolean = false

    override var createTime: Long = System.currentTimeMillis()

}