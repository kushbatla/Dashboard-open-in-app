package com.kushbatla.dashboard.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kushbatla.dashboard.databinding.FragmentTopLinksBinding
import com.kushbatla.dashboard.repository.Repository
import com.kushbatla.dashboard.ui.MainViewModel
import com.kushbatla.dashboard.ui.MainViewModelFactory
import com.kushbatla.dashboard.ui.adapters.TopLinksRvAdapter


class TopLinksFragment : Fragment() {

    private lateinit var binding : FragmentTopLinksBinding
    private lateinit var topLinksRvAdapter: TopLinksRvAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentTopLinksBinding.inflate(inflater, container,false)

        topLinksRvAdapter = TopLinksRvAdapter(requireContext())

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getTopLink()
        viewModel.topLinkResponse.observe(viewLifecycleOwner){ topLinks->
            setupRecyclerView()
            topLinksRvAdapter.topLinks = topLinks

        }

        /*lifecycleScope.launchWhenCreated {
            binding.toplinksRvPb.isVisible = true
            val response = try {
                RetrofitInstance.api.getDashboard()
            }catch (e : IOException){
                binding.toplinksRvPb.isVisible = false
                Log.d("Error", "IO EXCEPTION"+e)
                return@launchWhenCreated
            }catch (e: HttpException){
                binding.toplinksRvPb.isVisible = false
                Log.d("Error", "Http Exception"+ e)
                return@launchWhenCreated
            }


            if(response.isSuccessful && response.body()!=null){
              topLinksRvAdapter.topLinks = response.body()!!.data.top_links
                setupRecyclerView()
                Log.d("Success", response.body()!!.data.top_links.toString())

            }else {
                Log.d("Error", "Response not successful")
            }

            binding.toplinksRvPb.isVisible = false
        }*/
    }

    private fun setupRecyclerView() = binding.toplinksRv.apply {
        adapter = topLinksRvAdapter
        layoutManager = LinearLayoutManager(context)
    }
}