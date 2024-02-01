package com.example.rshop.ui.fragment.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.rnote.adapter.ProductAdapter
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
    }


    private fun createRecyclerView(){
        favoriteAdapter=FavoriteAdapter()
        binding.saveRecyclerView.adapter=favoriteAdapter
    }

    private fun observeData(){
        favoriteViewModel.getFavoriteList().observe(viewLifecycleOwner){
            favoriteAdapter.differ.submitList(it)

        }

    }


}