package com.eric.karschner.pcbuilder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.part_list_item.view.*
import android.content.Intent
import android.net.Uri


class PartAdapter(val parts: ArrayList<PCPart>, val context: Context) : RecyclerView.Adapter<PartAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PartAdapter.ViewHolder{

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.part_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val part = parts[position]
        holder.name_tv.text = part.name
        holder.price_tv.text = "$${part.price}"
        holder.type_tv.text = "${part.type}:"

    }

    override fun getItemCount() = parts.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val item = view
        val price_tv = view.part_price_tv
        val name_tv = view.part_name_tv
        val type_tv = view.part_type_tv

        init{
            item.setOnClickListener {
                val uri = Uri.parse(parts[adapterPosition].link)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            }
        }

    }
}