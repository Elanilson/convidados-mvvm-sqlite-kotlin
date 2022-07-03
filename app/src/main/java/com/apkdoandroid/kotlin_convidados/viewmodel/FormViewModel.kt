package com.apkdoandroid.kotlin_convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apkdoandroid.kotlin_convidados.model.Convidado
import com.apkdoandroid.kotlin_convidados.repositorio.Repositorio

class FormViewModel(application: Application) : AndroidViewModel(application) {
    private  val repositorio = Repositorio.getInstace(application)

    private val mConvidado = MutableLiveData<Convidado>()
    val convidado : LiveData<Convidado>  = mConvidado

    private val mRespota = MutableLiveData<String>()
    val resposta : LiveData<String>  = mRespota

    fun salvar(convidado: Convidado){
        if(convidado.id == 0){
            if(repositorio.insert(convidado)){
                mRespota.value = "Inserido com sucesso"
            }else{
                mRespota.value = ""
            }

        }else{
            if( repositorio.update(convidado)){
                mRespota.value = "Atualizado com sucesso"
            }else{
                mRespota.value = ""
            }
        }
    }

    fun get(id: Int){
        mConvidado.value = repositorio.get(id)
    }
}