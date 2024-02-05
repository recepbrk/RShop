package com.example.rshop.ui.fragment.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import com.example.rshop.R
import com.example.rshop.databinding.FragmentBasketBinding
import dagger.hilt.android.AndroidEntryPoint

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
        basketViewModel.getBasketList().observe(viewLifecycleOwner){basketList ->
            basketAdapter.differ.submitList(basketList)

        }
    }

    private fun createRecyclerView() {
        basketAdapter = BasketAdapter(basketViewModel)
        binding.saveRecyclerView.adapter=basketAdapter
    }


}