package dev.module.push

import android.os.Parcelable
import dev.engine.push.IPushEngine
import kotlinx.android.parcel.Parcelize

/**
 * detail: 推送消息包装类
 * @author Ttt
 * 自行根据所需参数进行封装
 */
@Parcelize
data class PushMessage(
    val messageId: String? = "",
    val notificationId: Int = 0,
    val notificationTitle: String? = "",
    val notificationContent: String? = "",
    val notificationExtras: String? = "",
    val code: String? = ""
) : Parcelable, IPushEngine.EngineItem()