cd /storage/emulated/0/python/RemedyFonts && cat > app/src/main/java/com/remedyfonts/app/MainActivity.kt << 'EOF'
package com.remedyfonts.app

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var textBold: TextView
    private lateinit var textItalic: TextView
    private lateinit var buttonUnlock: Button
    private lateinit var errorView: TextView
    private var italicUnlocked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.activity_main)

            editText = findViewById(R.id.editTextInput)
            textBold = findViewById(R.id.textBold)
            textItalic = findViewById(R.id.textItalic)
            buttonUnlock = findViewById(R.id.buttonUnlock)
            errorView = findViewById(R.id.errorView)

            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    val input = s?.toString() ?: ""
                    textBold.text = "Bold: " + input.replace("a","𝐚").replace("A","𝐀")
                    if (italicUnlocked) {
                        textItalic.text = "Italic: " + input.replace("a","𝑎").replace("A","𝐴")
                    } else {
                        textItalic.text = "Italic: (locked)"
                    }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            buttonUnlock.setOnClickListener {
                try {
                    AdManager.showRewardedAd(this) {
                        italicUnlocked = true
                        textItalic.alpha = 1.0f
                        editText.text.toString().let { input ->
                            textItalic.text = "Italic: " + input.replace("a","𝑎").replace("A","𝐴")
                        }
                        Toast.makeText(this, "Italic unlocked!", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    errorView.text = "Ad error: ${e.message}"
                }
            }

            AdManager.loadRewardedAd(this)
        } catch (e: Exception) {
            // Fallback: show error on screen
            setContentView(android.R.layout.activity_list_item)
            val tv = TextView(this)
            tv.text = "Crash: ${e.message}\n${e.stackTraceToString()}"
            tv.setTextIsSelectable(true)
            setContentView(tv)
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            AdManager.loadRewardedAd(this)
        } catch (e: Exception) {
            errorView.text = "Ad resume error: ${e.message}"
        }
    }
}
EOF

# Update the layout to include a hidden error text view
cat > app/src/main/res/layout/activity_main.xml << 'EOF'
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">
    <EditText
        android:id="@+id/editTextInput"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Type your text"
        android:textSize="20sp"/>
    <TextView
        android:id="@+id/textBold"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="18sp"
        android:padding="8dp"/>
    <Button
        android:id="@+id/buttonUnlock"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Unlock Italic (Watch Ad)"/>
    <TextView
        android:id="@+id/textItalic"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="18sp"
        android:padding="8dp"
        android:alpha="0.5"/>
    <!-- This will only show if an error is caught -->
    <TextView
        android:id="@+id/errorView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textColor="#FF0000"
        android:textSize="12sp"
        android:visibility="gone"/>
</LinearLayout>
EOF

git add .
git commit -m "Add crash diagnostics"
git push origin main
