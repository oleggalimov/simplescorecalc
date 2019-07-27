package org.oleggalimov.simplescorecalc.adapters

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
class Adapter (private val playersList: MutableList<String>) : RecyclerView.Adapter<Adapter.ViewHolder>()  {

    override fun getItemCount(): Int {
        return playersList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textView?.text = playersList[position]
        holder.textView?.setOnClickListener{
            try {
                playersList.removeAt(position)
                this.notifyDataSetChanged()
            } catch (ex:Exception) {
                Log.e("onBindViewHolder", "error on remove element $position on list with ${playersList.size}")
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
        var textView: TextView? =null
        init {
            textView = itemView.findViewById(R.id.player)
        }
    }

}