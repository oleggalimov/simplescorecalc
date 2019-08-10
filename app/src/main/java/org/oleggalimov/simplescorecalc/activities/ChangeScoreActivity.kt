package org.oleggalimov.simplescorecalc.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import org.oleggalimov.simplescorecalc.utilities.toastWithVibration
import android.content.Intent
import org.oleggalimov.simplescorecalc.R
import org.oleggalimov.simplescorecalc.enums.OperationType
import java.lang.Exception
import java.lang.UnsupportedOperationException


class ChangeScoreActivity:AppCompatActivity() {
    private lateinit var  playerName: TextView
    private lateinit var  playerScore: TextView
    private lateinit var  inputPoints: TextView
    private lateinit var  removePointButton: AppCompatImageButton
    private lateinit var  addPointButton: AppCompatImageButton
    private lateinit var  confirmButton: Button
    private lateinit var  cancelButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changescore)

        //получаем представления
        playerName = findViewById(R.id.player)
        playerScore = findViewById(R.id.playerScore)
        inputPoints = findViewById(R.id.inputPoints)
        removePointButton = findViewById(R.id.removePoint)
        addPointButton = findViewById(R.id.addPoint)
        confirmButton = findViewById(R.id.confirm)
        cancelButton = findViewById(R.id.cancel)

        val player = intent.getStringExtra("player")
        var score = intent.getIntExtra("score", 0)
        val position = intent.getIntExtra("position", 0)

        playerName.text=player
        playerScore.text=score.toString()
        //вешаем листенеры на кнопки
        val onClickListenerImpl = View.OnClickListener  (
            fun(view: View?) {
                when (view?.id) {
                    addPointButton.id, removePointButton.id -> {
                        val points = this.inputPoints.text.toString()

                        if (points.isBlank()) {
                            toastWithVibration(applicationContext, getString(R.string.hint_points_empty), true)
                        } else {
                            score = try {
                                val opType= if (view.id==addPointButton.id) {OperationType.ADD_POINTS} else {OperationType.REMOVE_POINTS}
                                changeScore (score, points, opType)
                            } catch (ex:Exception) {
                                toastWithVibration(applicationContext, getString(R.string.hint_empty_inappropriate), true)
                                score
                            }
                            playerScore.text= score.toString()
                            inputPoints.text = null
                        }
                    }

                    confirmButton.id -> {
                        val intent = Intent()
                        intent.putExtra("name", player)
                        intent.putExtra("score", score)
                        intent.putExtra("position", position)
                        setResult(RESULT_OK, intent)
                        this.finish()
                    }
                    cancelButton.id -> this.finish()
                }
            }
        )
        removePointButton.setOnClickListener (onClickListenerImpl)
        addPointButton.setOnClickListener (onClickListenerImpl)
        cancelButton.setOnClickListener (onClickListenerImpl)
        confirmButton.setOnClickListener (onClickListenerImpl)
    }
    @Throws(NumberFormatException::class)
    fun changeScore(previousScore:Int, points:String, operationType:OperationType):Int {
        val longPrevious =previousScore.toLong()
        val longPoints = points.toLong()
        val res:Long = when (operationType) {
            OperationType.ADD_POINTS -> longPrevious + longPoints
            OperationType.REMOVE_POINTS -> longPrevious - longPoints
        }
        if (res>Int.MAX_VALUE || res<Int.MIN_VALUE) {throw UnsupportedOperationException()}
        return res.toInt()
    }

}