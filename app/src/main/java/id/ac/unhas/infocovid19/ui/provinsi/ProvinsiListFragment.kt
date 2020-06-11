package id.ac.unhas.infocovid19.ui.provinsi

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.unhas.infocovid19.BuildConfig
import id.ac.unhas.infocovid19.R
import id.ac.unhas.infocovid19.model.DataProvinsi
import id.ac.unhas.infocovid19.model.DataSource
import id.ac.unhas.infocovid19.model.Provinsi
import id.ac.unhas.infocovid19.network.ApiEndPoint
import kotlinx.android.synthetic.main.provinsilist_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvinsiListFragment : Fragment() {

    companion object {
        fun newInstance() = ProvinsiListFragment()
    }

    private lateinit var viewModel: ProvinsiViewModel
    private lateinit var viewModelFactory: ProvinsiViewModelFactory

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.provinsilist_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MainFragment","createView")

        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder.build()

        val apiEndPoint = retrofit.create(ApiEndPoint::class.java)

        apiEndPoint.getDataProvinsi().enqueue(object : Callback<DataProvinsi> {
            override fun onFailure(call: Call<DataProvinsi>, t: Throwable) {
                Log.e(this::class.java.simpleName, "Error: ${t.printStackTrace()}")
            }

            override fun onResponse(call: Call<DataProvinsi>, response: Response<DataProvinsi>) {
                val dataProvinsi = response.body()?.data
                linearLayoutManager = LinearLayoutManager(context)
                recyclerview.layoutManager = linearLayoutManager
                val adapter = ProvinsiAdapter(toArrayListOfStrings(dataProvinsi))
                recyclerview.adapter = adapter
                Log.d("Hasil parsing retrofit ", response.body().toString())
            }
        })
    }

    private fun toArrayListOfStrings(dataProvinsi: List<Provinsi?>?): ArrayList<Provinsi> {
        val listItems = ArrayList<Provinsi>(dataProvinsi?.size ?: 0)

        dataProvinsi?.forEach {
            if (it != null) {
                listItems.add(it)
            }
        }
        return listItems
    }
}
