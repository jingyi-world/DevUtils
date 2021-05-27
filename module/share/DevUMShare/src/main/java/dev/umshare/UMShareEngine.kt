package dev.umshare

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import com.umeng.socialize.UMShareAPI
import dev.engine.share.IShareEngine
import dev.engine.share.listener.ShareListener
import dev.module.share.ShareConfig
import dev.module.share.ShareParams

/**
 * detail: 友盟分享 Engine 实现
 * @author Ttt
 */
class UMShareEngine : IShareEngine<ShareConfig, ShareParams> {

    override fun initialize(
        application: Application?,
        config: ShareConfig?
    ) {
        TODO("Not yet implemented")
    }

    override fun openMinApp(
        activity: Activity?,
        params: ShareParams?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun shareMinApp(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun shareUrl(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun shareImage(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun shareVideo(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun shareMusic(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun shareEmoji(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun shareText(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun shareFile(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun share(
        activity: Activity?,
        params: ShareParams?,
        listener: ShareListener<ShareParams>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onActivityResult(
        context: Context?,
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        context?.let {
            UMShareAPI.get(context).onActivityResult(
                requestCode, resultCode, data
            )
        }
    }
}