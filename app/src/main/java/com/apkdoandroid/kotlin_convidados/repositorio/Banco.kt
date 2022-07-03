package com.apkdoandroid.kotlin_convidados.repositorio

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.apkdoandroid.kotlin_convidados.constants.DataBaseConstants

class Banco(context: Context)
    : SQLiteOpenHelper(context, NOME, null, VERSAO) {

        companion object{
            private const val NOME = "bancoDb"
            private const val VERSAO = 1
        }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table "+DataBaseConstants.TABELA.NOME+" ("+DataBaseConstants.TABELA.COLUNAS.ID+" integer primary key autoincrement," +
                DataBaseConstants.TABELA.COLUNAS.NOME+"  text," +
                DataBaseConstants.TABELA.COLUNAS.PRESENCA+" integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}