package com.example.agrishop.fragments.categoryFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agrishop.R
import com.example.agrishop.Util.Rsource
import com.example.agrishop.Viewmodel.MainCategoryViewModel
import com.example.agrishop.adapter.BestProductAdapter
import com.example.agrishop.adapter.SpecialProductAdapter
import com.example.agrishop.databinding.FragmentMainCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
private val TAG="MainCAtegory"
@AndroidEntryPoint
class MainCategory:Fragment(R.layout.fragment_main_category) {
    private lateinit var binding:FragmentMainCategoryBinding
    private lateinit var specialProductAdapter: SpecialProductAdapter
    private val viewModel by viewModels<MainCategoryViewModel>()
    private lateinit var bestProductsAdapter:BestProductAdapter
    private var i=false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMainCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpecialProductsRv()
        setupBestProductsRv()
        lifecycleScope.launchWhenStarted {
            viewModel.specialProducts.collectLatest{
                when(it){
                    is Rsource.Loading->{
                        showLoading()
                    }
                    is Rsource.Success->{
                        specialProductAdapter.differ.submitList(it.data)
                        hideLoading()
                    }
                    is Rsource.Error->{
                        hideLoading()
                        Log.e(TAG, it.message.toString() )
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.bestProducts.collectLatest{
                Log.d(TAG, "onViewCreated: " + it.message)

                Log.d(TAG, "onViewCreated: " + i)
                when(it){
                    is Rsource.Loading->{
                        showLoading()
                        if(i){
                            showBottomLoading()
                        }

                    }
                    is Rsource.Success->{
                        bestProductsAdapter.differ.submitList(it.data)
                        hideLoading()
                        i=true
                        hideBottomLoading()
                    }
                    is Rsource.Error->{
                        hideLoading()
                        Log.e(TAG, it.message.toString() )
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

        binding.scrollable.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener{ v,_,scrollY,_,_->
            if(v.getChildAt(0).bottom <= v.height+scrollY){
                viewModel.BestProduct()
                if(i){
                    showBottomLoading()
                }
            }

        })

    }

    private fun showBottomLoading() {
        binding.pagingLoader.visibility=View.VISIBLE
    }
    private fun hideBottomLoading() {
        binding.pagingLoader.visibility=View.GONE
    }


    private fun setupBestProductsRv() {
        bestProductsAdapter= BestProductAdapter()
        binding.recycker3.apply {
            layoutManager=GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
            adapter=bestProductsAdapter
        }
    }

    private fun hideLoading() {
        binding.Loader.visibility=View.GONE
        binding.bestProducts.visibility=View.VISIBLE
    }

    private fun showLoading() {
        binding.bestProducts.visibility=View.GONE
        binding.Loader.visibility=View.VISIBLE
    }

    private fun setUpSpecialProductsRv() {
        specialProductAdapter= SpecialProductAdapter()
        binding.recycker.apply {
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter=specialProductAdapter
        }
    }

}