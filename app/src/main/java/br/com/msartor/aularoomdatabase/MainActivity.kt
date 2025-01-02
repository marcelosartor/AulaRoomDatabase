package br.com.msartor.aularoomdatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.msartor.aularoomdatabase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        binding.btnListar.setOnClickListener{
            binding.txtListaDeUsuarios.text = "Lista de Usuarios"
        }
        binding.btnSalvar.setOnClickListener {
            binding.txtListaDeUsuarios.text = "salvar"
        }
        binding.btnAtualizar.setOnClickListener {
            binding.txtListaDeUsuarios.text = "Atualizar"
        }
        binding.btnRemover.setOnClickListener {
            binding.txtListaDeUsuarios.text = "Remover"
        }
    }
}