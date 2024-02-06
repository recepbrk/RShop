package com.example.rshop.ui.fragment.basket


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rshop.data.source.local.basket.BasketEntity
import com.example.rshop.databinding.BasketListItemBinding

class BasketAdapter(private val basketViewModel: BasketViewModel) :
    RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {


    private val differCallBack = object : DiffUtil.ItemCallback<BasketEntity>() {
        override fun areItemsTheSame(oldItem: BasketEntity, newItem: BasketEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BasketEntity, newItem: BasketEntity): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)


    inner class BasketViewHolder(var binding: BasketListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: BasketEntity) {
            binding.apply {
                basketTitle.text = product.title
                basketPrice.text = "$" + product.price
                Glide.with(itemView).load(product.image).into(binding.basketImage)
                basketClose.setOnClickListener {
                   basketViewModel.deleteBasketProduct(product)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder(
            BasketListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(differ.currentList[position])


    }


}