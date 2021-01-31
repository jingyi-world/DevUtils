package afkt.push.jpush

import android.content.Context
import cn.jpush.android.api.CustomMessage
import cn.jpush.android.api.NotificationMessage
import cn.jpush.android.service.JPushMessageReceiver

/**
 * detail: 极光推送广播
 * @author Ttt
 */
class PushReceiver : JPushMessageReceiver() {

    // 日志 TAG
    private val TAG = PushReceiver::class.java.simpleName

    override fun onMessage(
        p0: Context?,
        p1: CustomMessage?
    ) {
        super.onMessage(p0, p1)
    }

    override fun onNotifyMessageOpened(
        p0: Context?,
        p1: NotificationMessage?
    ) {
        super.onNotifyMessageOpened(p0, p1)
    }
}