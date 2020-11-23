package afkt.demo.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val result = MutableLiveData<Int>()

    init {
        result.value = 150
    }
}