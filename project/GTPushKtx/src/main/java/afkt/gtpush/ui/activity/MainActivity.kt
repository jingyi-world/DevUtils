package afkt.gtpush.ui.activity

import afkt.gtpush.R
import afkt.gtpush.base.BaseActivity
import afkt.gtpush.base.config.RouterPath
import afkt.gtpush.databinding.ActivityMainBinding
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = RouterPath.MainActivity_PATH)
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun baseLayoutId(): Int = R.layout.activity_main

    override fun initListener() {
        super.initListener()

        binding.vidAmOtherBtn.setOnClickListener {
            routerActivity(RouterPath.OtherActivity_PATH)
        }

        binding.vidAmDeviceBtn.setOnClickListener {
            routerActivity(RouterPath.DeviceActivity_PATH)
        }
    }
}