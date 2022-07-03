package com.apkdoandroid.kotlin_convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apkdoandroid.kotlin_convidados.model.Convidado
import com.apkdoandroid.kotlin_convidados.repositorio.Repositorio

class TodosViewModel(application: Application) : AndroidViewModel(application) {

    private val  repositorio = Repositorio.getInstace(application.applicationContext)

    private val listaConvidados = MutableLiveData<List<Convidado>>()
    val convidados : LiveData<List<Convidado>> = listaConvidados

    fun getConvidados(){
        listaConvidados.value =repositorio.getAll()
    }
    fun getPresentes(){
        listaConvidados.value =repositorio.getPresentes()
    }
    fun getAusentes(){
        listaConvidados.value =repositorio.getAusentes()
    }

    fun delete(id: Int) {
        repositorio.delete(id)

    }

}