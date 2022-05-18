package dev.kotlin.engine.media

import dev.engine.DevEngine
import dev.engine.media.IMediaEngine

// ==========================================
// = IMediaEngine<EngineConfig, EngineData> =
// ==========================================

/**
 * 通过 Key 获取 Media Engine
 * @param engine String?
 * @return IMediaEngine<EngineConfig, EngineData>
 * 内部做了处理如果匹配不到则返回默认 Media Engine
 */
internal fun getEngine(engine: String?): IMediaEngine<in IMediaEngine.EngineConfig, in IMediaEngine.EngineData>? {
    DevEngine.getMedia(engine)?.let { value ->
        return value
    }
    return DevEngine.getMedia()
}

// ====================
// = Key Media Engine =
// ====================