package afkt_replace.core.lib.utils.barcode

import dev.engine.DevEngine
import dev.engine.analytics.IAnalyticsEngine

// ================================================================================
// = IAnalyticsEngine<IAnalyticsEngine.EngineConfig, IAnalyticsEngine.EngineItem> =
// ================================================================================

/**
 * 通过 Key 获取 Analytics Engine
 * @param engine String?
 * @return IAnalyticsEngine<IAnalyticsEngine.EngineConfig, IAnalyticsEngine.EngineItem>
 * 内部做了处理如果匹配不到则返回默认 Analytics Engine
 */
internal fun getEngine(engine: String?): IAnalyticsEngine<in IAnalyticsEngine.EngineConfig, in IAnalyticsEngine.EngineItem>? {
    DevEngine.getAnalytics(engine)?.let { value ->
        return value
    }
    return DevEngine.getAnalytics()
}

// ========================
// = Key Analytics Engine =
// ========================