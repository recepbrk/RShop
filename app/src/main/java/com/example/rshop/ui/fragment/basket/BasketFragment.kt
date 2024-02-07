package com.example.rshop.ui.fragment.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rshop.databinding.FragmentBasketBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify

@AndroidEntryPoint
class BasketFragment : Fragment() {
    private lateinit var binding:FragmentBasketBinding
    private val basketViewModel:BasketViewModel by viewModels()
    lateinit var basketAdapter:BasketAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentBasketBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createRecyclerView()
        observeData()

    }

    private fun observeData() {
        basketViewModel.getBasketList().observe(viewLifecycleOwner){ basketList ->
            basketAdapter.differ.submitList(basketList)

            basketAdapter.setDeleteClickListener {
                basketViewModel.deleteBasketProduct(it)

            }
            basketAdapter.setOnPlusClickListener { selectedItem ->
            basketViewModel.increaseBasketProductQuantity(selectedItem)
            }
            basketAdapter.setOnMinusClickListener { selectedItem ->
                basketViewModel.decreaseBasketProductQuantity(selectedItem)

            }
        //   binding.totalPrice.text= basketAdapter.getTotalBasketPrice().toString()

        }
    }

    private fun createRecyclerView() {
        basketAdapter = BasketAdapter()
        binding.basketRecyclerView.adapter = basketAdapter


    }

}