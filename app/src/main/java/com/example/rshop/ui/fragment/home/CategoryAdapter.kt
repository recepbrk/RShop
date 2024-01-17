package com.example.rshop.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rshop.databinding.CategoryItemBinding


class CategoryAdapter(private val imageList: ArrayList<Image>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Image) {
            binding.apply {
                binding.imageview.setImageResource(product.image)
                binding.root.setOnClickListener {
                    onItemClickListener?.let {
                        it(product)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val category=imageList[position]
       holder.bind(category)

    }

    private var onItemClickListener: ((Image) -> Unit)? = null

    fun setOnItemClickListener(listener: (Image) -> Unit) {
        onItemClickListener = listener
    }
}

data class Image(val image: Int)