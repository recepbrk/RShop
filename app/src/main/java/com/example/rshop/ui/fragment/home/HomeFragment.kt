package com.example.rshop.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rnote.adapter.ProductAdapter
import com.example.rshop.R
import com.example.rshop.data.model.ProductModel
import com.example.rshop.databinding.FragmentHomeBinding
import com.example.rshop.util.resource.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var imageList: ArrayList<Image>
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCategoryRecyclerView()
        initObserve()
        setupViewFlipper()

    }


    private fun setupViewFlipper() {
        val imageList = arrayListOf(
            R.drawable.banner1,
            R.drawable.banner1,
            R.drawable.banner2
        )

        val inAnimation=AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left)
        val outAnimation=AnimationUtils.loadAnimation(context,android.R.anim.slide_out_right)

        binding.viewFlipper.inAnimation=inAnimation
        binding.viewFlipper.outAnimation=outAnimation
        for (image in imageList){
            val imageView=ImageView(context)
            imageView.setImageResource(image)
            imageView.layoutParams=RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
            binding.viewFlipper.addView(imageView)
        }
    }

    private fun initAdapter(list: List<ProductModel>) {
        productAdapter = ProductAdapter()
        binding.popularRecyclerview.adapter = productAdapter
        binding.popularRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.popularRecyclerview.setHasFixedSize(true)
        productAdapter.differ.submitList(list)

    }

    private fun initObserve() {
        homeViewModel.getFiveProductList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        initAdapter(it)

                    }
                    productAdapter.setOnItemClickListener {
                        val navController = findNavController()
                        navController.navigate(R.id.action_homeFragment_to_productDetailsFragment)

                    }
                }

                is Resource.Error -> {
                    Toast.makeText(context, "Hata var !! ", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {

                }

                else -> {}
            }

        }
    }

    private fun setupCategoryRecyclerView() {
        imageList = ArrayList()
        imageList.add(Image(R.drawable.one))
        imageList.add(Image(R.drawable.two))
        imageList.add(Image(R.drawable.three))
        imageList.add(Image(R.drawable.four))
        imageList.add(Image(R.drawable.five))
        categoryAdapter = CategoryAdapter(imageList)
        binding.categoryRecyclerview.adapter = categoryAdapter
        binding.categoryRecyclerview.setHasFixedSize(true)
        binding.categoryRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter.setOnItemClickListener {
            when (it.image) {
                R.drawable.one -> findNavController().navigate(R.id.action_homeFragment_to_manClothesFragment)
                R.drawable.two -> findNavController().navigate(R.id.action_homeFragment_to_womanClothesFragment)
                R.drawable.three -> findNavController().navigate(R.id.action_homeFragment_to_electronicFragment)
                R.drawable.four -> findNavController().navigate(R.id.action_homeFragment_to_jeweleryFragment)
                R.drawable.five -> findNavController().navigate(R.id.action_homeFragment_to_allProductFragment)
            }

        }
    }
}