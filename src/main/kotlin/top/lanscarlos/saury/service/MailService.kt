package top.lanscarlos.saury.service

/**
 * Saury
 * top.lanscarlos.saury.service
 *
 * 邮件服务
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
     * @param isHtml 是否为 HTML 邮件
     *
     * @throws IllegalArgumentException 收件人邮箱格式不符合要求时抛出异常
     * @throws Exception 发送失败时抛出异常
     */
    fun sendMail(receiver: String, title: String, content: String, isHtml: Boolean = false)

    /**
     * 发送简单邮件
     *
     * @param receiver 收件人
     * @param title 邮件标题
     * @param content 邮件内容
     *
     * @throws IllegalArgumentException 收件人邮箱格式不符合要求时抛出异常
     * @throws Exception 发送失败时抛出异常
     */
    fun sendSimpleMail(receiver: String, title: String, content: String)

    /**
     * 发送 HTML 模板邮件
     *
     * @param receiver 收件人
     * @param title 邮件标题
     * @param template 模板名称
     * @param variables 模板插值
     *
     * @throws IllegalArgumentException 收件人邮箱格式不符合要求时抛出异常
     * @throws Exception 发送失败时抛出异常
     */
    fun sendHtmlMail(receiver: String, title: String, template: String, vararg variables: Pair<String, String>)

    /**
     * 发送 HTML 模板邮件
     *
     * @param receiver 收件人
     * @param title 邮件标题
     * @param template 模板名称
     * @param variables 模板插值
     *
     * @throws IllegalArgumentException 收件人邮箱格式不符合要求时抛出异常
     * @throws Exception 发送失败时抛出异常
     */
    fun sendHtmlMail(receiver: String, title: String, template: String, variables: Map<String, String>)

}