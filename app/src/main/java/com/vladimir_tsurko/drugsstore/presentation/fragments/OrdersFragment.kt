package com.vladimir_tsurko.drugsstore.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vladimir_tsurko.drugsstore.App
import com.vladimir_tsurko.drugsstore.R
import com.vladimir_tsurko.drugsstore.databinding.FragmentOrdersBinding
import com.vladimir_tsurko.drugsstore.presentation.adapters.ordersAdapter.OrdersListAdapter
import com.vladimir_tsurko.drugsstore.presentation.adapters.purchaseadapter.PurchaseListAdapter
import com.vladimir_tsurko.drugsstore.presentation.viewmodels.CatalogViewModel
import com.vladimir_tsurko.drugsstore.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject


class OrdersFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CatalogViewModel

    private lateinit var adapter: OrdersListAdapter

    private val component by lazy{
        (requireActivity().application as App).component
    }

    private var _binding: FragmentOrdersBinding? = null
    private val binding: FragmentOrdersBinding
        get() = _binding ?: throw RuntimeException("FragmentOrdersBinding == null")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[CatalogViewModel::class.java]
        setupRecyclerView()
        setupOnLogoutButtonClick()



    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
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
        adapter = OrdersListAdapter()
        binding.ordersRv.adapter = adapter
        viewModel.getOrders()
        viewModel.orders.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

    }

    private fun setupOnLogoutButtonClick(){
        binding.logoutButton.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.action_global_to_authGraph)
        }
    }



}