package top.lanscarlos.saury.core.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import top.lanscarlos.saury.service.MediaService
import java.io.File

/**
 * Saury
 * top.lanscarlos.saury.core.service
 *
 * @author Lanscarlos
 * @since 2024-02-24 19:06
 */
@Service
class DefaultMediaService : MediaService {

    override fun upload(file: MultipartFile): File {

        // 文件类型限制
        val type = file.contentType ?: error("文件类型不能为空")
        if (!type.startsWith("image") && !type.startsWith("video")) {
            error("文件类型不支持")
        }

        // 文件大小限制
        val size = file.size
        if (size > 10 * 1024 * 1024) {
            error("文件大小不能超过10M")
        }

        // 获取文件名
        val fileName = file.originalFilename ?: "file"

        if (file.isEmpty) {
            error("文件为空")
        }

        val directory = File("./static/")
        if (!directory.exists()) {
            directory.mkdirs()
        }
        val target = File(directory.absoluteFile, fileName)
        file.transferTo(target)

        return target
    }

}