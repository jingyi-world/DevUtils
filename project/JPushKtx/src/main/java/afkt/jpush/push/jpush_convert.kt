package afkt.jpush.push

import cn.jpush.android.api.CmdMessage
import cn.jpush.android.api.CustomMessage
import cn.jpush.android.api.NotificationMessage
import dev.module.push.PushMessage

// ==========
// = 对外方法 =
// ==========

fun convertEngineMessage(message: Any?): PushMessage? {
    return message?.let {
        return when (it) {
            is CmdMessage -> {
                convertCmdMessage(it)
            }
            is NotificationMessage -> {
                convertNotificationMessage(it)
            }
            is CustomMessage -> {
                convertCustomMessage(it)
            }
            else -> null
        }
    }
}

// ==========
// = 内部方法 =
// ==========

private fun convertCmdMessage(message: CmdMessage): PushMessage {
    return PushMessage(
        code = message.cmd.toString()
    )
}

private fun convertNotificationMessage(message: NotificationMessage): PushMessage {
    return PushMessage(
        messageId = message.msgId,
        title = message.notificationTitle,
        content = message.notificationContent,
        extras = message.notificationExtras,
    )
}

private fun convertCustomMessage(message: CustomMessage): PushMessage {
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
private fun convertPlatform(platform: Int): String {
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