package com.example.rshop.ui.fragment.electronic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rnote.adapter.ProductAdapter
import com.example.rshop.R
import com.example.rshop.data.model.ProductModel
import com.example.rshop.databinding.FragmentElectronicBinding
import com.example.rshop.ui.fragment.product.AllProductFragmentDirections
import com.example.rshop.util.resource.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ElectronicFragment : Fragment() {
    private lateinit var binding: FragmentElectronicBinding
    private val electronicViewModel: ElectronicViewModel by viewModels()
    lateinit var electronicAdapter: ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentElectronicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        electronicViewModel.getElectronic("electronics")
        initObserve()
        backButton()
    }


    private fun initObserve() {
        electronicViewModel.getElectronicList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {

                        initAdapter(it)
                        electronicAdapter.setOnItemClickListener {
                            val action =
                                ElectronicFragmentDirections.actionElectronicFragmentToProductDetailsFragment(
                                    it
                                )
                            findNavController().navigate(action)
                        }

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
        electronicAdapter = ProductAdapter()
        binding.recyclerViewElectronic.adapter = electronicAdapter
        binding.recyclerViewElectronic.setHasFixedSize(true)
        electronicAdapter.differ.submitList(list)
    }

    private fun backButton() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }


    }
}