package com.remedyfonts.app

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuotesActivity : AppCompatActivity() {

    private lateinit var categoryList: ListView
    private lateinit var quotesListView: ListView

    private val categories = mapOf(
        "Love Quotes" to listOf(
            "Love is not about how many days, months, or years you have been together. Love is about how much you love each other every single day.",
            "You know you're in love when you can't fall asleep because reality is finally better than your dreams."
        ),
        "Motivational Quotes" to listOf(
            "The future belongs to those who believe in the beauty of their dreams.",
            "Don't watch the clock; do what it does. Keep going."
        ),
        "Sad Quotes" to listOf(
            "The worst kind of sad is not being able to explain why.",
            "Sometimes you have to accept that some people can only be in your heart, not in your life."
        ),
        "Funny Quotes" to listOf(
            "I'm not lazy, I'm on energy-saving mode.",
            "If you think nobody cares if you're alive, try missing a couple of payments."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        categoryList = findViewById(R.id.categoryList)
        quotesListView = findViewById(R.id.quotesRecyclerView)
        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        val categoryNames = categories.keys.toList()
        categoryList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, categoryNames)

        categoryList.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val category = categoryNames[position]
            if (PrefsManager.isCategoryUnlocked(this, category)) {
                showQuotes(category)
            } else {
                unlockCategory(category)
            }
        }

        AdManager.loadRewardedAd(this)
    }

    private fun showQuotes(category: String) {
        val quotes = categories[category] ?: return
        quotesListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, quotes)
        quotesListView.visibility = View.VISIBLE
    }

    private fun unlockCategory(category: String) {
        AdManager.showRewardedAd(this) {
            PrefsManager.unlockCategory(this, category)
            showQuotes(category)
            Toast.makeText(this, "$category unlocked!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        AdManager.loadRewardedAd(this)
    }
}
