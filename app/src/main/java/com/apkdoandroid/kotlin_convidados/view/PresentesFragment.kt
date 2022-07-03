package com.apkdoandroid.kotlin_convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.apkdoandroid.kotlin_convidados.databinding.FragmentPresentesBinding
import com.apkdoandroid.kotlin_convidados.view.adapter.ConvidadoAdapter
import com.apkdoandroid.kotlin_convidados.view.listener.OnConvidadoListener
import com.apkdoandroid.kotlin_convidados.viewmodel.TodosViewModel

class PresentesFragment : Fragment() {

    private var _binding: FragmentPresentesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: TodosViewModel
    private val adapter = ConvidadoAdapter()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(TodosViewModel::class.java)

        _binding = FragmentPresentesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerViewAll.layoutManager = LinearLayoutManager(context)

        binding.recyclerViewAll.adapter = adapter

        val listener = object : OnConvidadoListener {
            override fun onCLick(id: Int) {
                val intent = Intent(context,FormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("convidadoId",id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getConvidados()
            }

        }

        adapter.attackOnConvidadoListener(listener)

        viewModel.getPresentes()

        observer()


        return root
    }
    private fun observer() {
        viewModel.convidados.observe(viewLifecycleOwner) {
            adapter.updateConvidados(it)

        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPresentes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}