package com.vall.kisahnabi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.vall.kisahnabi.R
import com.vall.kisahnabi.adapter.NabiRasulAdapter
import com.vall.kisahnabi.databinding.FragmentNabiBinding
import com.vall.kisahnabi.model.ResponseNabiRasulItem
import com.vall.kisahnabi.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NabiFragment : Fragment() {

    private lateinit var binding: FragmentNabiBinding
    private lateinit var adapters: NabiRasulAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNabiBinding.inflate(inflater, container, false)

        adapters = NabiRasulAdapter()
        binding.rvNabi.apply {
            adapter = adapters
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        val call = RetrofitService.getService().getDataNabi()

        call.enqueue(object : Callback<List<ResponseNabiRasulItem>> {
            override fun onResponse(
                call: Call<List<ResponseNabiRasulItem>>,
                response: Response<List<ResponseNabiRasulItem>>
            ) {
                val list = response.body()!!
                list.let { it.let { it1 -> adapters.addData(it1) } }
            }

            override fun onFailure(call: Call<List<ResponseNabiRasulItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return binding.root
    }
}