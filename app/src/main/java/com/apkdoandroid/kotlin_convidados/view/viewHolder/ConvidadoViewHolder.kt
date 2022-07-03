package com.apkdoandroid.kotlin_convidados.view.viewHolder

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apkdoandroid.kotlin_convidados.R
import com.apkdoandroid.kotlin_convidados.model.Convidado
import com.apkdoandroid.kotlin_convidados.view.listener.OnConvidadoListener

class ConvidadoViewHolder(itemView: View, private val  onlistene : OnConvidadoListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(convidado : Convidado){
       val nome = itemView.findViewById<TextView>(R.id.textView4Nome)
        nome.text = convidado.nome

        nome.setOnClickListener{
            onlistene.onCLick(convidado.id)
        }
        nome.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza ?")
                .setPositiveButton("Sim"
                ) { dialog, which -> onlistene.onDelete(convidado.id) }
                .setNegativeButton("Não",null)
                .show()

            false
        }


    }
}