package top.lanscarlos.saury.core.controller

import cn.dev33.satoken.util.SaResult
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
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
    fun success(@RequestParam("out_trade_no") orderId: Long, response: HttpServletResponse) {
        try {
            val order = alipayService.approveOrder(orderId)
            response.sendRedirect("http://localhost:8081/note/${order.noteId}")
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @RequestMapping("/error")
    fun error(): Map<String, String> {
        return mapOf("message" to "错误")
    }

    @RequestMapping("/submit")
    fun pay(amount: Double, subject: String, response: HttpServletResponse): SaResult {
        return try {
            val userId = authService.getLoginId()
            alipayService.submit(userId, subject, amount)
            SaResult.ok()
        } catch (ex: Exception) {
            ex.printStackTrace()
            SaResult.error(ex.message)
        }
    }

    @RequestMapping("/pay/{orderId}")
    fun pay(@PathVariable orderId: Long, response: HttpServletResponse) {
        try {
            response.contentType = "text/html;charset=utf-8"
            response.writer.write(alipayService.pay(orderId))
            response.writer.flush()
            response.writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}