package dev.module.share

import dev.engine.share.IShareEngine

/**
 * detail: 分享参数包装类
 * @author Ttt
 * 自行根据所需参数进行封装
 */
data class ShareParams(
    val messageId: String? = "",
) : IShareEngine.EngineItem()

/**
 * detail: 分享配置包装类
 * @author Ttt
 */
class ShareConfig(
    // 分享 SDK appKey
    val appKey: String,
    // 渠道信息
    val channel: String,
    val pushSecret: String? = "",
    // 是否 debug 模式
    val isDebugMode: Boolean = false,
    // 分享平台 Key 信息
    val platformKey: List<SharePlatformKey>
) : IShareEngine.EngineConfig()

/**
 * detail: 分享 ( 资源 ) 平台
 * @author Ttt
 * 参考友盟 SHARE_MEDIA 自行获取常用分享平台
 * 不直接使用 SHARE_MEDIA 方便使用 Engine 替换操作
 */
enum class SharePlatform {
    // 新浪微博
    SINA,

    // QQ、QQ 空间
    QZONE,
    QQ,

    // 微信、微信朋友圈、微信收藏
    WEIXIN,
    WEIXIN_CIRCLE,
    WEIXIN_FAVORITE,

    // 企业微信
    WXWORK,

    // 支付宝
    ALIPAY,

    // 钉钉
    DINGTALK,

    // 更多、其他 ( 预留 )
    MORE
}

/**
 * detail: 分享平台 Key
 * @author Ttt
 */
class SharePlatformKey(
    val platform: SharePlatform,
    val appId: String,
    val appKey: String? = "",
    val redirectUrl: String? = "",
    val fileProvider: String? = "",
    val agentId: String? = "",
    val schema: String? = ""
)