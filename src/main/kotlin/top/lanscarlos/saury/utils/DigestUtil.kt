package top.lanscarlos.saury.utils

import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

/**
 * Saury
 * top.lanscarlos.saury.utils
 *
 * @author Lanscarlos
 * @since 2024-02-24 20:29
 */

/**
 * 加密字符串
 * 默认使用 sha-256 算法
 *
 * @param algorithm 算法类型（可使用 md5、sha-1、sha-256 等）
 * */
fun String.digest(algorithm: String = "SHA-256"): String {
    return this.toByteArray(StandardCharsets.UTF_8).digest(algorithm)
}

/**
 * 加密字符串
 * 默认使用 sha-256 算法
 *
 * @param algorithm 算法类型（可使用 md5、sha-1、sha-256 等）
 * */
fun ByteArray.digest(algorithm: String = "SHA-256"): String {
    return try {
        val digest = MessageDigest.getInstance(algorithm)
        digest.update(this)
        BigInteger(1, digest.digest()).toString(16)
    } catch (e: Exception) {
        throw IllegalArgumentException("No such algorithm name \"$algorithm\"")
    }
}