package afkt.demo.ui.fragment

import afkt.demo.R
import afkt.demo.base.app.BaseFragment
import afkt.demo.databinding.FragmentVdbBinding
import dev.utils.app.ResourceUtils
import dev.utils.common.ChineseUtils

class VDBFragment : BaseFragment<FragmentVdbBinding>() {

    override fun baseContentId(): Int {
        return R.layout.fragment_vdb
    }

    override fun initValue() {
        super.initValue()

        // 设置图片
        binding.image = ResourceUtils.getDrawable(R.mipmap.icon_launcher_round)
        // 设置标题
        binding.title = ChineseUtils.randomWord(5)
    }
}