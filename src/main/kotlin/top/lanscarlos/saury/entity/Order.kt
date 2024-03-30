package top.lanscarlos.saury.entity

/**
 * Saury
 * top.lanscarlos.saury.entity
 *
 * @author Lanscarlos
 * @since 2024-03-06 17:20
 */
interface Order {

    /**
     * 唯一标识 (数据库自增主键)
     */
    val id: Long

    /**
     * 订单号
     */
    val orderNo: String

    /**
     * 订单用户
     */
    val user: User

    /**
     * 订单名称
     */
    val subject: String

    /**
     * 订单金额
     */
    val amount: Double

    /**
     * 订单状态
     * 是否已支付
     */
    val status: Boolean

    /**
     * 创建时间
     */
    val createTime: Long

    /**
     * 关联笔记属性
     * 用于判断当前订单属于充值订单还是购买订单
     * 若该属性大于零，即代表当前属于购买订单，该属性代表购买的笔记 ID
     * */
    val noteId: Long

}