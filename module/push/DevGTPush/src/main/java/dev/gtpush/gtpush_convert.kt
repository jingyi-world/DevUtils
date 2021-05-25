package dev.gtpush

import com.igexin.sdk.message.GTCmdMessage
import com.igexin.sdk.message.GTNotificationMessage
import com.igexin.sdk.message.GTTransmitMessage
import dev.module.push.PushMessage

fun convertGTCmdMessage(message: GTCmdMessage): PushMessage {
    return PushMessage(
    )
}

fun convertGTNotificationMessage(message: GTNotificationMessage): PushMessage {
    return PushMessage(
    )
}

fun convertGTTransmitMessage(message: GTTransmitMessage): PushMessage {
    return PushMessage(
    )
}