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

private var onPlusClick: ((BasketEntity) -> Unit)? = null
private var onMinusClick: ((BasketEntity) -> Unit)? = null
private var onDeleteClick: ((BasketEntity) -> Unit)? = null



class BasketAdapter() :
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
        @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
        fun bind(product: BasketEntity) {
            binding.apply {
                basketTitle.text = product.title
                Glide.with(itemView).load(product.image).into(binding.basketImage)
                basketNumber.text = product.quantity.toString()
                basketPrice.text = product.price.toString()

                val total_price =
                    basketNumber.text.toString().toDouble() * basketPrice.text.toString().toDouble()
                basketPrice.text = "$" + total_price.toString().toDouble()

              //  totalBasketPrice += basketPrice.toString().toDouble()




                basketPlus.setOnClickListener {
                    onPlusClick?.invoke(product)
                }
                basketMinus.setOnClickListener {
                    onMinusClick?.invoke(product)
                }
                basketDelete.setOnClickListener {
                    onDeleteClick?.invoke(product)
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

    fun setOnPlusClickListener(listener: (BasketEntity) -> Unit) {
        onPlusClick = listener
    }

    fun setOnMinusClickListener(listener: (BasketEntity) -> Unit) {
        onMinusClick = listener
    }

    fun setDeleteClickListener(listener: (BasketEntity) -> Unit) {
        onDeleteClick = listener
    }

}