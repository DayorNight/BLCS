package blcs.lwb.utils.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import blcs.lwb.utils.model.NetWorkRepository
import blcs.lwb.utils.utils.LoadState
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * 模板
 */
class CommonViewModel : ViewModel(){
    companion object{
        const val DEFAULT_PAGE = 1
    }

    private var mCurrentPage = DEFAULT_PAGE

    fun loadData(){
        // 初始状态
        loadState.value = LoadState.LOADING
        // 加载第一页数据
        loadDataByPage(mCurrentPage)

    }

    private val model by lazy {
        NetWorkRepository()
    }

    private fun loadDataByPage(mCurrentPage: Int) {
        try {
            viewModelScope.launch {
                val content = model.getContent(mCurrentPage)
                if (content.isEmpty()){
                    loadState.value = LoadState.EMPTY
                }else{
                    loadState.value = LoadState.SUCCESS
                    contentList.value = content
                }
            }
        }catch (e: Exception){
            loadState.value = LoadState.ERROR
        }
    }

    /**
     * 数据状态
     * 监听状态变化
     * viewModel.loadState.observe(this, Observer { newState->
     *     println("newState ---> $newState")
     *      //todo
     * })
     */
    val loadState by lazy {
        MutableLiveData<LoadState>()
    }

    /**
     * 数据
     * 监听数据变化
     * viewModel.contentList.observe(this, Observer { contentList->
     *     println("contentList ---> contentList")
     *      //todo
     * })
     */
    val contentList by lazy {
        MutableLiveData<MutableList<String>>()
    }
}