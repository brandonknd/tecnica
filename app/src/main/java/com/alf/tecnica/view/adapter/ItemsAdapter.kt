package com.alf.tecnica.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alf.tecnica.R
import com.alf.tecnica.databinding.MockiRowBinding
import com.alf.tecnica.model.MockiDataModel
import com.alf.tecnica.view.fragments.ClickListener
import com.alf.tecnica.view.viewholder.itemViewHolder
import java.util.ArrayList

class ItemsAdapter(private val listener: ClickListener): RecyclerView.Adapter<itemViewHolder>(){
    private val resourse = R.layout.mocki_row
    lateinit var  context: Context
    private val itemList = ArrayList<MockiDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        context = parent.context
        Log.e(">>>>>context>>>>>", context.toString())
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: MockiRowBinding =
            DataBindingUtil.inflate(layoutInflater, resourse, parent, false)
        return itemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        Log.e(">>>>>onBindViewHolder>>>>>", itemList.toString())
        //conect to view
        holder.setItem(itemList[position])

        holder.itemView.setOnClickListener {
            listener.itemSelect(itemList[position])
        }
    }

    fun setItems(list: ArrayList<MockiDataModel>){
        Log.e(">>>>>set Item>>>>>", list.toString())
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

}