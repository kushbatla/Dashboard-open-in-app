package com.kushbatla.dashboard.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kushbatla.dashboard.databinding.FragmentRecentLinksBinding
import com.kushbatla.dashboard.repository.Repository
import com.kushbatla.dashboard.ui.MainViewModel
import com.kushbatla.dashboard.ui.MainViewModelFactory
import com.kushbatla.dashboard.ui.adapters.RecentLinksRvAdapter


class RecentLinksFragment : Fragment() {


    private lateinit var binding: FragmentRecentLinksBinding
    private lateinit var recentLinksRvAdapter: RecentLinksRvAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecentLinksBinding.inflate(inflater,container,false)

        recentLinksRvAdapter = RecentLinksRvAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getRecentLink()
        viewModel.recentLinkResponse.observe(viewLifecycleOwner) { recentLinks->
        setupRecyclerView()
        recentLinksRvAdapter.recentLinks = recentLinks

        }

       /* lifecycleScope.launchWhenCreated {
            binding.recentlinksRvPb.isVisible = true
            val response = try {
                RetrofitInstance.api.getDashboard()
            }catch (e : IOException){
                binding.recentlinksRvPb.isVisible = false
                Log.d("Error", "IO EXCEPTION"+e)
                return@launchWhenCreated
            }catch (e: HttpException){
                binding.recentlinksRvPb.isVisible = false
                Log.d("Error", "Http Exception"+ e)
                return@launchWhenCreated
            }


            if(response.isSuccessful && response.body()!=null){
                recentLinksRvAdapter.recentLinks = response.body()!!.data.recent_links
                setupRecyclerView()
                Log.d("Success", response.body()!!.data.recent_links.toString())

            }else {
                Log.d("Error", "Response not successful")
            }

            binding.recentlinksRvPb.isVisible = false
        }*/
    }

    private fun setupRecyclerView() = binding.recentlinksRv.apply {
        adapter = recentLinksRvAdapter
        layoutManager = LinearLayoutManager(context)
    }
}