package top.lanscarlos.saury.entity

/**
 * Saury
 * top.lanscarlos.saury.entity
 *
 * 视频笔记
 *
 * @author Lanscarlos
 * @since 2024-02-11 00:49
 */
interface VideoNote : Note {

    /**
     * 笔记视频 Url
     */
    val content: String

}