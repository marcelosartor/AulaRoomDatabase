package br.com.msartor.aularoomdatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.msartor.aularoomdatabase.data.BancoDeDados
import br.com.msartor.aularoomdatabase.data.dao.UsuarioDao
import br.com.msartor.aularoomdatabase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy {   ActivityMainBinding.inflate(layoutInflater)   }
    private lateinit var bancoDeDados: BancoDeDados
    private lateinit var usuarioDao: UsuarioDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        bancoDeDados = BancoDeDados.getInstance(this)
        usuarioDao = bancoDeDados.recuperarUsuarioDao()


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