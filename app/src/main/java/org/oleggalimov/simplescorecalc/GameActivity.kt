package org.oleggalimov.simplescorecalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GameActivity : AppCompatActivity() {
    private lateinit var  gameTitleView: TextView
    private lateinit var  playersListRecView: RecyclerView
    private lateinit var  minusButton: AppCompatImageButton
    private lateinit var  inputPoints: TextView
    private lateinit var  plusButtonView: AppCompatImageButton
    private lateinit var  nextButton: Button
    private lateinit var  backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        //получаем представления
        gameTitleView = findViewById(R.id.gameTitle)
        minusButton = findViewById(R.id.minusButton)
        inputPoints = findViewById(R.id.inputPoints)
        plusButtonView = findViewById(R.id.plusButton)
        nextButton = findViewById(R.id.nextButton)
        backButton = findViewById(R.id.backButton)
        playersListRecView = findViewById(R.id.playersList)
        //получаем переменные
        val gameTitle = intent.getStringExtra("gameTitle")
        val playersList = intent.getStringArrayExtra("playersList")

        playersListRecView.layoutManager = LinearLayoutManager(this)
        val playersListAdapter = Adapter(playersList.toMutableList())
        playersListRecView.adapter = playersListAdapter
        gameTitleView.text=gameTitle
    }
}
