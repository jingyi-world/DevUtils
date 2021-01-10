package afkt.app.base

import afkt.app.module.AppListBean
import afkt.app.module.DeviceInfo
import afkt.app.module.SearchContent
import afkt.app.module.TypeEnum
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {

    // 应用排序
    val appSort = MutableLiveData<Boolean>()

    // 导出设备、屏幕信息
    val exportInfo = MutableLiveData<TypeEnum>()

    // 搜索触发
    var search = MutableLiveData<SearchContent>()

    // 回到顶部
    val backTop = MutableLiveData<TypeEnum>()

    // Fragment 切换
    val fragmentChange = MutableLiveData<TypeEnum>()

    // 设备信息
    val deviceInfo = MutableLiveData<DeviceInfo>()

    // 屏幕信息
    val screenInfo = MutableLiveData<DeviceInfo>()

    // 用户、系统、全部 APP 类型
    var userApp = MutableLiveData<AppListBean>()
    var systemApp = MutableLiveData<AppListBean>()
    var allApp = MutableLiveData<AppListBean>()

    fun infoObserve(
        owner: LifecycleOwner,
        observer: Observer<DeviceInfo>
    ) {
        deviceInfo.observe(owner, observer)
        screenInfo.observe(owner, observer)
    }

    fun appObserve(
        owner: LifecycleOwner,
        observer: Observer<AppListBean>
    ) {
        userApp.observe(owner, observer)
        systemApp.observe(owner, observer)
        allApp.observe(owner, observer)
    }
}