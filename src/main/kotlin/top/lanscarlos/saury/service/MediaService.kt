package top.lanscarlos.saury.service

import org.springframework.web.multipart.MultipartFile
import java.io.File

/**
 * Saury
 * top.lanscarlos.saury.service
 *
 * @author Lanscarlos
 * @since 2024-02-24 19:10
 */
interface MediaService {

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 上传后的文件
     */
    fun upload(file: MultipartFile): File

}