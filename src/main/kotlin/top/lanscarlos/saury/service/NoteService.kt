package top.lanscarlos.saury.service

import top.lanscarlos.saury.entity.Comment
import top.lanscarlos.saury.entity.Note
import top.lanscarlos.saury.entity.Order

/**
 * Saury
 * top.lanscarlos.saury.service
 *
 * 笔记服务
 *
 * @author Lanscarlos
 * @since 2024-02-20 15:33
 */
interface NoteService {

    /**
     * 获取笔记
     *
     * @param id 笔记ID
     * @return 笔记
     */
    fun getById(id: Long): Note

    /**
     * 获取用户的所有笔记
     *
     * @param userId 用户ID
     * @return 笔记列表
     */
    fun getNotesByUserId(userId: Long): List<Note>

    /**
     * 获取所有笔记
     * */
    fun getAllNotes(): List<Note>

    /**
     * 根据关键字搜索笔记
     *
     * @param keyword 关键字
     * @return 笔记列表
     */
    fun findByKeyword(keyword: String): List<Note>

    /**
     * 发布笔记
     *
     * @param userId 用户ID
     * @param title 标题
     * @param description 描述
     * @param type 类型
     * @param content 内容
     * @param tags 标签
     * @return 笔记
     */
    fun publishNote(userId: Long, title: String, description: String, type: String, price: Double, content: String, tags: List<String>): Note

    /**
     * 更新笔记
     *
     * @param noteId 笔记ID
     * @param title 标题
     * @param description 描述
     * @param content 内容
     * @param tags 标签
     * @return 笔记
     */
    fun updateNote(noteId: Long, title: String?, description: String?, content: String?, tags: List<String>?): Note

    /**
     * 删除笔记
     *
     * @param noteId 笔记ID
     */
    fun deleteNoteById(userId: Long, noteId: Long)

    /**
     * 审核笔记通过
     *
     * @param noteId 笔记ID
     * */
    fun approve(noteId: Long)

    /**
     * 审核笔记不通过
     *
     * @param noteId 笔记ID
     * */
    fun reject(noteId: Long)

    /**
     * 重审笔记
     *
     * @param noteId 笔记ID
     * */
    fun reaudit(noteId: Long)

    /**
     * 是否点赞笔记
     *
     * @param userId 用户ID
     * @param noteId 笔记ID
     * @return 是否点赞
     */
    fun isLike(userId: Long, noteId: Long): Boolean

    /**
     * 点赞笔记
     *
     * @param userId 用户ID
     * @param noteId 笔记ID
     */
    fun likeNote(userId: Long, noteId: Long)

    /**
     * 取消点赞笔记
     *
     * @param userId 用户ID
     * @param noteId 笔记ID
     */
    fun unlikeNote(userId: Long, noteId: Long)

    /**
     * 获取笔记的点赞数量
     *
     * @param noteId 笔记ID
     * @return 点赞数量
     */
    fun getLikeCount(noteId: Long): Long

    /**
     * 是否收藏笔记
     *
     * @param userId 用户ID
     * @param noteId 笔记ID
     * @return 是否收藏
     */
    fun isStar(userId: Long, noteId: Long): Boolean

    /**
     * 收藏笔记
     *
     * @param userId 用户ID
     * @param noteId 笔记ID
     */
    fun starNote(userId: Long, noteId: Long)

    /**
     * 取消收藏笔记
     *
     * @param userId 用户ID
     * @param noteId 笔记ID
     */
    fun unstarNote(userId: Long, noteId: Long)

    /**
     * 获取笔记的收藏数量
     *
     * @param noteId 笔记ID
     * @return 收藏数量
     */
    fun getStarCount(noteId: Long): Long

    /**
     * 评论笔记
     *
     * @param userId 用户ID
     * @param noteId 笔记ID
     * @param content 评论内容
     * @param parentId 父评论ID，如果是一级评论则为 0
     */
    fun commentNote(userId: Long, noteId: Long, content: String, parentId: Long): Comment

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     */
    fun deleteComment(commentId: Long)

    /**
     * 获取笔记的评论数量
     *
     * @param noteId 笔记ID
     * @return 评论数量
     */
    fun getCommentCount(noteId: Long): Long

    /**
     * 获取笔记的评论
     *
     * @param noteId 笔记ID
     * @return 评论列表
     */
    fun getComments(noteId: Long): List<Comment>

    /**
     * 是否已购买笔记
     *
     * @param noteId 笔记ID
     */
    fun isPurchased(userId: Long, noteId: Long): Boolean

    /**
     * 购买笔记
     *
     * @param noteId 笔记ID
     */
    fun purchase(userId: Long, noteId: Long): Order

}