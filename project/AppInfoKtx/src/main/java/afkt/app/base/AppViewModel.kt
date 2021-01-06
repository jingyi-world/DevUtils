package afkt.app.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {

    val sort = MutableLiveData<Boolean>()
}