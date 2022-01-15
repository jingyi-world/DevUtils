package dev.module.share

/**
 * 是否新浪微博平台
 * @param platform SharePlatform
 * @return Boolean
 */
fun isSINA(platform: SharePlatform?): Boolean {
    return platform == SharePlatform.SINA
}

/**
 * 是否 QQ 平台
 * @param platform SharePlatform
 * @return Boolean
 */
fun isQQ(platform: SharePlatform?): Boolean {
    return platform == SharePlatform.QQ
}

/**
 * 是否 QQ 空间平台
 * @param platform SharePlatform
 * @return Boolean
 */
fun isQZONE(platform: SharePlatform?): Boolean {
    return platform == SharePlatform.QZONE
}

/**
 * 是否微信平台
 * @param platform SharePlatform
 * @return Boolean
 */
fun isWEIXIN(platform: SharePlatform?): Boolean {
    return platform == SharePlatform.WEIXIN
}

/**
 * 是否微信朋友圈平台
 * @param platform SharePlatform
 * @return Boolean
 */
fun isWEIXIN_CIRCLE(platform: SharePlatform?): Boolean {
    return platform == SharePlatform.WEIXIN_CIRCLE
}

/**
 * 是否微信收藏平台
 * @param platform SharePlatform
 * @return Boolean
 */
fun isWEIXIN_FAVORITE(platform: SharePlatform?): Boolean {
    return platform == SharePlatform.WEIXIN_FAVORITE
}

/**
 * 是否企业微信平台
 * @param platform SharePlatform
 * @return Boolean
 */
fun isWXWORK(platform: SharePlatform?): Boolean {
    return platform == SharePlatform.WXWORK
}

/**
 * 是否支付宝平台
 * @param platform SharePlatform
 * @return Boolean
 */
fun isALIPAY(platform: SharePlatform?): Boolean {
    return platform == SharePlatform.ALIPAY
}

/**
 * 是否钉钉平台
 * @param platform SharePlatform
 * @return Boolean
 */
fun isDINGTALK(platform: SharePlatform?): Boolean {
    return platform == SharePlatform.DINGTALK
}