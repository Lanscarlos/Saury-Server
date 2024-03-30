package top.lanscarlos.saury.service

/**
 * Saury
 * top.lanscarlos.saury.service
 *
 * @author Lanscarlos
 * @since 2024-03-03 12:35
 */
interface AlipayService {

    fun pay(userId: Long, subject: String, amount: Double): String

}