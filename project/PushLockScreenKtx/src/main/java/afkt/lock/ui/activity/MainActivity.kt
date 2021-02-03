package afkt.lock.ui.activity

import afkt.lock.R
import afkt.lock.base.BaseActivity
import afkt.lock.databinding.ActivityMainBinding
import afkt.lock.receiver.DeviceReceiver
import dev.utils.app.DevicePolicyUtils

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun baseLayoutId(): Int = R.layout.activity_main

    override fun initListener() {
        super.initListener()

        DevicePolicyUtils.getInstance()
            .setComponentName(DeviceReceiver::class.java)

        // 申请权限
        binding.vidAmApplyBtn.setOnClickListener {
            if (DevicePolicyUtils.getInstance().isAdminActive()) {
                return@setOnClickListener
            }
            DevicePolicyUtils.getInstance().activeAdmin("需开启权限允许对设备进行操作!")
        }
    }
}