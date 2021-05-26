package dev.gtpush

import android.content.Context
import com.igexin.sdk.GTIntentService
import com.igexin.sdk.message.GTCmdMessage
import com.igexin.sdk.message.GTNotificationMessage
import com.igexin.sdk.message.GTTransmitMessage
import dev.engine.log.DevLogEngine
import dev.engine.push.DevPushEngine

/**
 * detail: 个推推送广播
 * @author Ttt
 * @see https://docs.getui.com/getui/mobile/android/api/
 */
class GTPushIntentService : GTIntentService() {

    // 日志 TAG
    private val TAG = GTPushIntentService::class.java.simpleName

    /**
     * 推送进程启动通知
     * @param context Context
     * @param pid Int
     */
    override fun onReceiveServicePid(
        context: Context?,
        pid: Int
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onReceiveServicePid] $pid"
        )
        // 推送进程启动通知
        DevPushEngine.getEngine()?.onReceiveServicePid(
            context, pid
        )
    }

    /**
     * 初始化 Client Id 成功通知
     * @param context Context
     * @param clientId String
     */
    override fun onReceiveClientId(
        context: Context?,
        clientId: String?
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onReceiveClientId] $clientId"
        )
        clientId?.let {
            // 初始化 Client Id 成功通知
            DevPushEngine.getEngine()?.onReceiveClientId(
                context, it
            )
        }
    }

    /**
     * 设备 ( 厂商 ) Token 通知
     * @param context Context
     * @param deviceToken String
     */
    override fun onReceiveDeviceToken(
        context: Context?,
        deviceToken: String?
    ) {
        super.onReceiveDeviceToken(context, deviceToken)

        DevLogEngine.getEngine()?.dTag(
            TAG, "[onReceiveDeviceToken] $deviceToken"
        )
        deviceToken?.let {
            // 设备 ( 厂商 ) Token 通知
            DevPushEngine.getEngine()?.onReceiveDeviceToken(
                context, it
            )
        }
    }

    /**
     * 在线状态变化通知
     * @param context Context
     * @param online Boolean
     */
    override fun onReceiveOnlineState(
        context: Context?,
        online: Boolean
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onReceiveOnlineState] $online"
        )
        // 在线状态变化通知
        DevPushEngine.getEngine()?.onReceiveOnlineState(
            context, online
        )
    }

    /**
     * 命令回执通知
     * @param context Context
     * @param message GTCmdMessage
     */
    override fun onReceiveCommandResult(
        context: Context?,
        message: GTCmdMessage?
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onReceiveCommandResult] $message"
        )
        message?.let {
            // 命令回执通知
            DevPushEngine.getEngine()?.run {
                onReceiveCommandResult(
                    context, convertMessage(it)
                )
            }
        }
    }

    /**
     * 推送消息送达通知
     * @param context Context
     * @param message GTNotificationMessage
     */
    override fun onNotificationMessageArrived(
        context: Context?,
        message: GTNotificationMessage?
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onNotificationMessageArrived] $message"
        )
        message?.let {
            // 推送消息送达通知
            DevPushEngine.getEngine()?.run {
                onNotificationMessageArrived(
                    context, convertMessage(it)
                )
            }
        }
    }

    /**
     * 推送消息点击通知
     * @param context Context
     * @param message GTNotificationMessage
     */
    override fun onNotificationMessageClicked(
        context: Context?,
        message: GTNotificationMessage?
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onNotificationMessageClicked] $message"
        )
        message?.let {
            // 推送消息点击通知
            DevPushEngine.getEngine()?.run {
                onNotificationMessageClicked(
                    context, convertMessage(it)
                )
            }
        }
    }

    /**
     * 透传消息送达通知
     * @param context Context
     * @param message GTTransmitMessage
     */
    override fun onReceiveMessageData(
        context: Context?,
        message: GTTransmitMessage?
    ) {
        DevLogEngine.getEngine()?.dTag(
            TAG, "[onReceiveMessageData] $message"
        )
        message?.let {
            // 推送消息点击通知
            DevPushEngine.getEngine()?.run {
                onReceiveMessageData(
                    context, convertMessage(it)
                )
            }
        }
    }
}