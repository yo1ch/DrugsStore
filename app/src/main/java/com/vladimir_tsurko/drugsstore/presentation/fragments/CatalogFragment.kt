package com.vladimir_tsurko.drugsstore.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vladimir_tsurko.drugsstore.App
import com.vladimir_tsurko.drugsstore.R
import com.vladimir_tsurko.drugsstore.databinding.FragmentCatalogBinding
import com.vladimir_tsurko.drugsstore.presentation.adapters.ProductListAdapter
import com.vladimir_tsurko.drugsstore.presentation.viewmodels.CatalogViewModel
import com.vladimir_tsurko.drugsstore.presentation.viewmodels.ViewModelFactory
import com.vladimir_tsurko.drugsstore.utils.Resource

import javax.inject.Inject

class CatalogFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CatalogViewModel

    private val component by lazy{
        (requireActivity().application as App).component
    }

    private var _binding: FragmentCatalogBinding? = null
    private val binding: FragmentCatalogBinding
        get() = _binding ?: throw RuntimeException("FragmentCatalogBinding == null")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[CatalogViewModel::class.java]
        setupOnLogoutButtonClick()
        setupRecyclerView()
        setupCategories()


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private fun setupOnLogoutButtonClick(){
        binding.logoutButton.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.action_global_to_authGraph)
        }
    }

    private fun setupCategories(){
        viewModel.categories.observe(viewLifecycleOwner){ response ->
            when(response){
                is Resource.Success ->{
                    val categories = mutableListOf<String>()
                    response.data?.forEach {
                        categories.add(it.name)
                    }
                    val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_menu_item, categories)
                    binding.categories1.setAdapter(arrayAdapter)
                    binding.categories1.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                        val selectedItem = parent.getItemAtPosition(position).toString()
                        Toast.makeText(activity,selectedItem,Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Error -> Toast.makeText(activity, response.message, Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }

    }

    private fun setupRecyclerView(){
        val adapter = ProductListAdapter()
        binding.rvProductsList.adapter = adapter
        viewModel.productsResponse.observe(viewLifecycleOwner){
            when(it) {
                is Resource.Success ->{
                    adapter.submitList(it.data)
                }
                is Resource.Error -> Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }

    }


}