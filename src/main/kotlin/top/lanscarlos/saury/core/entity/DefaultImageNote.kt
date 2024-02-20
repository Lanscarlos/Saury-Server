package top.lanscarlos.saury.core.entity

import jakarta.persistence.*
import top.lanscarlos.saury.entity.ImageNote

/**
 * Saury
 * top.lanscarlos.saury.core.entity
 *
 * @author Lanscarlos
 * @since 2024-02-11 00:55
 */
@Entity
@DiscriminatorValue("image")
class DefaultImageNote : DefaultNote(), ImageNote {

    override var content: String = ""

}