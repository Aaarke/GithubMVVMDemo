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
import com.roshi.ufabertask.network.Status
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var repoAdapter: RepoAdapter
    private lateinit var binding: HomeFragmentBinding
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
        pbHomeLoader.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.main.setOnRefreshListener {
            main.isRefreshing = false
        }
        setRepoAdapter()
        setAllObserver()

    }

    private fun setAllObserver() {
        viewModel.getResponse().observe(viewLifecycleOwner, {
            pbHomeLoader.visibility = View.GONE
            setValue(it.data)
        })
        viewModel.getNetworkStatus().observe(viewLifecycleOwner, {
            when (it) {
                Status.SUCCESS -> {
                    pbHomeLoader.visibility = View.GONE
                }
                Status.LOADING -> {
                    pbHomeLoader.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    pbHomeLoader.visibility = View.GONE
                }
                else -> {
                    pbHomeLoader.visibility = View.GONE

                }
            }
        })
    }

    private fun setValue(listRepo: List<GitData>?) {
        repoAdapter.submitList(listRepo?.subList(0, 20))
    }


    /**
     * ******************************** Function used to set repo adapter **************************
     */
    private fun setRepoAdapter() {
        repoAdapter = RepoAdapter(layoutInflater, object : OnItemClickedListeners {


            override fun onItemClicked(gitData: GitData, position: Int, imageView: ImageView) {
                navigateToRepoDetail(gitData, position, imageView)

            }
        })
        binding.rvRepository.adapter = repoAdapter
        binding.rvRepository.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun navigateToRepoDetail(gitData: GitData, position: Int, imageView: ImageView) {
        listener?.onFragmentInteraction(position, gitData, imageView)
    }


    interface OnHomeFragmentInteractionListener {
        fun onFragmentInteraction(position: Int, item: GitData, imageView: ImageView)
    }

}