package com.vladimir_tsurko.drugsstore.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.vladimir_tsurko.drugsstore.App
import com.vladimir_tsurko.drugsstore.databinding.ActivityMainBinding.inflate
import com.vladimir_tsurko.drugsstore.databinding.FragmentPurchaseBinding
import com.vladimir_tsurko.drugsstore.presentation.adapters.ProductListAdapter
import com.vladimir_tsurko.drugsstore.presentation.adapters.purchaseadapter.PurchaseListAdapter
import com.vladimir_tsurko.drugsstore.presentation.viewmodels.CatalogViewModel
import com.vladimir_tsurko.drugsstore.presentation.viewmodels.ViewModelFactory
import com.vladimir_tsurko.drugsstore.utils.Resource
import javax.inject.Inject

class PurchaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CatalogViewModel

    private lateinit var adapter: PurchaseListAdapter

    private val component by lazy{
        (requireActivity().application as App).component
    }

    private var _binding: FragmentPurchaseBinding? = null
    private val binding: FragmentPurchaseBinding
        get() = _binding ?: throw RuntimeException("FragmentPurchaseBinding == null")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[CatalogViewModel::class.java]
        setupRecyclerView()


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPurchaseBinding.inflate(inflater, container, false)
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

    private fun setupRecyclerView(){
        adapter = PurchaseListAdapter()
        binding.rvPurchase.adapter = adapter
        viewModel.cartProducts.observe(viewLifecycleOwner){
            adapter.submitList(it?.toList())
        }

    }



}