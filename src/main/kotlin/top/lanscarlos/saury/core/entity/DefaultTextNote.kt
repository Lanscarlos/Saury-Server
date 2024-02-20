package top.lanscarlos.saury.core.entity

import jakarta.persistence.*
import top.lanscarlos.saury.entity.TextNote

/**
 * Saury
 * top.lanscarlos.saury.core.entity
 *
 * @author Lanscarlos
 * @since 2024-02-11 00:50
 */
@Entity
@DiscriminatorValue("text")
class DefaultTextNote : DefaultNote(), TextNote {

    override var content: String = ""

}