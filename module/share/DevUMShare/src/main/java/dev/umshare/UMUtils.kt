package dev.umshare

import android.app.Activity
import android.content.Context
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import dev.base.DevSource
import dev.engine.log.DevLogEngine
import dev.engine.share.listener.ShareListener
import dev.module.share.ShareParams
import dev.module.share.SharePlatform

/**
 * 通过 [DevSource] 转换 [UMImage]
 * @param context Context
 * @param source DevSource
 * @return UMImage
 */
internal fun convertShareImage(
    context: Context,
    source: DevSource
): UMImage {
    return UMImage(context, "")
}

/**
 * 通过 [SharePlatform] 转换 [SHARE_MEDIA]
 * @param platform SharePlatform
 * @param throwError 是否抛出异常
 * @return SHARE_MEDIA
 */
internal fun convertSharePlatform(
    platform: SharePlatform,
    throwError: Boolean = true
): SHARE_MEDIA {
    return when (platform) {
        SharePlatform.SINA -> SHARE_MEDIA.SINA
        SharePlatform.QQ -> SHARE_MEDIA.QQ
        SharePlatform.QZONE -> SHARE_MEDIA.QZONE
        SharePlatform.WEIXIN -> SHARE_MEDIA.WEIXIN
        SharePlatform.WEIXIN_CIRCLE -> SHARE_MEDIA.WEIXIN_CIRCLE
        SharePlatform.WEIXIN_FAVORITE -> SHARE_MEDIA.WEIXIN_FAVORITE
        SharePlatform.WXWORK -> SHARE_MEDIA.WXWORK
        SharePlatform.ALIPAY -> SHARE_MEDIA.ALIPAY
        SharePlatform.DINGTALK -> SHARE_MEDIA.DINGTALK
        else -> {
            if (throwError) {
                throw Exception("暂不支持平台 $platform")
            } else {
                SHARE_MEDIA.MORE
            }
        }
    }
}

private const val LOG_TAG = "UM_Utils_Listener"

/**
 * 转换分享事件
 * @param params ShareParams
 * @param listener 分享回调
 * @return UMShareListener
 */
internal fun convertShareListener(
    params: ShareParams?,
    listener: ShareListener<ShareParams>?
): UMShareListener {
    val operate = params?.let {
        "params platform ${it.platform}, shareType ${it.shareType}"
    } ?: "params is null"

    return object : UMShareListener {
        override fun onStart(platform: SHARE_MEDIA?) {
            DevLogEngine.getEngine()?.dTag(
                LOG_TAG, "onStart: UM $platform, $operate"
            )
            // 触发回调
            listener?.onStart(params)
        }

        override fun onResult(platform: SHARE_MEDIA?) {
            DevLogEngine.getEngine()?.dTag(
                LOG_TAG, "onResult: UM $platform, $operate"
            )
            // 触发回调
            listener?.onResult(params)
        }

        override fun onError(
            platform: SHARE_MEDIA?,
            error: Throwable?
        ) {
            DevLogEngine.getEngine()?.eTag(
                LOG_TAG, error, "onError: UM $platform, $operate"
            )
            // 触发回调
            listener?.onError(params, error)
        }

        override fun onCancel(platform: SHARE_MEDIA?) {
            DevLogEngine.getEngine()?.dTag(
                LOG_TAG, "onCancel: UM $platform, $operate"
            )
            // 触发回调
            listener?.onCancel(params)
        }
    }
}

/**
 * 平台不支持事件触发回调
 * @param platform SharePlatform
 * @param listener 分享回调
 */
internal fun listenerTriggerByNotSupportPlatform(
    platform: SharePlatform,
    listener: UMShareListener
) {
    listener.onError(SHARE_MEDIA.MORE, Exception("暂不支持平台 $platform"))
}

/**
 * 分享异常捕获事件触发回调
 * @param params ShareParams
 * @param error Exception
 * @param listener 分享回调
 */
internal fun listenerTriggerByError(
    params: ShareParams?,
    error: Exception,
    listener: UMShareListener
) {
    params?.let {
        listener.onError(
            convertSharePlatform(it.platform, false), error
        )
    }
}

/**
 * 参数无效事件触发回调
 * @param activity Activity
 * @param params ShareParams
 * @param listener 分享回调
 */
internal fun listenerTriggerByInvalidParams(
    activity: Activity?,
    params: ShareParams?,
    listener: ShareListener<ShareParams>?
) {
    listener?.let {
        val builder = StringBuilder()
        if (activity == null) {
            builder.append("activity is null")
        }
        if (params == null) {
            if (activity == null) builder.append("、")
            builder.append("params is null")
        }
        // 触发回调
        it.onError(params, Exception(builder.toString()))
    }
}