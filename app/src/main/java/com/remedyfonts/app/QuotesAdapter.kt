package com.remedyfonts.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuotesAdapter(
    private val context: Context,
    private var quotes: List<String>,
    private val onSaveClick: (String) -> Unit
) : RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {

    fun updateQuotes(newQuotes: List<String>) {
        quotes = newQuotes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_font_style, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote = quotes[position]
        holder.textPreview.text = quote
        holder.textStyleName.visibility = View.GONE
        holder.buttonLock.visibility = View.GONE
        
        holder.buttonCopy.setOnClickListener {
            onSaveClick(quote)
        }
    }

    override fun getItemCount(): Int = quotes.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textPreview: TextView = view.findViewById(R.id.textPreview)
        val textStyleName: TextView = view.findViewById(R.id.textStyleName)
        val buttonCopy: ImageView = view.findViewById(R.id.buttonCopy)
        val buttonLock: ImageView = view.findViewById(R.id.buttonLock)
    }
}