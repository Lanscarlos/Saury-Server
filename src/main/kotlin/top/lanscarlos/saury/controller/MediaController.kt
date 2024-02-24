package top.lanscarlos.saury.controller

import cn.dev33.satoken.util.SaResult
import org.springframework.web.multipart.MultipartFile

/**
 * Saury
 * top.lanscarlos.saury.controller
 *
 * 用于上传/获取媒体资源
 *
 * @author Lanscarlos
 * @since 2024-02-24 15:26
 */
interface MediaController {

    /**
     * 上传文件
     * @param file 文件
     * @return 上传结果
     */
    fun upload(file: MultipartFile): SaResult

}