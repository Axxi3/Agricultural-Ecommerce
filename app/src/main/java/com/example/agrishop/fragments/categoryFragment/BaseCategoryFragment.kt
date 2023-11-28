package com.example.agrishop.fragments.categoryFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agrishop.R
import com.example.agrishop.adapter.BestProductAdapter
import com.example.agrishop.databinding.FragmentBaseCategoryBinding

open class BaseCategoryFragment:Fragment(R.layout.fragment_base_category) {
    private lateinit var binding:FragmentBaseCategoryBinding
  private lateinit var offerAdapter: BestProductAdapter
  private lateinit var bestProductsAdapter: BestProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentBaseCategoryBinding.inflate(inflater)
        return binding.root
        setupOfferRv()
        setupBestProduct()
    }

    private fun setupBestProduct() {
        bestProductsAdapter= BestProductAdapter()
        binding.recycker3.apply {
            layoutManager= GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false)
            adapter=bestProductsAdapter
        }
    }

    private fun setupOfferRv() {
        offerAdapter= BestProductAdapter()
        binding.recycker3.apply {
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter=offerAdapter
        }
    }
}