package com.apkdoandroid.kotlin_convidados.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apkdoandroid.kotlin_convidados.R
import com.apkdoandroid.kotlin_convidados.model.Convidado
import com.apkdoandroid.kotlin_convidados.view.listener.OnConvidadoListener
import com.apkdoandroid.kotlin_convidados.view.viewHolder.ConvidadoViewHolder

class ConvidadoAdapter : RecyclerView.Adapter<ConvidadoViewHolder>() {
    private var convidados : List<Convidado> = listOf()
    private lateinit var listener : OnConvidadoListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConvidadoViewHolder {
        val layout : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quest_row,parent,false)
        return ConvidadoViewHolder(layout,listener)
    }

    override fun onBindViewHolder(holder: ConvidadoViewHolder, position: Int) {
        holder.bind(convidados.get(position))
    }

    override fun getItemCount(): Int {
        return convidados.count()
    }

    fun updateConvidados(convidados: List<Convidado>) {
        this.convidados = convidados
        notifyDataSetChanged()

    }
    fun attackOnConvidadoListener(listener: OnConvidadoListener){
        this.listener = listener

    }
}