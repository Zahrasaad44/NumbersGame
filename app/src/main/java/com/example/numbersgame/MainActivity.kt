package com.example.numbersgame

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var rootLayout: ConstraintLayout  // To use it in the Snackbar
    private lateinit var userEntry: EditText
    private lateinit var guessBtn: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var entries: ArrayList<String>

    private var num = Random.nextInt(10)
    private var guesses = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rootLayout = findViewById(R.id.rootLayout)  // To use it in the Snackbar
        userEntry = findViewById(R.id.userEntry)
        guessBtn = findViewById(R.id.guessBtn)
        recyclerView = findViewById(R.id.recyclerView)

        entries = ArrayList()

        recyclerView.adapter = GuessAdapter(this, entries)
        recyclerView.layoutManager = LinearLayoutManager(this)

        guessBtn.setOnClickListener { addUserEntry() }
    }

   private fun addUserEntry() {
        val guess = userEntry.text.toString()

        if(guess.isNotEmpty()) {
            if(guesses > 0) {
                if(guess.toInt() == num) {
                    disableEntry()  // So that the user can't continue after winning (guessing correctly)
                    displayAlert("You Win!!")
                } else {
                    guesses--
                    entries.add("You guessed $guess")
                    entries.add("You have $guesses guesses left")
                }
                if(guesses == 0) {
                    disableEntry()
                    entries.add("You lose. The correct answer was $num")
                    entries.add("Game over")
                    displayAlert("You lose :( \nThe correct answer was $num.")
                }
            }
            userEntry.text.clear()
            //userEntry.clearFocus()
            recyclerView.adapter?.notifyDataSetChanged()
        } else {
            Snackbar.make(rootLayout, "Please enter a number", Snackbar.LENGTH_LONG).show()
        }
    }

   private fun disableEntry() {
        guessBtn.isEnabled = false
        guessBtn.isClickable = false
        userEntry.isEnabled = false
        userEntry.isClickable = false
    }

    private fun displayAlert(title: String){
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(title)
            .setCancelable(false)  // To not make the dialog canceled (dismissed) using the BACK key

            .setPositiveButton("Yes",DialogInterface.OnClickListener {
                    dialog, id ->  this.recreate()
            })
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()

            })
        // creating dialog box
        val alert = dialogBuilder.create()
        alert.setMessage("Do you want to play again?")
        alert.show()
    }
}