package com.example.rshop.ui.fragment.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rshop.databinding.FragmentProductDetailsBinding
import com.example.rshop.ui.fragment.favorite.FavoriteViewModel
import com.example.rshop.util.resource.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private val productDetailsViewModel: ProductDetailsViewModel by viewModels()
    private val favoriteViewModel :FavoriteViewModel by viewModels ()
    private val args: ProductDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productDetailsViewModel.getDetails(args.argDetails.id)
        initObserve()
        backButton()
    }


    private fun initObserve() {
        productDetailsViewModel.getdetailsList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {

                    response.data?.let {
                        binding.detailsTitle.text = it.title
                        binding.detailsPrice.text = "$" + it.price.toString()
                        binding.ratingbar.rating = it.rating.rate.toString().toFloat()
                        binding.detailsStar.text = it.rating.rate.toString()
                        binding.detailesDescription.text = it.description
                        Glide.with(requireContext()).load(it.image).into(binding.detailsImage)
                        binding.progressBar2.visibility = View.GONE
                        binding.favoriteIcon.setOnClickListener {
                            favoriteViewModel.addFavoriteProduct(response.data)
                        }
                    }

                }

                is Resource.Error -> {
                    binding.progressBar2.visibility = View.VISIBLE
                    Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()

                }

                is Resource.Loading -> {
                    binding.progressBar2.visibility = View.VISIBLE

                }

                else -> {}
            }

        }
    }

    private fun backButton() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }


    }
}


