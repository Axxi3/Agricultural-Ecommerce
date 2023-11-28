package com.example.agrishop.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.agrishop.Data.Product
import com.example.agrishop.databinding.ProductRvItemBinding

class BestProductAdapter:RecyclerView.Adapter<BestProductAdapter.BestProductViewHolder>(){
    inner class BestProductViewHolder (private val binding:ProductRvItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product:Product){
            binding.apply {
                Glide.with(itemView).load(product.img).into(imgProduct)

                tvName.text=product.product_name
                tvPrice.text=product.prices.values.first().toString()+"-"+product.prices.values.last().toString()
                Log.d("bestProductAdapter", "bind: " +product.prices)
            }


        }
    }

    private val diffCallback=object :DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }
    }

    val differ=AsyncListDiffer(this,diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductViewHolder {
        return BestProductViewHolder(
            ProductRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: BestProductViewHolder, position: Int) {
        val product=differ.currentList[position]
        holder.bind(product)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}