package top.lanscarlos.saury.core.entity

import jakarta.persistence.*

/**
 * Saury
 * top.lanscarlos.saury.core.entity
 *
 * @author Lanscarlos
 * @since 2024-02-20 13:14
 */
@Entity
@Table(name = "collection")
class DefaultCollection : top.lanscarlos.saury.entity.Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long = 0

    @ManyToOne
    @JoinColumn(name = "user")
    override lateinit var user: DefaultUser

    override val name: String = "未命名收藏夹"

    override val description: String = "简介空空如也"

    override var time: Long = -1

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "reference_collection_note",
        joinColumns = [JoinColumn(name = "collection")],
        inverseJoinColumns = [JoinColumn(name = "note")]
    )
    override lateinit var content: MutableSet<DefaultNote>

}