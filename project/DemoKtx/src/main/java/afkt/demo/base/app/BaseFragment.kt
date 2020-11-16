package afkt.demo.base.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import dev.base.expand.viewdata.DevBaseViewDataBindingFragment

/**
 * detail: Fragment 基类
 * @author Ttt
 */
abstract class BaseFragment<VDB : ViewDataBinding> : DevBaseViewDataBindingFragment<VDB>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // 初始化顺序 ( 按顺序调用方法 )
        initOrder()
        return mContentView
    }

    override fun baseContentView(): View? {
        return null
    }
}