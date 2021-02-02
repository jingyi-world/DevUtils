package afkt.lock.ui.activity

import afkt.lock.R
import afkt.lock.base.BaseActivity
import afkt.lock.databinding.ActivityMainBinding
import android.content.Intent
import dev.utils.app.AppUtils

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun baseLayoutId(): Int = R.layout.activity_main

    override fun initListener() {
        super.initListener()

        binding.vidAmOtherBtn.setOnClickListener {
            AppUtils.startActivity(Intent(this, OtherActivity::class.java))
        }
    }
}