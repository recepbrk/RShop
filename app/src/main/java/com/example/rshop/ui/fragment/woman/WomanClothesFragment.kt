package com.example.rshop.ui.fragment.woman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rnote.adapter.ProductAdapter
import com.example.rshop.data.model.ProductModel
import com.example.rshop.databinding.FragmentWomanClothesBinding
import com.example.rshop.util.resource.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WomanClothesFragment : Fragment() {
    private lateinit var binding: FragmentWomanClothesBinding
    private val womanViewModel: WomanClothesViewModel by viewModels()
    lateinit var womanAdapter: ProductAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWomanClothesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        womanViewModel.getWomanClothes("women\'s clothing")
        initObserve()
    }

    private fun initObserve() {
        womanViewModel.getWomanList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        initAdapter(it)
                    }
                    binding.progressBar.visibility = View.GONE
                }

                is Resource.Error -> {
                    Toast.makeText(context, "Hata var !!", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                else -> {}
            }
        }
    }

    private fun initAdapter(list: List<ProductModel>) {
        womanAdapter = ProductAdapter()
        binding.recyclerViewWoman.adapter = womanAdapter
        binding.recyclerViewWoman.setHasFixedSize(true)
        womanAdapter.differ.submitList(list)
    }

}
