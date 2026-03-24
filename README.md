# Assignment-1

Activity_main.xml


<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    tools:ignore="ExtraText">

    <TextView
        android:id="@+id/textViewResult"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Results will appear here"
        android:textSize="24sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.404" />

    <Button
        android:id="@+id/buttonCheck"
        android:layout_width="182dp"
        android:layout_height="99dp"
        android:text="Check Guess"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.497"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.818" />

    <EditText
        android:id="@+id/editTextGuess"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:ems="10"
        android:inputType="number"
        android:minHeight="48dp"
        android:text="Enter a Number (1-100)"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.5" />

    <Button
        android:id="@+id/buttonReset"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Play Again"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.935" />


    /

</androidx.constraintlayout.widget.ConstraintLayout>





MainActivity.kt

package com.shoddycracked.guessthenumber

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var editTextGuess: EditText
    private lateinit var buttonCheck: Button
    private lateinit var buttonReset: Button
    private lateinit var textViewResult: TextView

    private var randomNumber = Random.nextInt(1,101)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editTextGuess = findViewById(R.id.editTextGuess)
        buttonCheck = findViewById(R.id.buttonCheck)
        buttonReset = findViewById(R.id.buttonReset)
        textViewResult = findViewById(R.id.textViewResult)

        // CHECK BUTTON
        buttonCheck.setOnClickListener {

            val userInput = editTextGuess.text.toString()

            if (userInput.isEmpty()) {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val guess = userInput.toIntOrNull()

            if (guess == null) {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (guess == randomNumber) {
                textViewResult.text = " Correct! You guessed right"
            } else if (guess < randomNumber) {
                textViewResult.text = "Too low! Try again."
            } else {
                textViewResult.text = "Too high! Try again."
            }
        }


        buttonReset.setOnClickListener {
            randomNumber = Random.nextInt(1, 101)
            editTextGuess.text.clear()
            textViewResult.text = "Number reset, enter new guess"
        }
    }
}


S
