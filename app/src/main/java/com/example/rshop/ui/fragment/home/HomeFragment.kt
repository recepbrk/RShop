package com.example.rshop.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rshop.ui.fragment.electronic.ElectronicFragment
import com.example.rshop.ui.fragment.jewelery.JeweleryFragment
import com.example.rshop.ui.fragment.man.ManClothesFragment
import com.example.rshop.ui.fragment.woman.WomanClothesFragment
import com.example.rshop.ui.fragment.product.AllProductFragment
import com.example.rshop.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentAdapter= FragmentAdapter(childFragmentManager)
        fragmentAdapter.addFragment(AllProductFragment(),"All")
        fragmentAdapter.addFragment(JeweleryFragment(),"Jewelery")
        fragmentAdapter.addFragment(ElectronicFragment(),"Electronic")
        fragmentAdapter.addFragment(ManClothesFragment(),"Man")
        fragmentAdapter.addFragment(WomanClothesFragment(),"Woman")
        binding.viewPager.adapter=fragmentAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}