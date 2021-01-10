package afkt.app.base

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.viewbinding.ViewBinding
import dev.base.expand.viewbinding.DevBaseViewBindingActivity

abstract class BaseActivity<VB : ViewBinding> : DevBaseViewBindingActivity<VB>() {

    val viewModel by viewModels<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initOrder()
    }

    // ===================
    // = IDevBaseContent =
    // ===================

    override fun baseContentView(): View? = null
}