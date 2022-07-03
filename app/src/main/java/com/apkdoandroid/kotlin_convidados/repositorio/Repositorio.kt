package com.apkdoandroid.kotlin_convidados.repositorio

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.apkdoandroid.kotlin_convidados.constants.DataBaseConstants
import com.apkdoandroid.kotlin_convidados.model.Convidado
import java.lang.Exception

class Repositorio private constructor(context: Context){

    private val banco = Banco(context)

    companion object{
        private lateinit var repositorio: Repositorio

        fun getInstace(context: Context) : Repositorio {
            if(!::repositorio.isInitialized){
                repositorio = Repositorio(context)
            }
            return repositorio
        }
    }

    fun insert(convidado: Convidado) :Boolean{
      return  try {
            val db = banco.readableDatabase

            val presenca = if(convidado.presenca) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.TABELA.COLUNAS.NOME,convidado.nome)
            values.put(DataBaseConstants.TABELA.COLUNAS.PRESENCA,presenca)

            db.insert(DataBaseConstants.TABELA.NOME,null,values)

            true
        }catch (e:Exception){
            false

        }

    }

    fun update(convidado: Convidado) : Boolean{
        return try {

            val db = banco.writableDatabase

            val presenca = if(convidado.presenca) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.TABELA.COLUNAS.NOME,convidado.nome)
            values.put(DataBaseConstants.TABELA.COLUNAS.PRESENCA,presenca)

            val where = DataBaseConstants.TABELA.COLUNAS.ID+" =?"
            val whereArgs = arrayOf(convidado.id.toString())

            db.update(DataBaseConstants.TABELA.NOME,values,where,whereArgs)
            true
        }catch (e:Exception){
            false
        }
    }

    fun delete(convidadoId: Int) : Boolean{
        return try {

            val db = banco.writableDatabase

            val where = DataBaseConstants.TABELA.COLUNAS.ID+" =?"
            val whereArgs = arrayOf(convidadoId.toString())

            db.delete(DataBaseConstants.TABELA.NOME,where,whereArgs)
            true
        }catch (e:Exception){
            false
        }
    }

    @SuppressLint("Range")
    fun getAll() : List<Convidado>{
        val convidados = mutableListOf<Convidado>()

        try{
            val db = banco.readableDatabase
            val colunas = arrayOf(
                DataBaseConstants.TABELA.COLUNAS.ID,
                DataBaseConstants.TABELA.COLUNAS.NOME,
                DataBaseConstants.TABELA.COLUNAS.PRESENCA,
            )

            val cursor = db.query(
                DataBaseConstants.TABELA.NOME,colunas,null,null,null,null,null,null
            )

            if(cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TABELA.COLUNAS.ID))
                    val nome = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TABELA.COLUNAS.NOME))
                    val presenca = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TABELA.COLUNAS.PRESENCA))

                    convidados.add(Convidado(id,nome,presenca == 1))

                }
            }

            cursor.close()


        }catch (e:Exception){
            return  convidados

        }
        return  convidados
    }

    @SuppressLint("Range")
    fun getPresentes() : List<Convidado>{
        val convidados = mutableListOf<Convidado>()

        try{
            val db = banco.readableDatabase
            val colunas = arrayOf(
                DataBaseConstants.TABELA.COLUNAS.ID,
                DataBaseConstants.TABELA.COLUNAS.NOME,
                DataBaseConstants.TABELA.COLUNAS.PRESENCA,
            )

            val where = DataBaseConstants.TABELA.COLUNAS.PRESENCA+" =?"
            val whereArgs = arrayOf("1")

            val cursor = db.query(
                DataBaseConstants.TABELA.NOME,colunas,where,whereArgs,null,null,null,null
            )

            if(cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TABELA.COLUNAS.ID))
                    val nome = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TABELA.COLUNAS.NOME))
                    val presenca = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TABELA.COLUNAS.PRESENCA))

                    convidados.add(Convidado(id,nome,presenca == 1))

                }
            }

            cursor.close()


        }catch (e:Exception){
            return  convidados

        }
        return  convidados
    }

    @SuppressLint("Range")
    fun getAusentes() : List<Convidado>{
        val convidados = mutableListOf<Convidado>()

        try{
            val db = banco.readableDatabase
            val colunas = arrayOf(
                DataBaseConstants.TABELA.COLUNAS.ID,
                DataBaseConstants.TABELA.COLUNAS.NOME,
                DataBaseConstants.TABELA.COLUNAS.PRESENCA,
            )

            val where = DataBaseConstants.TABELA.COLUNAS.PRESENCA+" =?"
            val whereArgs = arrayOf("0")

            val cursor = db.query(
                DataBaseConstants.TABELA.NOME,colunas,where,whereArgs,null,null,null,null
            )

            if(cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TABELA.COLUNAS.ID))
                    val nome = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TABELA.COLUNAS.NOME))
                    val presenca = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TABELA.COLUNAS.PRESENCA))

                    convidados.add(Convidado(id,nome,presenca == 1))

                }
            }

            cursor.close()


        }catch (e:Exception){
            return  convidados

        }
        return  convidados
    }

    fun get(id: Int) : Convidado?{
        var convidado :Convidado? = null

        try{
            val db = banco.readableDatabase
            val colunas = arrayOf(
                DataBaseConstants.TABELA.COLUNAS.ID,
                DataBaseConstants.TABELA.COLUNAS.NOME,
                DataBaseConstants.TABELA.COLUNAS.PRESENCA,
            )

            val where = DataBaseConstants.TABELA.COLUNAS.ID+" =?"
            val whereArgs = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.TABELA.NOME,colunas,where,whereArgs,null,null,null,null
            )

            if(cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){

                    val nome = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TABELA.COLUNAS.NOME))
                    val presenca = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TABELA.COLUNAS.PRESENCA))

                    convidado = Convidado(id,nome,presenca == 1)

                }
            }

            cursor.close()


        }catch (e:Exception){
            return  convidado

        }
        return  convidado

    }
}