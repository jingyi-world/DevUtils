package dev.umshare

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import com.umeng.commonsdk.UMConfigure
import com.umeng.socialize.PlatformConfig
import com.umeng.socialize.UMShareAPI
import dev.engine.share.IShareEngine
import dev.engine.share.listener.ShareListener
import dev.module.share.ShareConfig
import dev.module.share.ShareParams
import dev.module.share.SharePlatform

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
                        SharePlatform.QZONE,
                        SharePlatform.QQ -> {
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
     * @param activity {@link Activity}
     * @param params   {@link EngineItem}
     * @return {@code true} success, {@code false} fail
     */
    override fun openMinApp(
        activity: Activity?,
        params: ShareParams?
    ): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * 分享小程序
     * @param activity {@link Activity}
     * @param params   {@link EngineItem}
     * @param listener {@link ShareListener}
     * @return {@code true} success, {@code false} fail
     */
    override fun shareMinApp(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * 分享链接
     * @param activity {@link Activity}
     * @param params   {@link EngineItem}
     * @param listener {@link ShareListener}
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
     * @param activity {@link Activity}
     * @param params   {@link EngineItem}
     * @param listener {@link ShareListener}
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
     * @param activity {@link Activity}
     * @param params   {@link EngineItem}
     * @param listener {@link ShareListener}
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
     * @param activity {@link Activity}
     * @param params   {@link EngineItem}
     * @param listener {@link ShareListener}
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
     * @param activity {@link Activity}
     * @param params   {@link EngineItem}
     * @param listener {@link ShareListener}
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
     * @param activity {@link Activity}
     * @param params   {@link EngineItem}
     * @param listener {@link ShareListener}
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
     * @param activity {@link Activity}
     * @param params   {@link EngineItem}
     * @param listener {@link ShareListener}
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
     * @param activity {@link Activity}
     * @param params   {@link EngineItem}
     * @param listener {@link ShareListener}
     * @return {@code true} success, {@code false} fail
     */
    override fun share(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    // =

    /**
     * 部分平台 Activity onActivityResult 额外调用处理
     * @param context     {@link Context}
     * @param requestCode 请求 code
     * @param resultCode  resultCode
     * @param data        {@link Intent}
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