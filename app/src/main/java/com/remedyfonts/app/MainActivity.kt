package com.remedyfonts.app

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FontAdapter
    private var allStyles: List<FontStyle> = emptyList()
    private var filteredStyles: MutableList<FontStyle> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTextInput)
        recyclerView = findViewById(R.id.recyclerViewFonts)
        val buttonQuotes = findViewById<Button>(R.id.buttonQuotes)
        val buttonBubble = findViewById<Button>(R.id.buttonBubble)

        allStyles = FontGenerator.getAllStyles()
        filteredStyles.addAll(allStyles)

        adapter = FontAdapter(this, filteredStyles) { style ->
            showRewardedAdForFont(style)
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

        buttonQuotes.setOnClickListener {
            startActivity(Intent(this, QuotesActivity::class.java))
        }

        buttonBubble.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName"))
                startActivity(intent)
                Toast.makeText(this, "Enable overlay permission first", Toast.LENGTH_LONG).show()
            } else {
                startService(Intent(this, FloatingBubbleService::class.java))
                Toast.makeText(this, "Bubble started! Switch apps to use it", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showRewardedAdForFont(style: FontStyle) {
        AdManager.showRewardedAd(this) {
            PrefsManager.unlockFont(this, style.name)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "${style.name} unlocked!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        AdManager.loadRewardedAd(this)
    }
}
