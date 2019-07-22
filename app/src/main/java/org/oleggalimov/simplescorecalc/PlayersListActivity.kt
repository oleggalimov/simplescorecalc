package org.oleggalimov.simplescorecalc

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PlayersListActivity : AppCompatActivity() {
    private lateinit var gameTitleView:TextView
    private lateinit var  playerNameView:TextView
    private lateinit var  addPlayerButton:Button
    private lateinit var  nextButton:Button
    private lateinit var  backButton:Button
    private lateinit var  playersListRecView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        val playersList = mutableListOf<String>()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_list)

        //получаем представления
        gameTitleView = findViewById(R.id.gameTitle)
        playerNameView = findViewById(R.id.playerName)
        addPlayerButton = findViewById(R.id.addPlayerButton)
        nextButton = findViewById(R.id.nextButton)
        backButton = findViewById(R.id.backButton)
        playersListRecView = findViewById(R.id.playersList)
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
                        if (playerNameView.text.isBlank()) {
                            Toast.makeText(applicationContext, getString(R.string.hint_playerNameBlank), Toast.LENGTH_SHORT).show()
                        } else {
                            playersList.add(playerNameView.text.toString())
                            playerNameView.text=null
                            playersListAdapter.notifyDataSetChanged()
                        }
                    }
                    nextButton.id -> TODO()
                    backButton.id -> this.finish()
                }
            }

        )
        addPlayerButton.setOnClickListener(onClickListenerImpl)
        backButton.setOnClickListener (onClickListenerImpl)


    }
    override fun onCreateContextMenu(contextMenu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menuInflater.inflate(R.menu.context_main, contextMenu)
//        menu?.add(R.string.contextMenu_clear)
        super.onCreateContextMenu(contextMenu, v, menuInfo)
    }
    //реагируем на нажатие контекстного меню
    override fun onContextItemSelected(item: MenuItem): Boolean {
        Toast.makeText(applicationContext, "Выбрано контекстное меню", Toast.LENGTH_SHORT).show()
        return super.onContextItemSelected(item)
    }

}