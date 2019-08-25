package org.oleggalimov.simplescorecalc.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.oleggalimov.simplescorecalc.R
import org.oleggalimov.simplescorecalc.adapters.GameAdapter

class GameActivity : AppCompatActivity() {
    private lateinit var gameTitleView: TextView
    private lateinit var playersListRecView: RecyclerView
    private lateinit var currentPlayer: TextView
    private lateinit var finishButton: Button
    private lateinit var backButton: Button
    private lateinit var playersListAdapter: GameAdapter
    private var playersList = mutableListOf<Pair<String, Int>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        //получаем представления
        gameTitleView = findViewById(R.id.gameTitle) //название игры
        currentPlayer = findViewById(R.id.currentPlayer) //название игры

        //кнопки
        finishButton = findViewById(R.id.finishButton)
        backButton = findViewById(R.id.backButton)
        playersListRecView = findViewById(R.id.playersList) //список игроков
        //получаем переменные
        val gameTitle = intent.getStringExtra("gameTitle")

        intent
            .getStringArrayExtra("playersList").orEmpty()
            .mapTo(playersList, { player -> Pair(player, 0) })
        playersListRecView.layoutManager = LinearLayoutManager(this)
        playersListAdapter = GameAdapter(playersList.toMutableList(), this)
        playersListRecView.adapter = playersListAdapter
        gameTitleView.text = gameTitle

        //вешаем листенеры на кнопки
        val onClickListenerImpl = View.OnClickListener(

            fun(view: View?) {
                when (view?.id) {

                    finishButton.id -> {
                        this.playersList =
                            playersListAdapter.getActualPlayerList() //TODO: find more elegant way to update the list from adapter
                        val winner = playersList.maxBy { it.second }
                        var player = winner?.first
                        val score = winner?.second
                        playersList.forEach {
                            if (player != it.first && score == it.second) {
                                player = "DRAW"
                            }
                        }
                        val intent = Intent("org.oleggalimov.simplescorecalc.actions.finishgame")
                        intent.putExtra("name", player)
                        intent.putExtra("score", score)
                        intent.putExtra("title", gameTitle)
                        startActivity(intent)
                    }
                    backButton.id -> this.finish()
                }
            }
        )
        finishButton.setOnClickListener(onClickListenerImpl)
        backButton.setOnClickListener(onClickListenerImpl)
    }

    override
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        playersListAdapter.onResultActivity(requestCode, resultCode, data)
    }
}
