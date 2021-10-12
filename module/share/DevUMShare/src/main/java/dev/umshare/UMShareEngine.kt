package dev.umshare

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import com.umeng.commonsdk.UMConfigure
import com.umeng.socialize.PlatformConfig
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.media.UMMin
import com.umeng.socialize.media.UMQQMini
import dev.engine.share.IShareEngine
import dev.engine.share.listener.ShareListener
import dev.module.share.*

/**
 * detail: 友盟分享 Engine 实现
 * @author Ttt
 */
class UMShareEngine : IShareEngine<ShareConfig, ShareParams> {

    override fun initialize(
        application: Application?,
        config: ShareConfig?
    ) {
        application?.let {
            config?.let { sc ->
                UMConfigure.init(
                    it, sc.appKey, sc.channel,
                    UMConfigure.DEVICE_TYPE_PHONE,
                    sc.pushSecret
                )
                // 友盟分享 Log 控制开关
                UMConfigure.setLogEnabled(sc.isDebugMode)
                // 配置分享平台 Key 信息
                sc.platformKey.forEach {
                    when (it.platform) {
                        SharePlatform.SINA -> {
                            // 新浪微博设置
                            PlatformConfig.setSinaWeibo(
                                it.appId, it.appKey,
                                it.redirectUrl
                            )
                            // 新浪微博必须统一设置为
                            // PlatformConfig.setSinaFileProvider(应用包名.fileprovider)
                            PlatformConfig.setSinaFileProvider(
                                it.fileProvider
                            )
                        }
                        SharePlatform.QQ,
                        SharePlatform.QZONE -> {
                            // QQ设置
                            PlatformConfig.setQQZone(
                                it.appId, it.appKey
                            )
                            PlatformConfig.setQQFileProvider(
                                it.fileProvider
                            )
                        }
                        SharePlatform.WEIXIN,
                        SharePlatform.WEIXIN_CIRCLE,
                        SharePlatform.WEIXIN_FAVORITE -> {
                            // 微信设置
                            PlatformConfig.setWeixin(
                                it.appId, it.appKey
                            )
                            PlatformConfig.setWXFileProvider(
                                it.fileProvider
                            )
                        }
                        SharePlatform.WXWORK -> {
                            // 企业微信设置
                            PlatformConfig.setWXWork(
                                it.appId, it.appKey,
                                it.agentId, it.schema
                            )
                            PlatformConfig.setWXWorkFileProvider(
                                it.fileProvider
                            )
                        }
                        SharePlatform.ALIPAY -> {
                            // 支付宝设置
                            PlatformConfig.setAlipay(it.appId)
                        }
                        SharePlatform.DINGTALK -> {
                            // 钉钉设置
                            PlatformConfig.setDing(it.appId)
                        }
                    }
                }
            }
        }
    }

    // =

    /**
     * 打开小程序
     * @param activity Activity
     * @param params   Share ( Data、Params ) Item
     * @param listener 分享回调
     * @return {@code true} success, {@code false} fail
     */
    override fun openMinApp(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        // 转换分享事件
        val shareListener = convertShareListener(params, listener)
        if (activity != null && params != null) {
            try {
                // 获取 UM 分享平台
                val umPlatform = convertSharePlatform(params.platform)

            } catch (error: Exception) {
                // 分享异常捕获事件触发回调
                listenerTriggerByError(params, error, shareListener)
            }
        } else {
            // 参数无效事件触发回调
            listenerTriggerByInvalidParams(activity, params, listener)
        }
        return false
    }

    /**
     * 分享小程序
     * @param activity Activity
     * @param params   Share ( Data、Params ) Item
     * @param listener 分享回调
     * @return {@code true} success, {@code false} fail
     */
    override fun shareMinApp(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        // 转换分享事件
        val shareListener = convertShareListener(params, listener)
        if (activity != null && params != null) {
            try {
                // 小程序类型支持分享微信小程序和 QQ 小程序
                // 其中微信小程序只支持分享到微信好友、朋友圈, 微信收藏暂不支持
                if (isWEIXIN(params.platform) || isWEIXIN_CIRCLE(params.platform)) {
                    // 兼容低版本的网页链接
                    val umMin = UMMin(params.url)
                    // 小程序消息封面图片
                    umMin.setThumb(convertShareImage(activity, params.thumbnail!!))
                    // 小程序消息 title
                    umMin.title = params.title
                    // 小程序消息描述
                    umMin.description = params.description
                    // 小程序页面路径
                    umMin.path = params.path
                    // 小程序原始 ID ( 在微信平台查询 )
                    umMin.userName = params.userName
                    // 分享操作
                    ShareAction(activity)
                        .withMedia(umMin)
                        .setPlatform(convertSharePlatform(params.platform))
                        .setCallback(shareListener).share()
                    return true
                } else if (isQQ(params.platform)) {
                    // 兼容低版本的网页链接
                    val qqMini = UMQQMini(params.url)
                    // 小程序消息封面图片 ( 缩略图支持网络图片和本地图片 )
                    qqMini.setThumb(convertShareImage(activity, params.thumbnail!!))
                    // 小程序消息 title
                    qqMini.title = params.title
                    // 小程序消息描述
                    qqMini.description = params.description
                    // 小程序页面路径
                    qqMini.path = params.path
                    // 小程序原始 ID
                    qqMini.miniAppId = params.miniAppId
                    // 分享操作
                    ShareAction(activity)
                        .withMedia(qqMini)
                        .setPlatform(convertSharePlatform(params.platform))
                        .setCallback(shareListener).share()
                    return true
                } else {
                    // 平台不支持事件触发回调
                    listenerTriggerByNotSupportPlatform(params.platform, shareListener)
                }
            } catch (error: Exception) {
                // 分享异常捕获事件触发回调
                listenerTriggerByError(params, error, shareListener)
            }
        } else {
            // 参数无效事件触发回调
            listenerTriggerByInvalidParams(activity, params, listener)
        }
        return false
    }

    /**
     * 分享链接
     * @param activity Activity
     * @param params   Share ( Data、Params ) Item
     * @param listener 分享回调
     * @return {@code true} success, {@code false} fail
     */
    override fun shareUrl(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * 分享图片
     * @param activity Activity
     * @param params   Share ( Data、Params ) Item
     * @param listener 分享回调
     * @return {@code true} success, {@code false} fail
     */
    override fun shareImage(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * 分享视频
     * @param activity Activity
     * @param params   Share ( Data、Params ) Item
     * @param listener 分享回调
     * @return {@code true} success, {@code false} fail
     */
    override fun shareVideo(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * 分享音乐
     * @param activity Activity
     * @param params   Share ( Data、Params ) Item
     * @param listener 分享回调
     * @return {@code true} success, {@code false} fail
     */
    override fun shareMusic(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * 分享表情
     * @param activity Activity
     * @param params   Share ( Data、Params ) Item
     * @param listener 分享回调
     * @return {@code true} success, {@code false} fail
     */
    override fun shareEmoji(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * 分享文本
     * @param activity Activity
     * @param params   Share ( Data、Params ) Item
     * @param listener 分享回调
     * @return {@code true} success, {@code false} fail
     */
    override fun shareText(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * 分享文件
     * @param activity Activity
     * @param params   Share ( Data、Params ) Item
     * @param listener 分享回调
     * @return {@code true} success, {@code false} fail
     */
    override fun shareFile(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * 分享操作 ( 通用扩展 )
     * @param activity Activity
     * @param params   Share ( Data、Params ) Item
     * @param listener 分享回调
     * @return {@code true} success, {@code false} fail
     */
    override fun share(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        if (activity != null && params != null) {
            return when (params.shareType) {
                // 打开小程序
                ShareType.OPEN_MIN_APP -> openMinApp(activity, params, listener)
                // 分享小程序
                ShareType.SHARE_MIN_APP -> shareMinApp(activity, params, listener)
                // 分享链接
                ShareType.SHARE_URL -> shareUrl(activity, params, listener)
                // 分享图片
                ShareType.SHARE_IMAGE -> shareImage(activity, params, listener)
                // 分享视频
                ShareType.SHARE_VIDEO -> shareVideo(activity, params, listener)
                // 分享音乐
                ShareType.SHARE_MUSIC -> shareMusic(activity, params, listener)
                // 分享表情
                ShareType.SHARE_EMOJI -> shareEmoji(activity, params, listener)
                // 分享文本
                ShareType.SHARE_TEXT -> shareText(activity, params, listener)
                // 分享文件
                ShareType.SHARE_FILE -> shareFile(activity, params, listener)
            }
        } else {
            // 参数无效事件触发回调
            listenerTriggerByInvalidParams(activity, params, listener)
        }
        return false
    }

    // =

    /**
     * 部分平台 Activity onActivityResult 额外调用处理
     * @param context     Context
     * @param requestCode 请求 code
     * @param resultCode  resultCode
     * @param data        Intent
     */
    override fun onActivityResult(
        context: Context?,
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        context?.let {
            UMShareAPI.get(context).onActivityResult(
                requestCode, resultCode, data
            )
        }
    }
}