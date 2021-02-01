package afkt.push.ui.activity

import afkt.push.R
import afkt.push.base.BaseActivity
import androidx.viewbinding.ViewBinding

/**
 * detail: 推送消息 Activity
 * @author Ttt
 * 点击推送后根据, 推送消息跳转对应的路由页
 */
class MessageActivity : BaseActivity<ViewBinding>() {

    override fun isToolBar(): Boolean = true

    override fun isViewBinding(): Boolean = false

    override fun baseLayoutId(): Int = R.layout.activity_message

    override fun initValue() {
        super.initValue()
        setTitle(TAG) // MessageActivity
    }
}