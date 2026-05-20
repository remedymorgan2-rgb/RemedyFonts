package com.remedyfonts.app

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FontAdapter(
    private val context: Context,
    private val styles: List<FontStyle>,
    private val onUnlockClick: (FontStyle) -> Unit
) : RecyclerView.Adapter<FontAdapter.ViewHolder>() {

    private var inputText: String = ""

    fun updateText(text: String) {
        inputText = text
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_font_style, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val style = styles[position]
        val transformed = style.transform(inputText.ifEmpty { "Hello World" })

        holder.textPreview.text = transformed
        holder.textStyleName.text = style.name

        if (style.isPremium && !PrefsManager.isFontUnlocked(context, style.name)) {
            holder.buttonLock.visibility = View.VISIBLE
            holder.textPreview.alpha = 0.4f
        } else {
            holder.buttonLock.visibility = View.GONE
            holder.textPreview.alpha = 1.0f
        }

        holder.buttonCopy.setOnClickListener {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newPlainText("styled_text", transformed))
            Toast.makeText(context, "Copied!", Toast.LENGTH_SHORT).show()
        }

        holder.buttonLock.setOnClickListener {
            onUnlockClick(style)
        }
    }

    override fun getItemCount(): Int = styles.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textPreview: TextView = view.findViewById(R.id.textPreview)
        val textStyleName: TextView = view.findViewById(R.id.textStyleName)
        val buttonCopy: TextView = view.findViewById(R.id.buttonCopy)
        val buttonLock: TextView = view.findViewById(R.id.buttonLock)
    }
}
