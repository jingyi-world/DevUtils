package afkt_replace.core.lib.utils.storage

import dev.engine.DevEngine
import dev.engine.storage.IStorageEngine

// ============================================
// = IStorageEngine<EngineItem, EngineResult> =
// ============================================

/**
 * 通过 Key 获取 Storage Engine
 * @param engine String?
 * @return IStorageEngine<EngineItem, EngineResult>
 * 内部做了处理如果匹配不到则返回默认 Storage Engine
 */
internal fun getEngine(engine: String?): IStorageEngine<in IStorageEngine.EngineItem, in IStorageEngine.EngineResult>? {
    DevEngine.getStorage(engine)?.let { value ->
        return value
    }
    return DevEngine.getStorage()
}

// ======================
// = Key Storage Engine =
// ======================