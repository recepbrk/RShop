package com.example.rshop.ui.fragment.favorite



import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rshop.data.source.local.favorite.FavoriteEntity
import com.example.rshop.databinding.SaveListItemBinding

class FavoriteAdapter() : RecyclerView.Adapter<FavoriteAdapter.ProductViewHolder>() {


    private val differCallBack = object : DiffUtil.ItemCallback<FavoriteEntity>() {
        override fun areItemsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)


    inner class ProductViewHolder(var binding: SaveListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: FavoriteEntity) {
            binding.apply {
                saveTitle.text = product.title
                savePrice.text = "$" + product.price.toString()
                saveRatingbar.rating = product.rating.rate.toFloat()
                saveStar.text = product.rating.rate.toFloat().toString()
                Glide.with(itemView).load(product.image).into(binding.saveImage)
                deleteIcon.setOnClickListener {
                    onItemClickListener.let {
                        if (it != null) {
                            it(product)
                        }
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
    private var onItemClickListener: ((FavoriteEntity) -> Unit)? = null

    fun setOnItemClickListener(listener: (FavoriteEntity) -> Unit) {
        onItemClickListener = listener
    }

}