package org.oleggalimov.simplescorecalc.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.oleggalimov.simplescorecalc.adapters.Adapter
import org.oleggalimov.simplescorecalc.R
import org.oleggalimov.simplescorecalc.utilities.toastWithVibration

class PlayersListActivity : AppCompatActivity() {
    private lateinit var gameTitleView:TextView
    private lateinit var  playerNameView:TextView
    private lateinit var  addPlayerButton:Button
    private lateinit var  nextButton:Button
    private lateinit var  backButton:Button
    private lateinit var  playersListRecView:RecyclerView
    private lateinit var vibrator:Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        val playersList = mutableListOf<String>()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_list)

        //получаем представления
        gameTitleView = findViewById(R.id.gameTitle)
        playerNameView = findViewById(R.id.player)
        addPlayerButton = findViewById(R.id.addPlayerButton)
        nextButton = findViewById(R.id.nextButton)
        backButton = findViewById(R.id.backButton)
        playersListRecView = findViewById(R.id.playersList)
        vibrator= this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        //меняем название игры
        val gameTitle = intent.getStringExtra("gameTitle")
        /*
       LayoutManager отвечает за позиционирование view-компонентов в RecyclerView,
       а также за определение того, когда следует переиспользовать view-компоненты, которые больше не видны пользователю.
        */
        playersListRecView.layoutManager = LinearLayoutManager(this)
        val playersListAdapter = Adapter(playersList)
        playersListRecView.adapter = playersListAdapter
        gameTitleView.text=gameTitle

        //вешаем листенеры на кнопки
        val onClickListenerImpl = View.OnClickListener  (

            fun(view: View?) {
                when (view?.id) {
                    addPlayerButton.id -> {
                        val playerName = playerNameView.text.toString()
                        when {
                            playerName.isBlank() -> {
                                toastWithVibration(applicationContext, getString(R.string.hint_playerNameBlank), true)
                                return
                            }
                            playersList.contains(playerName) -> return
                            else -> {
                                playersList.add(playerName)
                                playerNameView.text=null
                                playersListAdapter.notifyDataSetChanged()
                                return
                            }
                        }
                    }
                    nextButton.id -> {
                        if (playersList.isEmpty()) {
                            toastWithVibration(applicationContext, getString(R.string.hint_players_list_isEmpty),true)
                            return
                        } else {
                            val intent = Intent("org.oleggalimov.simplescorecalc.actions.game")
                            intent.putExtra("gameTitle", gameTitle)
                            intent.putExtra("playersList", playersList.toTypedArray())
                            startActivity(intent)
                            return
                        }

                    }
                    backButton.id -> this.finish()
                }
            }

        )
        addPlayerButton.setOnClickListener(onClickListenerImpl)
        backButton.setOnClickListener (onClickListenerImpl)
        nextButton.setOnClickListener (onClickListenerImpl)


    }
//    override fun onCreateContextMenu(contextMenu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
//        menuInflater.inflate(R.menu.context_main, contextMenu)
////        menu?.add(R.string.contextMenu_clear)
//        super.onCreateContextMenu(contextMenu, v, menuInfo)
//    }
//    //реагируем на нажатие контекстного меню
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        Toast.makeText(applicationContext, "Выбрано контекстное меню", Toast.LENGTH_SHORT).show()
//        return super.onContextItemSelected(item)
//    }

}