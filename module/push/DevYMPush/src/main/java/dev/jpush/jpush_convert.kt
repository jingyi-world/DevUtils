package dev.jpush

import cn.jpush.android.api.CmdMessage
import cn.jpush.android.api.CustomMessage
import cn.jpush.android.api.NotificationMessage
import dev.module.push.PushMessage

fun convertCmdMessage(message: CmdMessage): PushMessage {
    return PushMessage(
        code = message.cmd.toString()
    )
}

fun convertNotificationMessage(message: NotificationMessage): PushMessage {
    return PushMessage(
        messageId = message.msgId,
        title = message.notificationTitle,
        content = message.notificationContent,
        extras = message.notificationExtras,
    )
}

fun convertCustomMessage(message: CustomMessage): PushMessage {
    return PushMessage(
        messageId = message.messageId,
        title = message.title,
        content = message.message,
        extras = message.extra,
    )
}

/**
 * 转换平台字符串标识
 * @param platform Int
 * @return String
 */
fun convertPlatform(platform: Int): String {
    return when (platform) {
        1 -> "小米"
        2 -> "华为"
        3 -> "魅族"
        4 -> "OPPO"
        5 -> "VIVO"
        6 -> "ASUS"
        8 -> "FCM"
        else -> "UNKNOWN"
    }
}