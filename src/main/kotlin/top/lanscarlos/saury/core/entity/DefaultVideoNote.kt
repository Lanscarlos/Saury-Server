package top.lanscarlos.saury.core.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import top.lanscarlos.saury.entity.VideoNote

/**
 * Saury
 * top.lanscarlos.saury.core.entity
 *
 * @author Lanscarlos
 * @since 2024-02-20 11:13
 */
@Entity
@DiscriminatorValue("video")
class DefaultVideoNote : DefaultNote(), VideoNote {

    override var content: String = ""

}