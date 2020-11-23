package afkt.demo.ui.activity

import afkt.demo.R
import afkt.demo.base.BaseApplication
import afkt.demo.model.MainActivityViewModel
import afkt.demo.utils.ViewModelTempUtils
import android.os.Bundle
import android.os.Handler
import android.view.View
import dev.base.expand.viewmodel.DevBaseViewModelActivity

/**
 * detail: Main Application ViewModel Activity
 * @author Ttt
 */
class MainApplicationViewModelActivity : DevBaseViewModelActivity<MainActivityViewModel>() {

    override fun baseContentId(): Int {
        return R.layout.activity_main_application_view_model
    }

    override fun baseContentView(): View? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    override fun initViewModel() {
        viewModel = getAppViewModelProvider(BaseApplication.getApplication())?.get(MainActivityViewModel::class.java)!!
        // 复用方法进行监听
        ViewModelTempUtils.observe(TAG, this, viewModel)
        // 临时改变值
        Handler().postDelayed(Runnable {
            viewModel.number.value = Int.MAX_VALUE
        }, 3000)
    }
}