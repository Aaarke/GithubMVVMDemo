package com.roshi.ufabertask.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.roshi.ufabertask.databinding.HomeFragmentBinding
import com.roshi.ufabertask.model.GitData

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var repoAdapter: RepoAdapter
    private lateinit var binding:HomeFragmentBinding
    private var listener: OnHomeFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnHomeFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        setSmsAdapter()
        setAllObserver()

    }

    private fun setAllObserver() {
        viewModel.getResponse().observe(viewLifecycleOwner, {
            setValue(it.data)
        })
    }

    private fun setValue(listRepo: List<GitData>?) {
        repoAdapter.submitList(listRepo)
    }


    /**
     * ******************************** Function used to set repo adapter ***********************
     */
    private fun setSmsAdapter() {
        repoAdapter = RepoAdapter(layoutInflater,object :OnItemClickedListeners{


            override fun onItemClicked(gitData: GitData, position: Int, imageView: ImageView) {
                navigateToRepoDetail(gitData,position,imageView)

            }
        })
        binding.rvRepository.adapter = repoAdapter
        binding.rvRepository.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun navigateToRepoDetail(gitData: GitData, position: Int, imageView: ImageView) {
        listener?.onFragmentInteraction(position,gitData,imageView)
    }


    interface OnHomeFragmentInteractionListener {
        fun onFragmentInteraction(position: Int, item: GitData, imageView: ImageView)
    }

}