package afkt_replace.core.lib.utils.barcode

import android.graphics.Bitmap
import dev.engine.DevEngine
import dev.engine.barcode.IBarCodeEngine
import dev.engine.barcode.listener.BarCodeDecodeCallback
import dev.engine.barcode.listener.BarCodeEncodeCallback

// ==========================================================
// = IBarCodeEngine<EngineConfig, EngineItem, EngineResult> =
// ==========================================================

/**
 * 通过 Key 获取 BarCode Engine
 * @param engine String?
 * @return IBarCodeEngine<EngineConfig, EngineItem, EngineResult>
 * 内部做了处理如果匹配不到则返回默认 BarCode Engine
 */
internal fun getEngine(engine: String?): IBarCodeEngine<
        in IBarCodeEngine.EngineConfig,
        in IBarCodeEngine.EngineItem,
        in IBarCodeEngine.EngineResult>? {
    DevEngine.getBarCode(engine)?.let { value ->
        return value
    }
    return DevEngine.getBarCode()
}

// ======================
// = Key BarCode Engine =
// ======================

// =============
// = 对外公开方法 =
// =============

fun <Config : IBarCodeEngine.EngineConfig> barcode_initialize(
    engine: String? = null,
    config: Config?
) {
    getEngine(engine)?.initialize(config)
}

fun <Config : IBarCodeEngine.EngineConfig> barcode_getConfig(
    engine: String? = null
): Config? {
    return getEngine(engine)?.config as? Config
}

// ==========
// = 生成条码 =
// ==========

fun <Item : IBarCodeEngine.EngineItem> barcode_encodeBarCode(
    engine: String? = null,
    params: Item?,
    callback: BarCodeEncodeCallback?
) {
    getEngine(engine)?.encodeBarCode(params, callback)
}

@Throws(Exception::class)
fun <Item : IBarCodeEngine.EngineItem> barcode_encodeBarCodeSync(
    engine: String? = null,
    params: Item?
): Bitmap? {
    return getEngine(engine)?.encodeBarCodeSync(params)
}

// ==========
// = 解析条码 =
// ==========

fun <Result : IBarCodeEngine.EngineResult> barcode_decodeBarCode(
    engine: String? = null,
    bitmap: Bitmap?,
    callback: BarCodeDecodeCallback<Result>?
) {
    getEngine(engine)?.let {
        (it as? IBarCodeEngine<*, *, Result>)?.decodeBarCode(
            bitmap, callback
        )
    }
}

@Throws(Exception::class)
fun <Result : IBarCodeEngine.EngineResult> barcode_decodeBarCodeSync(
    engine: String? = null,
    bitmap: Bitmap?
): Result? {
    return getEngine(engine)?.decodeBarCodeSync(bitmap) as? Result
}

@Throws(Exception::class)
fun <Item : IBarCodeEngine.EngineItem> barcode_addIconToBarCode(
    engine: String? = null,
    params: Item?,
    src: Bitmap?,
    icon: Bitmap?
): Bitmap? {
    return getEngine(engine)?.addIconToBarCode(params, src, icon)
}