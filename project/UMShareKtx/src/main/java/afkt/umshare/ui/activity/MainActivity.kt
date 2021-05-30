package afkt.umshare.ui.activity

import afkt.umshare.R
import afkt.umshare.base.BaseActivity
import afkt.umshare.base.config.RouterPath
import afkt.umshare.databinding.ActivityMainBinding
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