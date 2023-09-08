package top.lanscarlos.saury.core.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
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
         * 邮箱表达式
         * https://c.runoob.com/front-end/854/
         */
        val PATTERN_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$".toRegex()

        /**
         * 模板插值表达式
         * */
        val PATTERN_INTERPOLATION = "\\$\\{\\s*(\\w+)\\s*}".toRegex()
    }

    @Value("\${spring.mail.username}")
    lateinit var sender: String

    @Autowired
    lateinit var mailSender: JavaMailSender

    @Autowired
    lateinit var templateEngine: TemplateEngine

    /**
     * https://zhuanlan.zhihu.com/p/62526698
     * */
    override fun sendMail(receiver: String, title: String, content: String, isHtml: Boolean) {
        if (!receiver.matches(PATTERN_EMAIL)) {
            throw IllegalArgumentException("Mail format: $receiver is not correct.")
        }

        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)
        helper.setFrom(sender, "Saury")
        helper.setTo(receiver)
        helper.setSubject(title)
        helper.setText(content, isHtml)
        mailSender.send(message)
    }

    override fun sendSimpleMail(receiver: String, title: String, content: String) {
        sendMail(receiver, title, content, false)
    }

    override fun sendHtmlMail(receiver: String, title: String, template: String, vararg variables: Pair<String, String>) {
        sendHtmlMail(receiver, title, template, variables.toMap())
    }

    override fun sendHtmlMail(receiver: String, title: String, template: String, variables: Map<String, String>) {
        val context = org.thymeleaf.context.Context().apply {
            setVariable("title", title)
            setVariables(variables)
        }
        sendMail(receiver, title, templateEngine.process(template, context), true)
    }
}