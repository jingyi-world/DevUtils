package afkt_replace.core.lib.utils.engine.permission

import android.app.Activity
import android.content.Context
import dev.engine.DevEngine
import dev.engine.permission.IPermissionEngine

// =====================
// = IPermissionEngine =
// =====================

/**
 * 通过 Key 获取 Permission Engine
 * @param engine String?
 * @return IPermissionEngine
 * 内部做了处理如果匹配不到则返回默认 Permission Engine
 */
internal fun getEngine(engine: String?): IPermissionEngine {
    DevEngine.getPermission(engine)?.let { value ->
        return value
    }
    return DevEngine.getPermission()
}

// =========================
// = Key Permission Engine =
// =========================

fun Context.permission_isGranted(
    engine: String? = null,
    vararg permissions: String?
): Boolean {
    return getEngine(engine).isGranted(this, *permissions)
}

fun Activity.permission_shouldShowRequestPermissionRationale(
    engine: String? = null,
    vararg permissions: String?
): Boolean {
    return getEngine(engine).shouldShowRequestPermissionRationale(
        this, *permissions
    )
}

fun Activity.permission_getDeniedPermissionStatus(
    engine: String? = null,
    shouldShow: Boolean,
    vararg permissions: String?
): MutableList<String> {
    return getEngine(engine).getDeniedPermissionStatus(
        this, shouldShow, *permissions
    )
}

fun Activity.permission_againRequest(
    engine: String? = null,
    callback: IPermissionEngine.Callback?,
    deniedList: MutableList<String>?
): Int {
    return getEngine(engine).againRequest(this, callback, deniedList)
}

// =============
// = 权限请求方法 =
// =============

fun Activity.permission_request(
    engine: String? = null,
    permissions: Array<out String>?
) {
    return getEngine(engine).request(this, permissions)
}

fun Activity.permission_request(
    engine: String? = null,
    permissions: Array<out String>?,
    callback: IPermissionEngine.Callback?
) {
    return getEngine(engine).request(this, permissions, callback)
}

fun Activity.permission_request(
    engine: String? = null,
    permissions: Array<out String>?,
    callback: IPermissionEngine.Callback?,
    againRequest: Boolean
) {
    return getEngine(engine).request(this, permissions, callback, againRequest)
}