package com.awesome.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.awesome.investpro.R
import com.awesome.rev.viewModel


class cAdapter(private var mlist:List<viewModel>):RecyclerView.Adapter<cAdapter.ViewHolder>() {

    private lateinit var mListuser: onItemClickListener

    fun filterList(filterList: ArrayList<viewModel>) {
        // below line is to add our filtered
        // list in our course array list.
        mlist = filterList
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }
    interface onItemClickListener{
        fun onItemClick(position: Int)

    }
    fun onItemClickListener(listener: onItemClickListener){
        mListuser=listener
    }

    fun setFilteredList(mlist: List<viewModel>){
        this.mlist=mlist
    }

    class ViewHolder(ItemView:View,listener: onItemClickListener):RecyclerView.ViewHolder(ItemView) {

        val symbolTV:TextView = itemView.findViewById(R.id.idTVSymbol)
        val rateTV :TextView= itemView.findViewById(R.id.idTVRate);
        val  nameTV:TextView = itemView.findViewById(R.id.idTVName)

        init {
            itemView.setOnClickListener {
                itemView.setOnClickListener {
                    listener.onItemClick(adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listview,parent, false)

        return ViewHolder(view,mListuser)
    }

    override fun getItemCount(): Int {
return mlist.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val items=mlist[position]
        holder.symbolTV.text=mlist[position].symbol
        holder.rateTV.text=items.price
        holder.nameTV.text=items.name



    }


}