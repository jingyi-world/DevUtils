package afkt.lock.ui.activity

import afkt.lock.R
import afkt.lock.base.BaseActivity
import afkt.lock.databinding.ActivityMessageBinding
import afkt.lock.jpush.PushMessage
import afkt.lock.jpush.toJsonFormat
import dev.utils.DevFinal

/**
 * detail: 推送消息 Activity
 * @author Ttt
 * 点击推送后根据, 推送消息跳转对应的路由页
 */
class MessageActivity : BaseActivity<ActivityMessageBinding>() {

    override fun isToolBar(): Boolean = true

    override fun baseLayoutId(): Int = R.layout.activity_message

    override fun initValue() {
        super.initValue()
        setTitle(TAG) // MessageActivity

        intent?.getParcelableExtra<PushMessage>(DevFinal.DATA)?.run {
            binding.vidAmMessageTv.text = toJsonFormat(this)
        }
    }
}