package com.example.pagging3example.Paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagging3example.R
import com.example.pagging3example.models.Result

class QuotePagingAdapter :
    PagingDataAdapter<com.example.pagging3example.models.Result, QuotePagingAdapter.QuoteViewHolder>(
        COMPARATOR
    ) {
    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quote = itemView.findViewById<TextView>(R.id.quote)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {

        val item = getItem(position)
        if (item != null) {
            holder.quote.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_quote_layout, parent, false)
        return QuoteViewHolder(view)
    }

    companion object {
        private val COMPARATOR =
            object : DiffUtil.ItemCallback<com.example.pagging3example.models.Result>() {
                override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem._id == newItem._id
                }

                override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem == newItem
                }

            }
    }
}