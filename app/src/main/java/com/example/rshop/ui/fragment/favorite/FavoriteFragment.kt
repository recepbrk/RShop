package com.example.rshop.ui.fragment.favorite

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rshop.databinding.FragmentSaveBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding:FragmentSaveBinding
    private val favoriteViewModel :FavoriteViewModel by viewModels ()
    lateinit var favoriteAdapter: FavoriteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSaveBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        createRecyclerView()
        observeData()
        deleteFavoriteProduct()
    }
    private fun createRecyclerView() {
        favoriteAdapter = FavoriteAdapter()
        binding.saveRecyclerView.adapter = favoriteAdapter
    }

    private fun observeData() {
        favoriteViewModel.getFavoriteList().observe(viewLifecycleOwner) { favoriteList ->

            if (favoriteList.isEmpty()) {
                binding.favoriteEmpty.visibility = View.VISIBLE
                binding.favoriteEmptyTextview.visibility = View.VISIBLE
                binding.saveRecyclerView.visibility = View.GONE
            } else {
                favoriteAdapter.differ.submitList(favoriteList)
                binding.favoriteEmpty.visibility = View.GONE
                binding.favoriteEmptyTextview.visibility = View.GONE
            }
        }
    }
    private fun deleteFavoriteProduct() {
        favoriteAdapter.setOnItemClickListener {

            var alertDiologBuilder = AlertDialog.Builder(context)
            alertDiologBuilder.setTitle("Delete Product")
            alertDiologBuilder.setMessage("Delete a product from your favorites?")
            alertDiologBuilder.setPositiveButton("Yes") { diolog, which ->
                favoriteViewModel.deleteFavoriteProduct(it)
            }
            alertDiologBuilder.setNegativeButton("No") { diolog, which ->
                val alertDialog = alertDiologBuilder.create()
                alertDialog.dismiss()
            }
            val alertDialog = alertDiologBuilder.create()
            alertDialog.show()
        }
    }
}