package com.alf.tecnica.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alf.tecnica.R
import com.alf.tecnica.databinding.FragmentMockiListBinding
import com.alf.tecnica.model.MockiDataModel
import com.alf.tecnica.view.adapter.ItemsAdapter
import com.alf.tecnica.viewmodels.RecyclerMockiViewModel


class mockiListFragment : Fragment(), ClickListener {
    lateinit var viewModel: RecyclerMockiViewModel
    lateinit var binding: FragmentMockiListBinding
    private var mAdapter: ItemsAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            activity?.let{
                ViewModelProvider(it).get(RecyclerMockiViewModel::class.java)
            }!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMockiListBinding.inflate(inflater,container, false)
        binding.viewModel = viewModel
        // Inflate the layout for this fragment
        return binding.root
       // return inflater.inflate(R.layout.fragment_mocki_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("========","asd")
        // inicializa recyclerview
        mAdapter = ItemsAdapter(this)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = mAdapter

        //observador de la lista
        viewModel.listState.observe(viewLifecycleOwner) {

            mAdapter?.setItems(list = it)
            binding.progress.isInvisible = true
        }

        viewModel.progressState.observe(viewLifecycleOwner) { show ->
            binding.progress.isVisible = show
        }


        //mAdapter?.setItems(list)
        viewModel.fetchPokemonData()

    }


    override fun itemSelect(data: MockiDataModel) {
        viewModel.setItemSelection(data)

//        activity?.supportFragmentManager
//            ?.beginTransaction()
//            ?.replace(android.R.id.content, mockiDetailFragment.newInstance())
//            ?.addToBackStack(null)
//            ?.commit()
        findNavController().navigate(R.id.action_mockiListFragment_to_mockiDetailFragment)
    }


}

interface ClickListener{
    fun itemSelect(data:MockiDataModel)
}