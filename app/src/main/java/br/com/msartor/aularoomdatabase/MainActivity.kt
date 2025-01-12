package br.com.msartor.aularoomdatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.com.msartor.aularoomdatabase.data.BancoDeDados
import br.com.msartor.aularoomdatabase.data.dao.UsuarioDao
import br.com.msartor.aularoomdatabase.data.model.Endereco
import br.com.msartor.aularoomdatabase.data.model.Usuario
import br.com.msartor.aularoomdatabase.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private val binding by lazy {   ActivityMainBinding.inflate(layoutInflater)   }
    private lateinit var bancoDeDados: BancoDeDados
    private lateinit var usuarioDao: UsuarioDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        bancoDeDados = BancoDeDados.getInstance(this)
        usuarioDao = bancoDeDados.getInstanceUsuarioDao()

        binding.btnSalvar.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val usuario = Usuario(
                0,
                nome,
                "teste@teste.com",
                "123456",
                52,
                82.0,
                Endereco("Carlos JG",61)
            )
            CoroutineScope(Dispatchers.IO).launch {
                usuarioDao.salvar(usuario)
            }

            binding.txtListaDeUsuarios.text = "salvar"
        }



        binding.btnAtualizar.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val id = binding.editId.text.toString().toInt()
            val usuario = Usuario(
                id,
                nome,
                "teste@teste.com",
                "123456",
                24,
                82.0,
                Endereco("Carlos J.Gon",61)
            )
            CoroutineScope(Dispatchers.IO).launch {
                usuarioDao.atualizat(usuario)
            }
            binding.txtListaDeUsuarios.text = "Remover"
            binding.txtListaDeUsuarios.text = "Atualizar"
        }
        binding.btnRemover.setOnClickListener {
            val id = binding.editId.text.toString().toInt()
            val usuario = Usuario(
                id,
                "Ruff111",
                "teste@teste.com",
                "123456",
                52,
                82.0,
                Endereco("Carlos JG",61)
            )
            CoroutineScope(Dispatchers.IO).launch {
                usuarioDao.remover(usuario)
            }
            binding.txtListaDeUsuarios.text = "Remover"
        }

        binding.btnListar.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val listaUsuario = usuarioDao.listar()
                var textUsuarios = ""
                listaUsuario.forEach {
                    textUsuarios += "\n${it.id}-${it.nome} [End:${it.endereco.rua.trim()},${it.endereco.numero} ]"
                }
                withContext( Dispatchers.Main) {
                    binding.txtListaDeUsuarios.text = textUsuarios
                }
            }

        }

        binding.btnFiltrar.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val textoPesquisa = binding.editNome.text.toString()
                val listaUsuario = usuarioDao.filtrar(textoPesquisa)
                var textUsuarios = ""
                listaUsuario.forEach {
                    textUsuarios += "\n${it.id}-${it.nome} [End:${it.endereco.rua.trim()},${it.endereco.numero} ]"
                }
                withContext( Dispatchers.Main) {
                    binding.txtListaDeUsuarios.text = textUsuarios
                }
            }

        }
    }
}