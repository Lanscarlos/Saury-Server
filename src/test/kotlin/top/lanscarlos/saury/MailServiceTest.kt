package top.lanscarlos.saury

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import top.lanscarlos.saury.service.MailService

/**
 * Saury
 * top.lanscarlos.saury
 *
 * @author Lanscarlos
 * @since 2023-09-08 00:41
 */
@SpringBootTest
class MailServiceTest {

    @Autowired
    lateinit var mailService: MailService

//    @Test
    fun sendEmail() {
        mailService.sendSimpleMail(
            "lanscarlos@hotmail.com",
            "Hello! Lanscarlos!",
            "Your code is 114514. The code will be expired in 5 minutes. Please do not tell anyone."
        )
    }

//    @Test
    fun templateVerification() {
        val resource =
            ClassPathResource("templates/mail-verify-code.html").inputStream.bufferedReader(Charsets.UTF_8).use {
                it.readText()
            }
        println(resource)
    }

//    @Test
    fun testHtmlMail() {
        mailService.sendHtmlMail("lanscarlos@hotmail.com", "Verify Code", "mail-verify-code", "code" to "114514")
    }
}