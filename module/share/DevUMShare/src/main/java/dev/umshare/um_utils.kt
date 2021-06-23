package dev.umshare

import android.app.Activity
import android.content.Intent
import com.umeng.socialize.UMShareAPI

fun shareActivityResult(
    activity: Activity?,
    requestCode: Int,
    resultCode: Int,
    data: Intent?
) {
    activity?.let {
        UMShareAPI.get(it).onActivityResult(requestCode, resultCode, data)
    }
}