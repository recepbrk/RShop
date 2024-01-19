package com.example.rshop.ui.fragment.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rnote.adapter.ProductAdapter
import com.example.rshop.data.model.ProductModel
import com.example.rshop.databinding.FragmentAllProductBinding
import com.example.rshop.util.resource.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllProductFragment : Fragment() {
    private lateinit var binding: FragmentAllProductBinding
    private val allProductViewModel: AllProductViewModel by viewModels()
    lateinit var productAdapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
        backButton()
    }

    private fun initObserve() {
        allProductViewModel.getProductList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        initAdapter(it)
                    }
                    binding.progressBar.visibility = View.GONE
                    productAdapter.setOnItemClickListener {
                        val action =
                            AllProductFragmentDirections.actionAllProductFragmentToProductDetailsFragment(
                                it
                            )
                        findNavController().navigate(action)
                    }

                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Toast.makeText(context, "Hata var !! ", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE

                }

                else -> {}
            }

        }
    }

    private fun initAdapter(list: List<ProductModel>) {
        productAdapter = ProductAdapter()
        binding.recyclerViewAll.adapter = productAdapter
        binding.recyclerViewAll.setHasFixedSize(true)
        productAdapter.differ.submitList(list)

    }

    private fun backButton() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }


    }
}
