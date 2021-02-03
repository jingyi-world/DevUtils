package afkt.lock.base

import android.view.View
import androidx.viewbinding.ViewBinding
import dev.base.expand.content.DevBaseContentViewBindingActivity

abstract class BaseActivity<VB : ViewBinding> : DevBaseContentViewBindingActivity<VB>() {

    override fun baseLayoutView(): View? = null
}