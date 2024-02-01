package com.example.rshop.ui.fragment.favorite



import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rshop.data.model.ProductModel
import com.example.rshop.databinding.SaveListItemBinding

class FavoriteAdapter() : RecyclerView.Adapter<FavoriteAdapter.ProductViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem == newItem
        }

    }
    val differ=AsyncListDiffer(this,differCallBack)


    inner class ProductViewHolder(var binding: SaveListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: ProductModel) {
            binding.apply {
                saveTitle.text = product.title
                savePrice.text = "$" + product.price.toString()
                saveRatingbar.rating = product.rating.rate.toFloat()
                saveStar.text = product.rating.rate.toFloat().toString()
                Glide.with(itemView).load(product.image).into(binding.saveImage)
                binding.root.setOnClickListener {
                    onItemClickListener?.let {
                        it(product)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(SaveListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(differ.currentList[position])


    }

    private var onItemClickListener: ((ProductModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (ProductModel) -> Unit) {
        onItemClickListener = listener
    }
}