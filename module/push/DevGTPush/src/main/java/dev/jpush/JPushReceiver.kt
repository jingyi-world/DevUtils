package dev.jpush

import android.content.Context
import android.content.Intent
import cn.jpush.android.api.CmdMessage
import cn.jpush.android.api.CustomMessage
import cn.jpush.android.api.NotificationMessage
import cn.jpush.android.service.JPushMessageReceiver
import dev.engine.log.DevLogEngine
import dev.engine.push.DevPushEngine

/**
 * detail: 极光推送广播
 * @author Ttt
 * @see https://docs.jiguang.cn/jpush/client/Android/android_api/
 */
class JPushReceiver : JPushMessageReceiver() {

    // 日志 TAG
    private val TAG = JPushReceiver::class.java.simpleName

    /**
     * 收到自定义消息回调 ( 透传 )
     * @param context Context
     * @param customMessage CustomMessage
     */
    override fun onMessage(
        context: Context,
        customMessage: CustomMessage?
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onMessage] $customMessage"
        )
        customMessage?.let {
            // 透传消息送达通知
            DevPushEngine.getEngine()?.onReceiveMessageData(
                context, convertCustomMessage(it)
            )
        }
    }

    /**
     * 点击通知回调
     * @param context Context
     * @param message NotificationMessage
     */
    override fun onNotifyMessageOpened(
        context: Context,
        message: NotificationMessage?
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onNotifyMessageOpened] $message"
        )
        message?.let {
            // 推送消息点击通知
            DevPushEngine.getEngine()?.onNotificationMessageClicked(
                context, convertNotificationMessage(it)
            )
        }
    }

    /**
     * 收到通知回调
     * @param context Context
     * @param message NotificationMessage
     */
    override fun onNotifyMessageArrived(
        context: Context,
        message: NotificationMessage?
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onNotifyMessageArrived] $message"
        )
        message?.let {
            // 推送消息送达通知
            DevPushEngine.getEngine()?.onNotificationMessageArrived(
                context, convertNotificationMessage(it)
            )
        }
    }

    /**
     * 清除通知回调
     * @param context Context
     * @param message NotificationMessage
     */
    override fun onNotifyMessageDismiss(
        context: Context,
        message: NotificationMessage?
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onNotifyMessageDismiss] $message"
        )
    }

    /**
     * 通知的 MultiAction 回调
     * @param context Context
     * @param intent Intent
     */
    override fun onMultiActionClicked(
        context: Context,
        intent: Intent?
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onMultiActionClicked] 用户点击了通知栏按钮"
        )
    }

    /**
     * 注册成功回调
     * @param context Context
     * @param registrationId String
     */
    override fun onRegister(
        context: Context,
        registrationId: String?
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onRegister] $registrationId"
        )
        registrationId?.let {
            // 初始化 Client Id 成功通知
            DevPushEngine.getEngine()?.onReceiveClientId(
                context, it
            )
        }
    }

    /**
     * 长连接状态回调
     * @param context Context
     * @param isConnected Boolean
     */
    override fun onConnected(
        context: Context,
        isConnected: Boolean
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onConnected] $isConnected"
        )
        // 在线状态变化通知
        DevPushEngine.getEngine()?.onReceiveOnlineState(
            context, isConnected
        )
    }

    /**
     * 交互事件回调
     * @param context Context
     * @param cmdMessage CmdMessage
     */
    override fun onCommandResult(
        context: Context,
        cmdMessage: CmdMessage?
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onCommandResult] $cmdMessage"
        )
        cmdMessage?.let {
            // 厂商 token 注册回调
            if (it.cmd == 10000 && it.extra != null) {
                val token = it.extra.getString("token")
                token?.let {
                    // 设备 ( 厂商 ) Token 通知
                    DevPushEngine.getEngine()?.onReceiveDeviceToken(
                        context, token
                    )
                }
                val platform = it.extra.getInt("platform")
                val platformStr = convertPlatform(platform)
                DevLogEngine.getEngine()?.dTag(
                    TAG, "[onCommandResult] platform : $platformStr, token : $token"
                )
            } else {
                // 命令回执通知
                DevPushEngine.getEngine()?.onReceiveCommandResult(
                    context, convertCmdMessage(it)
                )
            }
        }
    }

    /**
     * 通知开关状态回调
     * @param context Context
     * @param isOn Boolean
     * @param source Int
     */
    override fun onNotificationSettingsCheck(
        context: Context,
        isOn: Boolean,
        source: Int
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onNotificationSettingsCheck] isOn : $isOn, source: $source"
        )
    }
}