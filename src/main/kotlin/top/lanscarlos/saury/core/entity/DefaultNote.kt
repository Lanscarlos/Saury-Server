package top.lanscarlos.saury.core.entity

import jakarta.persistence.*
import top.lanscarlos.saury.entity.Note

/**
 * Saury
 * top.lanscarlos.saury.core.entity
 *
 * @author Lanscarlos
 * @since 2024-02-11 20:50
 */
@Entity
@Table(name = "node")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
abstract class DefaultNote : Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long = 0

    @ManyToOne
    @JoinColumn(name = "user")
    override lateinit var user: DefaultUser

    override var title: String = "无标题笔记"

    override var description: String = ""

    override var createTime: Long = 0

    override var updateTime: Long = 0

}