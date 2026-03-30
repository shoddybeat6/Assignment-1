package com.shoddycracked.assignment1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity : AppCompatActivity() {

    private lateinit var editTextTimeOfDay: EditText
    private lateinit var buttonGetSuggestion: Button
    private lateinit var textViewSuggestion: TextView
    private lateinit var buttonReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editTextTimeOfDay = findViewById(R.id.editTextTimeOfDay)
        buttonGetSuggestion = findViewById(R.id.buttonGetSuggestion)
        textViewSuggestion = findViewById(R.id.textViewSuggestion)
        buttonReset = findViewById(R.id.buttonReset)


        buttonGetSuggestion.setOnClickListener {

            val timeOfDay = editTextTimeOfDay.text.toString().trim().lowercase()

            val suggestion = getSocialSuggestions(timeOfDay)

            if (suggestion.isNotEmpty()) {
                textViewSuggestion.text = suggestion
            } else {
                Toast.makeText(
                    this, "Please enter a valid time of day (morning, afternoon, etc.)",
                    Toast.LENGTH_SHORT).show()
            }
        }


        buttonReset.setOnClickListener {
            editTextTimeOfDay.text.clear()
            textViewSuggestion.text = "Suggestion will appear here"
        }
    }


    private fun getSocialSuggestions(timeOfDay: String): String {
        return when (timeOfDay) {
            "morning" -> "Send a 'Good morning' text to a family member."
            "mid-morning" -> "Reach out to a colleague with a quick 'Thank you.'"
            "afternoon" -> "Share a funny meme or interesting link with a friend."
            "afternoon snack time" -> "Send a quick 'thinking of you' message."
            "dinner" -> "Call a friend or relative for a 5-minute catch-up."
            "after dinner", "night" -> "Leave a thoughtful comment on a friend's post."
            "midnight", "am" , "ams", "late morning" -> "try to unwind from social media."
            "noon", "midday" -> "Socialise with other people around you."
            "breakfast", "lunch" -> "A meal worth shearing with loved ones"
            else -> ""

        }
    }
}