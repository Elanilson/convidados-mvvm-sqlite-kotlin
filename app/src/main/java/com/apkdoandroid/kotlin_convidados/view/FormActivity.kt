package com.apkdoandroid.kotlin_convidados.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apkdoandroid.kotlin_convidados.viewmodel.FormViewModel
import com.apkdoandroid.kotlin_convidados.R
import com.apkdoandroid.kotlin_convidados.databinding.ActivityFormBinding
import com.apkdoandroid.kotlin_convidados.model.Convidado


class FormActivity : AppCompatActivity() ,View.OnClickListener {

    private lateinit var binding: ActivityFormBinding
    private lateinit var viewModel : FormViewModel
    private var convidadoId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(FormViewModel::class.java)

        binding.buttonSalvar.setOnClickListener(this)
        binding.radioButtonPresente.isChecked = true




    observe()
    loadDate()



    }

    private fun observe() {
        viewModel.convidado.observe(this, Observer {
            binding.editTexNome.setText(it.nome)
            if(it.presenca){
                binding.radioButtonPresente.isChecked = true

            }else{
                binding.radioButtonAusente.isChecked = true
            }
        })

        viewModel.resposta.observe(this, Observer {
            if(it != ""){
                    Toast.makeText(applicationContext,it,Toast.LENGTH_SHORT)
                finish()

            }else{
                Toast.makeText(applicationContext,"falaha",Toast.LENGTH_SHORT)
            }

        })
    }

    private fun loadDate() {
        val bundle = intent.extras
        if(bundle != null){
            convidadoId = bundle.getInt("convidadoId")
            viewModel.get(convidadoId)
        }
    }

    override fun onClick(v: View) {
        if(v.id == R.id.buttonSalvar){
            val nome = binding.editTexNome.text.toString()
            val presenca = binding.radioButtonPresente.isChecked

            viewModel.salvar(Convidado(convidadoId,nome,presenca))

        }
    }
}