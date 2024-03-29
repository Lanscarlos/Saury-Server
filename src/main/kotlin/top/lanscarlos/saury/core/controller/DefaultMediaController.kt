package top.lanscarlos.saury.core.controller

import cn.dev33.satoken.util.SaResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import top.lanscarlos.saury.controller.MediaController
import top.lanscarlos.saury.service.MediaService
import java.io.File

/**
 * Saury
 * top.lanscarlos.saury.core.controller
 *
 * @author Lanscarlos
 * @since 2024-02-24 15:28
 */
@RestController
@RequestMapping("/media")
class DefaultMediaController : MediaController {

    @Autowired
    private lateinit var mediaService: MediaService

    @RequestMapping("/upload")
    override fun upload(file: MultipartFile): SaResult {
        return try {
//            SaResult.data("http://localhost:8080/media/static/$fileName")
            println("upload path => " + File("./media").absolutePath)
            val fileName = mediaService.upload(file)
            SaResult.data(File("./media").absolutePath)
        } catch (ex: Exception) {
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/static/{fileName}")
    fun getStatic(@PathVariable fileName: String): SaResult {
        return SaResult.data("http://localhost:8080/media/static/$fileName")
    }
}