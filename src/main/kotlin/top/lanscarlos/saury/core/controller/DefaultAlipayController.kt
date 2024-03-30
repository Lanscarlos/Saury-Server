package top.lanscarlos.saury.core.controller

import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.lanscarlos.saury.service.AlipayService
import top.lanscarlos.saury.service.AuthService

/**
 * Saury
 * top.lanscarlos.saury.core.controller
 *
 * @author Lanscarlos
 * @since 2024-03-06 12:03
 */
@CrossOrigin
@RestController
@RequestMapping("/alipay")
class DefaultAlipayController {

    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    lateinit var alipayService: AlipayService

    @RequestMapping("/success")
    fun success(): Map<String, String> {
        return mapOf("message" to "成功")
    }

    @RequestMapping("/error")
    fun error(): Map<String, String> {
        return mapOf("message" to "错误")
    }

    @RequestMapping("/submit")
    fun pay(amount: Double, subject: String, response: HttpServletResponse) {
        try {
            response.contentType = "text/html;charset=utf-8"
            response.writer.write(alipayService.pay(authService.getLoginId(), subject, amount))
            response.writer.flush()
            response.writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}