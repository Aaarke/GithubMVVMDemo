package com.roshi.ufabertask.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.roshi.ufabertask.R
import com.roshi.ufabertask.model.GitData

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        setAllObserver()

    }

    private fun setAllObserver() {
        viewModel.getResponse().observe(viewLifecycleOwner, Observer {
            setValue(it.data)
        })
    }

    private fun setValue(listRepo: List<GitData>?) {
        Log.d("List of values",listRepo.toString())
    }

}