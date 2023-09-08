package top.lanscarlos.saury.service

/**
 * Saury
 * top.lanscarlos.saury.service
 *
 * @author Lanscarlos
 * @since 2023-09-08 00:39
 */
interface MailService {

    /**
     * 发送邮件
     *
     * @param receiver 收件人
     * @param title 邮件标题
     * @param content 邮件内容
     *
     * @throws IllegalArgumentException 收件人邮箱格式不符合要求时抛出异常
     * @throws Exception 发送失败时抛出异常
     */
    fun sendMail(receiver: String, title: String, content: String)

}