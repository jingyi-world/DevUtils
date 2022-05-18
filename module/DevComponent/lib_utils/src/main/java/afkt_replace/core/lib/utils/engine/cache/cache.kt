package afkt_replace.core.lib.utils.engine.cache

import dev.engine.DevEngine
import dev.engine.cache.ICacheEngine

// ==========================================
// = ICacheEngine<EngineConfig, EngineItem> =
// ==========================================

/**
 * 通过 Key 获取 Cache Engine
 * @param engine String?
 * @return ICacheEngine<EngineConfig, EngineItem>
 * 内部做了处理如果匹配不到则返回默认 Cache Engine
 */
internal fun getEngine(engine: String?): ICacheEngine<in ICacheEngine.EngineConfig, in ICacheEngine.EngineItem>? {
    DevEngine.getCache(engine)?.let { value ->
        return value
    }
    return DevEngine.getCache()
}

// ====================
// = Key Cache Engine =
// ====================