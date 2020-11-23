package afkt.demo.base

import afkt.demo.utils.ViewModelTempUtils
import androidx.lifecycle.*
import androidx.multidex.MultiDexApplication
import dev.DevUtils
import dev.utils.app.logger.DevLogger
import dev.utils.app.logger.LogConfig
import dev.utils.app.logger.LogLevel

class BaseApplication : MultiDexApplication(), ViewModelStoreOwner, LifecycleOwner {

    // 日志 TAG
    private val TAG = "DemoKtx_TAG"

    // ViewModelStore
    private lateinit var mAppViewModelStore: ViewModelStore

    // LifecycleRegistry 为了实现 Application 注册 ViewModel 才进行实现
    private lateinit var mLifecycleRegistry: LifecycleRegistry

    override fun onCreate() {
        super.onCreate()

        // 初始化 Logger 配置
        DevLogger.init(
            LogConfig()
                .logLevel(LogLevel.DEBUG)
                .tag(TAG)
                .sortLog(true)
                .methodCount(0)
        )
        // 打开 lib 内部日志 - 线上环境, 不调用方法
        DevUtils.openLog()
        DevUtils.openDebug()

        application = this
        mAppViewModelStore = ViewModelStore()
        mLifecycleRegistry = LifecycleRegistry(this)

        // 绑定全局
        ViewModelTempUtils.observeApplication(TAG, this, application)
    }

    // ===========
    // = 静态方法 =
    // ===========

    companion object {

        private lateinit var application: BaseApplication

        fun getApplication(): BaseApplication {
            return application
        }
    }

    // =======================
    // = ViewModelStoreOwner =
    // =======================

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    // ==================
    // = LifecycleOwner =
    // ==================

    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }
}