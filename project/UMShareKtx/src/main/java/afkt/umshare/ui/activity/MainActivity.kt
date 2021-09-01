package afkt.umshare.ui.activity

import afkt.umshare.R
import afkt.umshare.base.BaseActivity
import afkt.umshare.base.config.RouterPath
import afkt.umshare.databinding.ActivityMainBinding
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import dev.engine.DevEngine
import dev.engine.share.listener.ShareListener
import dev.module.share.ShareParams

@Route(path = RouterPath.MainActivity_PATH)
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun baseLayoutId(): Int = R.layout.activity_main

    override fun initListener() {
        super.initListener()

        binding.vidAmOpenMinappBtn.setOnClickListener {
            // 打开小程序
            DevEngine.getShare()?.openMinApp(
                this, null
            )
        }

        binding.vidAmShareMinappBtn.setOnClickListener {
            // 分享小程序
            DevEngine.getShare()?.shareMinApp(
                this, null, null
            )
        }

        binding.vidAmShareUrlBtn.setOnClickListener {
            // 分享链接
            DevEngine.getShare()?.shareUrl(
                this, null, null
            )
        }

        binding.vidAmShareImageBtn.setOnClickListener {
            // 分享图片
            DevEngine.getShare()?.shareImage(
                this, null, null
            )
        }

        binding.vidAmShareVideoBtn.setOnClickListener {
            // 分享视频
            DevEngine.getShare()?.shareVideo(
                this, null, null
            )
        }

        binding.vidAmShareMusicBtn.setOnClickListener {
            // 分享音乐
            DevEngine.getShare()?.shareMusic(
                this, null, null
            )
        }

        binding.vidAmShareEmojiBtn.setOnClickListener {
            // 分享表情
            DevEngine.getShare()?.shareEmoji(
                this, null, null
            )
        }

        binding.vidAmShareTextBtn.setOnClickListener {
            // 分享文本
            DevEngine.getShare()?.shareText(
                this, null, null
            )
        }

        binding.vidAmShareFileBtn.setOnClickListener {
            // 分享文件
            DevEngine.getShare()?.shareFile(
                this, null, null
            )
        }

        binding.vidAmShareBtn.setOnClickListener {
            // 分享操作
            DevEngine.getShare()?.shareFile(
                this, null, object : ShareListener<ShareParams> {
                    override fun onStart(params: ShareParams?) {
                    }

                    override fun onResult(params: ShareParams?) {
                    }

                    override fun onError(
                        params: ShareParams?,
                        throwable: Throwable?
                    ) {
                    }

                    override fun onCancel(params: ShareParams?) {
                    }
                }
            )
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        DevEngine.getShare()?.onActivityResult(
            this, requestCode, resultCode, data
        )
    }
}