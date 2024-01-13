package com.example.rshop.ui.fragment.jewelery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rnote.adapter.ProductAdapter
import com.example.rshop.data.model.ProductModel
import com.example.rshop.databinding.FragmentJeweleryBinding
import com.example.rshop.util.resource.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JeweleryFragment : Fragment() {
    private lateinit var binding: FragmentJeweleryBinding
    lateinit var jeweleryAdapter: ProductAdapter
    private val jeweleryViewModel: JeweleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJeweleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jeweleryViewModel.getJewelery("jewelery")
        initObserve()
    }

    private fun initObserve() {
        jeweleryViewModel.getJeweleryList.observe(viewLifecycleOwner) { response ->

            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        initadapter(it)
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

    private fun initadapter(list: List<ProductModel>) {
        jeweleryAdapter = ProductAdapter()
        binding.recyclerViewJewelery.adapter = jeweleryAdapter
        binding.recyclerViewJewelery.setHasFixedSize(true)
        jeweleryAdapter.differ.submitList(list)
    }


}