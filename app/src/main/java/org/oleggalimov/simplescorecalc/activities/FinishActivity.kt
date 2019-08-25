package org.oleggalimov.simplescorecalc.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.oleggalimov.simplescorecalc.R
import org.oleggalimov.simplescorecalc.dto.Game
import org.oleggalimov.simplescorecalc.utilities.toastWithVibration


class FinishActivity : AppCompatActivity() {
    private lateinit var congratsText: TextView
    private lateinit var restartButtonView: Button
    private lateinit var player: String
    private lateinit var score: String
    private lateinit var gameLog: ArrayList<Game>
    private lateinit var gameTitle: String
    private lateinit var logTableLayout: TableLayout
    private val MAX_LOG_SIZE = 5
    private var isTableClicked: Long = 0


    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        congratsText = findViewById(R.id.congratText)
        restartButtonView = findViewById(R.id.restartButton)
        player = intent.getStringExtra("name").orEmpty()
        score = intent.getIntExtra("score", 0).toString()
        gameTitle = intent.getStringExtra("title").orEmpty()
        logTableLayout = findViewById(R.id.gameLogTable)

        val bestPlayerName = if (player == "DRAW") getString(R.string.final_draw) else player
        val oldLog = loadGameLog()

        gameLog = if (oldLog.size >= MAX_LOG_SIZE) {
            val newList = ArrayList<Game>(oldLog.subList(0, MAX_LOG_SIZE - 1))
            newList.add(Game(gameTitle, score.toInt(), bestPlayerName))
            saveLog(newList)
            newList.sort()
            newList
        } else {
            oldLog.add(Game(gameTitle, score.toInt(), bestPlayerName))
            saveLog(oldLog)
            oldLog.sort()
            oldLog
        }
        //now we can feel the table

        congratsText.text =
            if (player == "DRAW") {
                getString(R.string.final_congratulations_draw)
                    .replace("PLAYER_SCORE", score)
            } else {
                getString(R.string.final_congratulations)
                    .replace("PLAYER_NAME", player)
                    .replace("PLAYER_SCORE", score)
            }
        fillTable()
        logTableLayout.setOnClickListener {
            if (isTableClicked + 2000 > System.currentTimeMillis()) {
                gameLog = arrayListOf(Game(gameTitle, score.toInt(), bestPlayerName))
                saveLog(gameLog)
                toastWithVibration(this, getString(R.string.hint_clearTable_done), false)
            } else {
                toastWithVibration(this, getString(R.string.hint_clearTable), false)
            }
            isTableClicked = System.currentTimeMillis()
        }
        restartButtonView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    private fun loadGameLog(): ArrayList<Game> {
        val pref = getPreferences(Context.MODE_PRIVATE).all.values.toMutableList()
        return pref.map { Game(it as String) } as ArrayList
    }

    private fun saveLog(list: List<Game>): Boolean {
        val pref = getPreferences(Context.MODE_PRIVATE).edit()
        pref.clear()
        for (game in list) {
            pref.putString(game.title, game.toString())
        }
        return pref.commit()
    }

    private fun fillTable() {
        gameLog.forEach {
            val tableRow = TableRow(this)
            for (count in 1..3) {
                val column = TextView(this)
                column.gravity = Gravity.CENTER
                column.textSize = 18F
                column.text = when (count) {
                    1 -> it.title
                    2 -> it.score.toString()
                    3 -> it.bestPlayerName
                    else -> ""
                }
                tableRow.addView(column)
            }
            logTableLayout.addView(tableRow)
        }
    }
}