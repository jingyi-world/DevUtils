package afkt.umshare.ui.activity

import afkt.umshare.R
import afkt.umshare.base.BaseActivity
import afkt.umshare.base.config.RouterPath
import afkt.umshare.databinding.ActivityMainBinding
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import dev.engine.share.DevShareEngine
import dev.engine.share.listener.ShareListener
import dev.module.share.ShareParams

@Route(path = RouterPath.MainActivity_PATH)
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun baseLayoutId(): Int = R.layout.activity_main

    override fun initListener() {
        super.initListener()

        binding.vidAmOpenMinappBtn.setOnClickListener {
            // 打开小程序
            DevShareEngine.getEngine()?.openMinApp(
                this, null
            )
        }

        binding.vidAmShareMinappBtn.setOnClickListener {
            // 分享小程序
            DevShareEngine.getEngine()?.shareMinApp(
                this, null, null
            )
        }

        binding.vidAmShareUrlBtn.setOnClickListener {
            // 分享链接
            DevShareEngine.getEngine()?.shareUrl(
                this, null, null
            )
        }

        binding.vidAmShareImageBtn.setOnClickListener {
            // 分享图片
            DevShareEngine.getEngine()?.shareImage(
                this, null, null
            )
        }

        binding.vidAmShareVideoBtn.setOnClickListener {
            // 分享视频
            DevShareEngine.getEngine()?.shareVideo(
                this, null, null
            )
        }

        binding.vidAmShareMusicBtn.setOnClickListener {
            // 分享音乐
            DevShareEngine.getEngine()?.shareMusic(
                this, null, null
            )
        }

        binding.vidAmShareEmojiBtn.setOnClickListener {
            // 分享表情
            DevShareEngine.getEngine()?.shareEmoji(
                this, null, null
            )
        }

        binding.vidAmShareTextBtn.setOnClickListener {
            // 分享文本
            DevShareEngine.getEngine()?.shareText(
                this, null, null
            )
        }

        binding.vidAmShareFileBtn.setOnClickListener {
            // 分享文件
            DevShareEngine.getEngine()?.shareFile(
                this, null, null
            )
        }

        binding.vidAmShareBtn.setOnClickListener {
            // 分享操作
            DevShareEngine.getEngine()?.shareFile(
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

        DevShareEngine.getEngine()?.onActivityResult(
            this, requestCode, resultCode, data
        )
    }
}