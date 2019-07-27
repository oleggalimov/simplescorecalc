package org.oleggalimov.simplescorecalc.adapters

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import org.oleggalimov.simplescorecalc.R


/*
Мы должны создать класс, который расширяет RecyclerView.Adapter, который в качестве параметра принимает класс, который расширяет RecyclerView.ViewHolder.
    getItemCount() возвращает общее количество элементов списка. Значения списка передаются с помощью конструктора.
    onCreateViewHolder() создает новый объект ViewHolder всякий раз, когда RecyclerView нуждается в этом.
        Это тот момент, когда создаётся layout строки списка, передается объекту ViewHolder, и каждый дочерний view-компонент может быть найден и сохранен.
    onBindViewHolder() принимает объект ViewHolder и устанавливает необходимые данные для соответствующей строки во view-компоненте.
 */
class GameAdapter (private val playersList: MutableList<Pair<String, Int>>, private val parent: Activity) : RecyclerView.Adapter<GameAdapter.GameViewHolder>()  {
     override fun getItemCount(): Int {
        return playersList.size
    }
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val playerName = playersList[position].first
        val playerScore:Int = playersList[position].second
        holder.playerName?.text = playerName
        holder.playerScore?.text = playerScore.toString()


        holder.playerName?.setOnClickListener{
            try {
                val intent = Intent("org.oleggalimov.simplescorecalc.actions.changescore")
                intent.putExtra("player", playerName)
                intent.putExtra ("score", playerScore)
                intent.putExtra ("position", position)
                parent.startActivityForResult(intent,1)
            } catch (ex:Exception) {
                Log.e("onBindViewHolder", "error on remove element $position on list with ${playersList.size}")
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.player_list_item, parent, false)
        return GameViewHolder(itemView)
    }
    class GameViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
        var playerName: TextView? =null
        var playerScore: TextView?=null
        init {
            playerName = itemView.findViewById(R.id.onGamePlayerName)
            playerScore = itemView.findViewById(R.id.onGamePlayerScore)
        }
    }
    fun onResultActivity (requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            val name = data.getStringExtra("name")
            val score = data.getIntExtra("score", 0)
            val position = data.getIntExtra("position",0)
            playersList[position]= Pair(name,score)
            this.notifyDataSetChanged()
            return
        } else {
            return
        }
    }
    fun getActualPlayerList (): MutableList<Pair<String, Int>> {
        return playersList
    }
}