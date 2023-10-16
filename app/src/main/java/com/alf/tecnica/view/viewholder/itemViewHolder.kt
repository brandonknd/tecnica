package com.alf.tecnica.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.alf.tecnica.databinding.MockiRowBinding
import com.alf.tecnica.model.MockiDataModel

class itemViewHolder(binding: MockiRowBinding): RecyclerView.ViewHolder(binding.root) {
    private var binding: MockiRowBinding?= null

    init {
        this.binding = binding
    }

    fun setItem(model: MockiDataModel){
        binding?.let {view->
            view.name = model.name
            view.type = "Tipo: " + model.type

        }
    }
}