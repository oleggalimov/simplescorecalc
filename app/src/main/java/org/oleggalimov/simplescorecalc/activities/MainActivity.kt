package org.oleggalimov.simplescorecalc.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import org.oleggalimov.simplescorecalc.R
import org.oleggalimov.simplescorecalc.utilities.toastWithVibration


class MainActivity : AppCompatActivity() {
    private var isBackPressed: Long = 0
    private var gameTitleText = "NoName"
    private lateinit var gameTitle: TextView
    private lateinit var nextButton: Button
    private lateinit var mainConstraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //

        gameTitle = findViewById(R.id.gameTitle)
        nextButton = findViewById(R.id.nextButton)
        mainConstraintLayout = findViewById(R.id.mainConstraintLayout)
        //регистрируем элемент для контекстного меню
        registerForContextMenu(gameTitle)
        //еще вариант того же
//        gameTitle.setOnCreateContextMenuListener(this)

        nextButton.setOnClickListener {
            if (gameTitle.text.isBlank()) {
                toastWithVibration(applicationContext, getString(R.string.hint_blankGameTitle), true)
            } else {
                gameTitleText = gameTitle.text.toString()
                //очищаем view
//                mainConstraintLayout.removeView(gameTitle)
                //создаем анимацию
//                val animation = AnimationUtils.loadAnimation(this, R.anim.myscale)
//                gameTitle.startAnimation(animation)

                //запускаем вторую активити
                val intent = Intent("org.oleggalimov.simplescorecalc.actions.createplayerslist")
                intent.putExtra("gameTitle", gameTitleText)
                startActivity(intent)
            }
        }
    }
    //создаем меню
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
    //реагируем на выбор элемента меню
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
//        return super.onOptionsItemSelected(item)
//    }
    //Создаем контекстное меню
//    override fun onCreateContextMenu(contextMenu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
//        menuInflater.inflate(R.menu.context_main, contextMenu)
////        menu?.add(R.string.contextMenu_clear)
//        super.onCreateContextMenu(contextMenu, v, menuInfo)
//    }
//    //реагируем на нажатие контекстного меню
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        this.gameTitleText=""
//        this.gameTitle.text=null
//        return super.onContextItemSelected(item)
//    }


    override fun onBackPressed() {
        if (isBackPressed + 2000 > System.currentTimeMillis()) {
            System.exit(0)
        } else {
            toastWithVibration(this, getString(R.string.hint_exit), false)
        }
        isBackPressed = System.currentTimeMillis()
    }
}
