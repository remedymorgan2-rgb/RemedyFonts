package com.remedyfonts.app

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import android.view.Gravity
import android.view.WindowManager

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FontAdapter
    private lateinit var buttonQuotes: Button
    private lateinit var buttonBubble: Button

    private var allStyles: List<FontStyle> = emptyList()
    private var filteredStyles: MutableList<FontStyle> = mutableListOf()
    private val handler = Handler(Looper.getMainLooper())
    private var rgbRunnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTextInput)
        recyclerView = findViewById(R.id.recyclerViewFonts)
        buttonQuotes = findViewById(R.id.buttonQuotes)
        buttonBubble = findViewById(R.id.buttonBubble)

        // Load all font styles
        allStyles = FontGenerator.getAllStyles()
        filteredStyles.addAll(allStyles)

        // Setup RecyclerView
        adapter = FontAdapter(this, filteredStyles) { style ->
            showRewardedAdForFont(style)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Live text update
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                adapter.updateText(s?.toString() ?: "")
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Load rewarded ad
        AdManager.loadRewardedAd(this)

        // Quotes button
        buttonQuotes.setOnClickListener {
            startActivity(Intent(this, QuotesActivity::class.java))
        }

        // Floating bubble button
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

        // Start RGB background animation
        startRgbAnimation()
    }

    private fun showRewardedAdForFont(style: FontStyle) {
        AdManager.showRewardedAd(this) {
            PrefsManager.unlockFont(this, style.name)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "${style.name} unlocked!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startRgbAnimation() {
        val root = findViewById<android.view.View>(R.id.rootLayout)
        val colors = intArrayOf(
            Color.parseColor("#FF0B0B0E"),
            Color.parseColor("#1A0033"),
            Color.parseColor("#0B0B1A"),
            Color.parseColor("#1A001A"),
            Color.parseColor("#0B0B0E")
        )
        var index = 0

        rgbRunnable = object : Runnable {
            override fun run() {
                val nextIndex = (index + 1) % colors.size
                val fromColor = colors[index]
                val toColor = colors[nextIndex]

                // Simple transition — just alternate backgrounds for now
                root.setBackgroundColor(toColor)
                index = nextIndex
                handler.postDelayed(this, 3000)
            }
        }
        handler.post(rgbRunnable!!)
    }

    override fun onResume() {
        super.onResume()
        AdManager.loadRewardedAd(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        rgbRunnable?.let { handler.removeCallbacks(it) }
    }
}