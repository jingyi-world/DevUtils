package afkt.demo.use.datastore

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import dev.other.DataStoreUtils
import dev.utils.app.logger.DevLogger
import dev.utils.app.share.SPUtils
import dev.utils.app.share.SharedUtils
import kotlinx.coroutines.flow.collect

object DataStoreUse {

    val TAG: String = DataStoreUse::class.java.simpleName

    private const val spStoreName = "spStore"

    suspend fun use(activity: AppCompatActivity) {
        // 监听数据变化
        listener(activity)
        // 写入数据
        write(activity)
        // 读取数据
        read(activity)
    }

    /**
     * 写入数据
     * @param context [Context]
     */
    private suspend fun write(context: Context) {
        // 首先进行 SP 数据存储存储
        // 会在 /data/data/afkt.demo/shared_prefs/ 创建 **.xml
        // 接着运行 migrationSPToDataStore 则会把 shared_prefs 文件夹内的 xml
        // 存储到 /data/data/afkt.demo/files/datastore/ 中指定的 **.preferences_pb
        // 并且会把 shared_prefs 下迁移成功的文件进行删除

        // name: SPConfig
        SharedUtils.put("userName", "u123456")

        SharedUtils.put("tips", "没有密码")

        SPUtils.getPreference(context, "AA").put("type", "AA")

        SPUtils.getPreference(context, "BB").put("typeB", "BB")

        SPUtils.getPreference(context, "CC").put("typeC", "CC")

        SPUtils.getPreference(context, "DD").put("type", "DD")

        SPUtils.getPreference(context, "DD").put("abc", "abc")

        // 打印数据
        printSPData(context)

        var dataStore = DataStoreUtils.migrationSPToDataStore(
            context, spStoreName,
            "AA", "BB", "CC", "DD"
        )

        dataStore.put("value", 1)

        DataStoreUtils.get(context, spStoreName).put("key", "key Value")

        // =============
        // = storeName =
        // =============

        val storeName = TAG

        DataStoreUtils.get(context, storeName).put("int", 9)

        DataStoreUtils.get(context, storeName).put("String", "xx")

        DataStoreUtils.get(context, storeName).put("boolean", true)

        DataStoreUtils.get(context, storeName).put("float", 0.48791F)

        DataStoreUtils.get(context, storeName).put("long", 555L)

        DataStoreUtils.get(context, storeName).put("double", 1.2312)
    }

    /**
     * 读取数据
     * @param activity [Activity]
     */
    private suspend fun read(activity: AppCompatActivity) {
        DataStoreUtils.get(activity, TAG).getString("aaaaa", "不存在该 key 返回指定值")
            ?.collect { value ->
                DevLogger.dTag(
                    TAG, "get %s DataStore, key : %s, value : %s",
                    TAG, "aaaaa", value
                )
            }
    }

    /**
     * 监听数据变化
     * @param activity [Activity]
     */
    private fun listener(activity: AppCompatActivity) {
        /**
         * 监听 [TAG] DataStore key "int" 值变化
         */
        DataStoreUtils.get(activity, TAG).getInt("int")?.let {
            it.asLiveData().observe(activity) { value ->
                DevLogger.dTag(
                    TAG, "listener %s DataStore, key : %s, value : %s",
                    TAG, "int", value
                )
            }
        }
        /**
         * 监听 [spStoreName] DataStore key "userName" 值变化
         */
        DataStoreUtils.get(activity, spStoreName).getString("type")?.let {
            it.asLiveData().observe(activity) { value ->
                DevLogger.dTag(
                    TAG, "listener %s DataStore, key : %s, value : %s",
                    spStoreName, "type", value
                )
            }
        }
    }

    /**
     * 打印 SharedPreferences 存储数据
     * @param context [Context]
     */
    private fun printSPData(context: Context) {
        var builder = StringBuilder()
            .append("SharedPreferences")
            .append("\n\nSPConfig.xml Data")
            .append("\nuserName:")
            .append(SharedUtils.getString("userName"))
            .append("\ntips:")
            .append(SharedUtils.getString("tips"))
            .append("\n\nAA.xml Data")
            .append("\ntype:")
            .append(SPUtils.getPreference(context, "AA").getString("type"))
            .append("\n\nBB.xml Data")
            .append("\ntypeB:")
            .append(SPUtils.getPreference(context, "BB").getString("typeB"))
            .append("\n\nCC.xml Data")
            .append("\ntypeC:")
            .append(SPUtils.getPreference(context, "CC").getString("typeC"))
            .append("\n\nDD.xml Data")
            .append("\ntype:")
            .append(SPUtils.getPreference(context, "DD").getString("type"))
            .append("\nabc:")
            .append(SPUtils.getPreference(context, "DD").getString("abc"))
        DevLogger.dTag(TAG, builder.toString())
    }
}
