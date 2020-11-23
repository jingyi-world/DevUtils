package afkt.demo.utils

import afkt.demo.model.MainActivityViewModel
import android.app.Application
import androidx.lifecycle.LifecycleOwner
import dev.base.utils.assist.DevBaseViewModelAssist
import dev.utils.app.logger.DevLogger

/**
 * detail: ViewModel 临时工具类
 * @author Ttt
 */
object ViewModelTempUtils {

    // 日志 TAG
    private val TAG = ViewModelTempUtils::class.java.simpleName

    /**
     * 统一监听方法
     * @param tag TAG
     * @param owner [LifecycleOwner]
     * @param viewModel [MainActivityViewModel]
     */
    fun observe(tag: String, owner: LifecycleOwner, viewModel: MainActivityViewModel?) {
        viewModel?.let {
            // 进行监听
            viewModel.number.observe(owner, {
                DevLogger.dTag(TAG, "%s observe number：%s", tag, viewModel.number.value)
            })
        }
    }

    /**
     * 统一监听方法
     * @param tag TAG
     * @param owner [LifecycleOwner]
     * @param application [Application]
     */
    fun observeApplication(tag: String, owner: LifecycleOwner, application: Application?) {
        observe(
            tag, owner,
            DevBaseViewModelAssist().getAppViewModelProvider(application)?.get(
                MainActivityViewModel::class.java
            )!!
        )
    }
}