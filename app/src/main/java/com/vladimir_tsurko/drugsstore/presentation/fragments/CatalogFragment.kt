package com.vladimir_tsurko.drugsstore.presentation.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.vladimir_tsurko.drugsstore.App
import com.vladimir_tsurko.drugsstore.R
import com.vladimir_tsurko.drugsstore.databinding.FragmentCatalogBinding
import com.vladimir_tsurko.drugsstore.domain.models.CategoryModel
import com.vladimir_tsurko.drugsstore.domain.models.ProductModel
import com.vladimir_tsurko.drugsstore.presentation.adapters.ProductListAdapter
import com.vladimir_tsurko.drugsstore.presentation.viewmodels.CatalogViewModel
import com.vladimir_tsurko.drugsstore.presentation.viewmodels.ViewModelFactory
import com.vladimir_tsurko.drugsstore.utils.Resource
import kotlinx.android.synthetic.main.item_product_list.view.*

import javax.inject.Inject

class CatalogFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CatalogViewModel

    private lateinit var adapter: ProductListAdapter

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
        setupOnButtonClickListener()


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
                    val categoriesName = mutableListOf<String>()
                    val categories = mutableListOf<CategoryModel>()
                    response.data?.forEach {
                        categories.add(it)
                        categoriesName.add(it.name)
                    }
                    val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_menu_item, categoriesName)
                    binding.categoriesDropDown.setAdapter(arrayAdapter)
                    binding.categoriesDropDown.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                        val selectedItemName = parent.getItemAtPosition(position).toString()
                        val selectedItem = categories.find {
                            it.name == selectedItemName
                        }
                        selectedItem?.let { viewModel.getProductsByCategory(it.id) }
                    }
                }
                is Resource.Error -> Toast.makeText(activity, response.message, Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }

    }
    private fun setupOnButtonClickListener(){
        adapter.onButtonClickListener = { view: View, product: ProductModel ->
            makeButtonClicked(view)
            viewModel.addToCart(product)

        }
    }

    private fun setupRecyclerView(){
        adapter = ProductListAdapter()
        binding.rvProductsList.adapter = adapter
        val itemAnimator = binding.rvProductsList.itemAnimator
        if(itemAnimator is DefaultItemAnimator){
            itemAnimator.supportsChangeAnimations = false
        }
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

    private fun makeButtonClicked(view: View){
        with(view.add_to_cart_button){
            isClickable = false
            setBackgroundColor(Color.WHITE)
            text = "Товар добавлен в корзину"
            setTextColor(Color.BLACK)
            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10f)

        }

    }


}