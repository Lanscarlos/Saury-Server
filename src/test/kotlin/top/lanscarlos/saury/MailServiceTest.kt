package top.lanscarlos.saury

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
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
    lateinit var sender: JavaMailSender

    @Test
    fun feasibilityVerification() {
        val message = sender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)
        helper.setFrom("saury_live@163.com")
        helper.setTo("lanscarlos@hotmail.com")
        helper.setSubject("Hello! Lanscarlos!")
        helper.setText("Your code is 123456. The code will be expired in 5 minutes. Please do not tell anyone.")
        sender.send(message)
    }

    @Autowired
    lateinit var mailService: MailService

    @Test
    fun sendEmail() {
        mailService.sendMail(
            "lanscarlos@hotmail.com",
            "Hello! Lanscarlos!",
            "Your code is 114514. The code will be expired in 5 minutes. Please do not tell anyone."
        )
    }
}