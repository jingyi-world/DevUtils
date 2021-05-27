package dev.module.share

import android.os.Parcelable
import dev.engine.share.IShareEngine
import kotlinx.android.parcel.Parcelize

/**
 * detail: 分享参数包装类
 * @author Ttt
 * 自行根据所需参数进行封装
 */
@Parcelize
data class ShareParams(
    val messageId: String? = "",
) : Parcelable,
    IShareEngine.EngineItem()

/**
 * detail: 分享配置包装类
 * @author Ttt
 */
class ShareConfig(
    val isDebugMode: Boolean = false
) : IShareEngine.EngineConfig()