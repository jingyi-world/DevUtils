package afkt.demo.ui.activity

import afkt.demo.R
import afkt.demo.base.BaseApplication
import afkt.demo.model.MainActivityViewModel
import android.os.Bundle
import android.os.Handler
import android.view.View
import dev.base.expand.viewmodel.DevBaseViewModelActivity
import dev.utils.app.logger.DevLogger

/**
 * detail: Main ViewModel Activity
 * @author Ttt
 */
class MainViewModelActivity : DevBaseViewModelActivity<MainActivityViewModel>() {

    override fun baseContentId(): Int {
        return R.layout.activity_main
    }

    override fun baseContentView(): View? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    lateinit var vvvv: MainActivityViewModel

    override fun initViewModel() {
        var provider = viewModelAssist.getAppViewModelProvider(BaseApplication.getApplication())
        vvvv = provider?.get(MainActivityViewModel::class.java)!!

        // 打印结果
        DevLogger.dTag(TAG, "result：%s", vvvv.result.value)
        // 进行监听
        vvvv.result.observe(this, {
            DevLogger.dTag(TAG, "observe result：%s - %s", it, vvvv.result.value)
        })

        Handler().postDelayed(Runnable {
            vvvv.result.value = Int.MAX_VALUE
        }, 2000)
    }
}