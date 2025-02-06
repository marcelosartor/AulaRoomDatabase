package br.com.msartor.aularoomdatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.com.msartor.aularoomdatabase.data.BancoDeDados
import br.com.msartor.aularoomdatabase.data.dao.ClientePedidoDao
import br.com.msartor.aularoomdatabase.data.dao.EnderecoDao
import br.com.msartor.aularoomdatabase.data.dao.ProdutoDao
import br.com.msartor.aularoomdatabase.data.dao.UsuarioDao
import br.com.msartor.aularoomdatabase.data.entity.Cliente
import br.com.msartor.aularoomdatabase.data.entity.Endereco
import br.com.msartor.aularoomdatabase.data.entity.Pedido
import br.com.msartor.aularoomdatabase.data.entity.Produto
import br.com.msartor.aularoomdatabase.data.entity.ProdutoDetalhe
//import br.com.msartor.aularoomdatabase.data.model.Endereco
import br.com.msartor.aularoomdatabase.data.entity.Usuario
import br.com.msartor.aularoomdatabase.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


class MainActivity : AppCompatActivity() {
    private val binding by lazy {   ActivityMainBinding.inflate(layoutInflater)   }
    private lateinit var bancoDeDados: BancoDeDados
    private lateinit var usuarioDao: UsuarioDao
    private lateinit var enderecoDao: EnderecoDao
    private lateinit var produtoDao: ProdutoDao
    private lateinit var clientePedidoDao: ClientePedidoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        bancoDeDados = BancoDeDados.getInstance(this)
        usuarioDao = bancoDeDados.usuarioDao
        enderecoDao = bancoDeDados.enderecoDao
        produtoDao = bancoDeDados.produtoDao
        clientePedidoDao = bancoDeDados.clientePedidoDao


        binding.btnSalvar.setOnClickListener {
            // Exemplo - Salvar Usuario
            //salvarUsuario()

            // Exemplo - Salvar Produto (one to one)
            //salvarProduto()

            // Exemplo - Salvar Cliente e Pedido  (one to Many)
            salvarClientePedido()


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
                //Endereco("Carlos J.Gon",61),
                LocalDate.now(),
                LocalTime.now(),
                LocalDateTime.now()

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
                //Endereco("Carlos JG",61),
                LocalDate.now(),
                LocalTime.now(),
                LocalDateTime.now()
            )
            CoroutineScope(Dispatchers.IO).launch {
                usuarioDao.remover(usuario)
            }
            binding.txtListaDeUsuarios.text = "Remover"
        }

        binding.btnListar.setOnClickListener{
            /*
            CoroutineScope(Dispatchers.IO).launch {
                val listaUsuario = usuarioDao.listar()
                var textUsuarios = ""
                listaUsuario.forEach {
                    textUsuarios += "\n${it.id}-${it.nome}"+
                            //"\n[End:${it.endereco.rua.trim()},${it.endereco.numero} ]" +
                            "\nData: ${it.date}" +
                            "\nTime: ${it.time}" +
                            "\nDataTime: ${it.dateTime}" +
                            "\n----------------------------------------"
                }
                withContext( Dispatchers.Main) {
                    binding.txtListaDeUsuarios.text = textUsuarios
                }
            }
            */
            CoroutineScope(Dispatchers.IO).launch {
                val listaProdutos = produtoDao.listarProdutosEProdutoDetalhe()
                var textProdutos = ""
                listaProdutos.forEach {
                      textProdutos += "\n${it.produto.id}-${it.produto.nome}"+
                            "\nPreço: ${it.produto.preco}" +
                            "\nMarca: ${it.produtoDetalhe.marca}" +
                            "\nDescricao: ${it.produtoDetalhe.descricao}" +
                            "\n----------------------------------------"
                }
                withContext( Dispatchers.Main) {
                    binding.txtListaDeUsuarios.text = textProdutos
                }
            }


        }

        binding.btnFiltrar.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val textoPesquisa = binding.editNome.text.toString()
                val listaUsuario = usuarioDao.filtrar(textoPesquisa)
                var textUsuarios = ""
                listaUsuario.forEach {
                    textUsuarios += "\n${it.id}-${it.nome}" +
                            //"\n[End:${it.endereco.rua.trim()},${it.endereco.numero} ]" +
                            "\nData: ${it.date}" +
                            "\n----------------------------------------"
                }
                withContext( Dispatchers.Main) {
                    binding.txtListaDeUsuarios.text = textUsuarios
                }
            }

        }
    }

    private fun salvarUsuario() {
        val nome = binding.editNome.text.toString()
        val usuario = Usuario(
            0,
            nome,
            "teste@teste.com",
            "123456",
            52,
            82.0,
            //Endereco("Carlos JG",61),
            LocalDate.now(),
            LocalTime.now(),
            LocalDateTime.now()
        )
        val endereco = Endereco(
            0, "Rua tal", 20
        )

        CoroutineScope(Dispatchers.IO).launch {
            usuarioDao.salvar(usuario)
            enderecoDao.salvar(endereco)
        }
    }

    private fun salvarProduto() {
        val nome = binding.editNome.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            val produto = Produto(
                0,
                nome,
                preco = 1200.90
            )
            val produtoId = produtoDao.salvarProduto(produto)

            val produtoDetalhe = ProdutoDetalhe(
                0,
                produtoId,
                "Marca ${produtoId}",
                "Produto ${produtoId}"
            )

            val produtoDetalheId = produtoDao.salvarProdutoDetalhe(produtoDetalhe)
        }
    }

    private fun salvarClientePedido() {
        val nome = binding.editNome.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            val cliente = Cliente(0,nome,"--")
            val clienteId = clientePedidoDao.salvarCliente(cliente)

            repeat(3){numero->
                val pedido = Pedido(0,clienteId,"Produto${numero}", 200.90 + (150*numero.toDouble()))
                clientePedidoDao.salvarPedido(pedido)
            }
        }
    }
}