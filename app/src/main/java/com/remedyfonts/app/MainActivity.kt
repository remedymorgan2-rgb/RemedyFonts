package com.remedyfonts.app

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var textBold: TextView
    private lateinit var textItalic: TextView
    private lateinit var buttonUnlock: Button
    private var italicUnlocked = false
    private var rewardedAd: RewardedAd? = null
    private val adUnitId = "ca-app-pub-3940256099942544/5224354917"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTextInput)
        textBold = findViewById(R.id.textBold)
        textItalic = findViewById(R.id.textItalic)
        buttonUnlock = findViewById(R.id.buttonUnlock)

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updateText(s?.toString() ?: "")
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        buttonUnlock.setOnClickListener {
            showRewardedAd()
        }

        loadRewardedAd()
    }

    private fun updateText(input: String) {
        textBold.text = "Bold: " + input.replace("a","𝐚").replace("A","𝐀")
        textItalic.text = if (italicUnlocked) {
            "Italic: " + input.replace("a","𝑎").replace("A","𝐴")
        } else {
            "Italic: (locked)"
        }
    }

    private fun loadRewardedAd() {
        RewardedAd.load(this, adUnitId, AdRequest.Builder().build(), object : RewardedAdLoadCallback() {
            override fun onAdLoaded(ad: RewardedAd) {
                rewardedAd = ad
            }
            override fun onAdFailedToLoad(error: LoadAdError) {
                rewardedAd = null
            }
        })
    }

    private fun showRewardedAd() {
        rewardedAd?.let { ad ->
            ad.show(this) { rewardItem ->
                italicUnlocked = true
                updateText(editText.text.toString())
                Toast.makeText(this, "Italic unlocked!", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(this, "Ad not ready, try again", Toast.LENGTH_SHORT).show()
            loadRewardedAd()
        }
    }

    override fun onResume() {
        super.onResume()
        loadRewardedAd()
    }
}
