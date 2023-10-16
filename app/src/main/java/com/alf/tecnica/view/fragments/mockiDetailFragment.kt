package com.alf.tecnica.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.alf.tecnica.R
import com.alf.tecnica.databinding.FragmentMockiDetailBinding
import com.alf.tecnica.viewmodels.RecyclerMockiViewModel

class mockiDetailFragment : Fragment() {

    private var _binding: FragmentMockiDetailBinding? = null
    private val binding get() = _binding

    lateinit var  viewModel: RecyclerMockiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.let {
            ViewModelProvider(it).get(RecyclerMockiViewModel::class.java)
        }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMockiDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.itemDataSelected?.let{data ->
            binding!!.tvName.text = data.name

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        fun newInstance() = mockiDetailFragment()
    }


}