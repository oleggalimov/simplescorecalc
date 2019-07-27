package org.oleggalimov.simplescorecalc.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.oleggalimov.simplescorecalc.R


class FinishActivity : AppCompatActivity() {
    private lateinit var congratsText:TextView
    private lateinit var restartButtonView: Button
    private lateinit var player: String
    private lateinit var score: String

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        congratsText=findViewById(R.id.congratText)
        restartButtonView=findViewById(R.id.restartButton)

        player = intent.getStringExtra("name").orEmpty()
        score = intent.getIntExtra("score", 0).toString()
        congratsText.text =
            if (player == "DRAW") {
                getString(R.string.final_congratulations_draw)
                .replace("PLAYER_SCORE",score)
            } else {
                getString(R.string.final_congratulations)
                .replace("PLAYER_NAME",player)
                .replace("PLAYER_SCORE",score)
        }
        val onClickListenerImpl = View.OnClickListener  (
            fun (v:View) {
                if (v.id==R.id.restartButton) {

                }
            }
        )
        restartButtonView.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

    }
}