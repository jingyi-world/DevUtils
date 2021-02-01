package afkt.push.ui.activity

import afkt.push.R
import afkt.push.base.BaseActivity
import afkt.push.databinding.ActivityMessageBinding
import afkt.push.jpush.PushMessage
import dev.utils.DevFinal

/**
 * detail: 推送消息 Activity
 * @author Ttt
 * 点击推送后根据, 推送消息跳转对应的路由页
 */
class MessageActivity : BaseActivity<ActivityMessageBinding>() {

    override fun isToolBar(): Boolean = true

    override fun isViewBinding(): Boolean = false

    override fun baseLayoutId(): Int = R.layout.activity_message

    override fun initValue() {
        super.initValue()
        setTitle(TAG) // MessageActivity

        intent?.getParcelableExtra<PushMessage>(DevFinal.DATA)?.run {
            var builder = StringBuilder()
                .append("msgId: ${msgId}")
                .append("${DevFinal.NEW_LINE_STR}")
                .append("notificationId: ${notificationId}")
                .append("${DevFinal.NEW_LINE_STR}")
                .append("notificationTitle: ${notificationTitle}")
                .append("${DevFinal.NEW_LINE_STR}")
                .append("notificationContent: ${notificationContent}")
                .append("${DevFinal.NEW_LINE_STR}")
                .append("notificationExtras: ${notificationExtras}")
                .append("${DevFinal.NEW_LINE_STR}")
                .append("pushExtras routerUri: ${pushExtras?.routerUri}")

            binding.vidAmMessageTv.text = builder.toString()
        }
    }
}