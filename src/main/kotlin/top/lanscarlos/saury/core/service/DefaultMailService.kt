package top.lanscarlos.saury.core.service

import jakarta.mail.internet.InternetAddress
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import top.lanscarlos.saury.service.MailService

/**
 * Saury
 * top.lanscarlos.saury.core.service
 *
 * @author Lanscarlos
 * @since 2023-09-08 00:38
 */
@Service
class DefaultMailService : MailService {

    companion object {

        /**
         * 邮箱正则表达式
         * https://c.runoob.com/front-end/854/
         */
        val PATTERN_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$".toRegex()
    }

    @Value("\${spring.mail.username}")
    lateinit var sender: String

    @Autowired
    lateinit var mailSender: JavaMailSender

    /**
     * https://zhuanlan.zhihu.com/p/62526698
     * */
    override fun sendMail(receiver: String, title: String, content: String) {
        if (!receiver.matches(PATTERN_EMAIL)) {
            throw IllegalArgumentException("Mail format: $receiver is not correct.")
        }

        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)
        helper.setFrom(sender, "Saury")
        helper.setTo(receiver)
        helper.setSubject(title)
        helper.setText(content)
        mailSender.send(message)
    }
}