package afkt.app.module

/**
 * detail: 文件删除事件
 * @author Ttt
 */
class FileDeleteEvent

/**
 * detail: Fragment 切换事件
 * @author Ttt
 */
class FragmentEvent(val type: TypeEnum)

/**
 * detail: 刷新事件
 * @author Ttt
 */
class RefreshEvent(val type: TypeEnum)

/**
 * detail: 扫描 SDCard 事件
 * @author Ttt
 */
class ScanSDCardEvent(val lists: ArrayList<FileApkItem>)