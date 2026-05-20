package com.remedyfonts.app

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FontAdapter
    private val allStyles = FontGenerator.getAllStyles()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTextInput)
        recyclerView = findViewById(R.id.recyclerViewFonts)

        adapter = FontAdapter(this, allStyles) { style ->
            AdManager.showRewardedAd(this) {
                PrefsManager.unlockFont(this, style.name)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "${style.name} unlocked!", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                adapter.updateText(s?.toString() ?: "")
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        AdManager.loadRewardedAd(this)
    }

    override fun onResume() {
        super.onResume()
        AdManager.loadRewardedAd(this)
    }
}
