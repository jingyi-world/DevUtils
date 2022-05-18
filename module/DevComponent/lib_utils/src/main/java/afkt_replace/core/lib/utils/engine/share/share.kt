package afkt_replace.core.lib.utils.engine.share

import dev.engine.DevEngine
import dev.engine.share.IShareEngine

// ==========================================
// = IShareEngine<EngineConfig, EngineItem> =
// ==========================================

/**
 * 通过 Key 获取 Share Engine
 * @param engine String?
 * @return IShareEngine<EngineConfig, EngineItem>
 * 内部做了处理如果匹配不到则返回默认 Share Engine
 */
internal fun getEngine(engine: String?): IShareEngine<in IShareEngine.EngineConfig, in IShareEngine.EngineItem>? {
    DevEngine.getShare(engine)?.let { value ->
        return value
    }
    return DevEngine.getShare()
}

// ====================
// = Key Share Engine =
// ====================