package id.ac.unhas.infocovid19.ui.provinsi

import androidx.lifecycle.ViewModel
import id.ac.unhas.infocovid19.model.Provinsi

class ProvinsiViewModel(
    private val repository: ProvinsiRepository
) : ViewModel() {
    private var _provinsi = ArrayList<Provinsi>()
    val movies:ArrayList<Provinsi>
        get() = _provinsi

    fun getMoviesFromRepo(){
        //_movies.clear()
        _provinsi.apply {
            if(isEmpty()){
                addAll(repository.getProvinsiFromJsonString())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        _provinsi.clear()
    }
}
