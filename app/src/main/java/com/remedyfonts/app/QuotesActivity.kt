package com.remedyfonts.app

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QuotesActivity : AppCompatActivity() {

    private lateinit var categoryList: ListView
    private lateinit var quotesList: RecyclerView
    private lateinit var adapter: QuotesAdapter
    private lateinit var btnBack: ImageView

    private val categories = mapOf(
        "Love Quotes" to listOf(
            "Love is not about how many days, months, or years you have been together. Love is about how much you love each other every single day.",
            "You know you're in love when you can't fall asleep because reality is finally better than your dreams.",
            "I swear I couldn't love you more than I do right now, and yet I know I will tomorrow.",
            "The best thing to hold onto in life is each other.",
            "In all the world, there is no heart for me like yours. In all the world, there is no love for you like mine."
        ),
        "Motivational Quotes" to listOf(
            "The future belongs to those who believe in the beauty of their dreams.",
            "Don't watch the clock; do what it does. Keep going.",
            "Success is not final, failure is not fatal: it is the courage to continue that counts.",
            "Believe you can and you're halfway there.",
            "The only way to do great work is to love what you do."
        ),
        "Sad Quotes" to listOf(
            "The worst kind of sad is not being able to explain why.",
            "Sometimes you have to accept that some people can only be in your heart, not in your life.",
            "It hurts to let go, but sometimes it hurts more to hold on.",
            "The saddest thing about betrayal is that it never comes from your enemies.",
            "Tears are words the heart can't express."
        ),
        "Funny Quotes" to listOf(
            "I'm not lazy, I'm on energy-saving mode.",
            "If you think nobody cares if you're alive, try missing a couple of payments.",
            "I used to think I was indecisive, but now I'm not too sure.",
            "My bed is a magical place where I suddenly remember everything I forgot to do.",
            "I followed my heart, and it led me to the fridge."
        ),
        "Flirty Letters" to listOf(
            "Are you a magician? Because whenever I look at you, everyone else disappears.",
            "Do you have a name, or can I call you mine?",
            "I must be a snowflake, because I've fallen for you.",
            "Are you WiFi? Because I'm really feeling a connection.",
            "If you were a vegetable, you'd be a cute-cumber."
        ),
        "Heartbreak Letters" to listOf(
            "I gave you my heart, and you gave it back in pieces. I'm still picking them up.",
            "You promised forever, but forever ended too soon.",
            "I never knew heartbreak until I heard goodbye from your lips.",
            "The hardest part isn't losing you; it's losing the future we planned.",
            "You walked away like it was nothing, but it meant everything to me."
        ),
        "Goodnight Messages" to listOf(
            "Goodnight, my love. May your dreams be as sweet as you are.",
            "As you close your eyes, know that you are the last thought on my mind.",
            "I wish I could be there to tuck you in. Goodnight, beautiful.",
            "May the stars light up your dreams tonight. Sleep well.",
            "Goodnight! Don't let the bed bugs bite — and if they do, bite back!"
        ),
        "Wedding Wishes" to listOf(
            "May your love be a never-ending story. Congratulations on your wedding!",
            "Two hearts, one love. Wishing you a lifetime of happiness together.",
            "Today you are two, but from now on you are one. Best wishes!",
            "May your marriage be filled with laughter, love, and endless adventures.",
            "Here's to love, laughter, and happily ever after!"
        ),
        "Irony Quotes" to listOf(
            "The only constant in life is change. How ironic.",
            "I'm a pacifist, but I'd fight anyone who disagrees.",
            "I hate people who use big words just to make themselves look perspicacious.",
            "Isn't it ironic that we ignore those who adore us and adore those who ignore us?",
            "The best way to find something is to stop looking for it."
        ),
        "Goodbye Letters" to listOf(
            "Goodbyes are not forever. Goodbyes are not the end. They simply mean I'll miss you until we meet again.",
            "It's time to say goodbye, but I think goodbyes are sad and I'd much rather say hello.",
            "How lucky I am to have something that makes saying goodbye so hard.",
            "Don't cry because it's over. Smile because it happened.",
            "See you later, alligator. After a while, crocodile."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        categoryList = findViewById(R.id.categoryList)
        quotesList = findViewById(R.id.quotesRecyclerView)
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener { finish() }

        adapter = QuotesAdapter(this, emptyList()) { quote ->
            ImageSaver.saveTextAsImage(this, quote, "Quote")
        }
        quotesList.layoutManager = LinearLayoutManager(this)
        quotesList.adapter = adapter

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
        categories[category]?.let { quotes ->
            adapter.updateQuotes(quotes)
            quotesList.visibility = View.VISIBLE
            Toast.makeText(this, category, Toast.LENGTH_SHORT).show()
        }
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