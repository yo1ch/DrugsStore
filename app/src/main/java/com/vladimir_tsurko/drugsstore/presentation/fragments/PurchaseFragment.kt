package com.vladimir_tsurko.drugsstore.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.vladimir_tsurko.drugsstore.App
import com.vladimir_tsurko.drugsstore.databinding.ActivityMainBinding.inflate
import com.vladimir_tsurko.drugsstore.databinding.FragmentPurchaseBinding
import com.vladimir_tsurko.drugsstore.presentation.viewmodels.CatalogViewModel
import com.vladimir_tsurko.drugsstore.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class PurchaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CatalogViewModel

    private val component by lazy{
        (requireActivity().application as App).component
    }

    private var _binding: FragmentPurchaseBinding? = null
    private val binding: FragmentPurchaseBinding
        get() = _binding ?: throw RuntimeException("FragmentPurchaseBinding == null")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[CatalogViewModel::class.java]
        viewModel.productsResponse.observe(viewLifecycleOwner){
            Log.d("CARTF", it.toString())
            Log.d("CARTF",it?.data.toString())
        }


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



}