package br.com.msartor.aularoomdatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.com.msartor.aularoomdatabase.data.BancoDeDados
import br.com.msartor.aularoomdatabase.data.dao.ClientePedidoDao
import br.com.msartor.aularoomdatabase.data.dao.EnderecoDao
import br.com.msartor.aularoomdatabase.data.dao.PessoaComputadorDao
import br.com.msartor.aularoomdatabase.data.dao.ProdutoDao
import br.com.msartor.aularoomdatabase.data.dao.UsuarioDao
import br.com.msartor.aularoomdatabase.data.entity.Cliente
import br.com.msartor.aularoomdatabase.data.entity.Computador
import br.com.msartor.aularoomdatabase.data.entity.Endereco
import br.com.msartor.aularoomdatabase.data.entity.Pedido
import br.com.msartor.aularoomdatabase.data.entity.Pessoa
import br.com.msartor.aularoomdatabase.data.entity.PessoaComputador
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
    private lateinit var pessaoComputadorDao: PessoaComputadorDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        bancoDeDados = BancoDeDados.getInstance(this)
        usuarioDao = bancoDeDados.usuarioDao
        enderecoDao = bancoDeDados.enderecoDao
        produtoDao = bancoDeDados.produtoDao
        clientePedidoDao = bancoDeDados.clientePedidoDao
        pessaoComputadorDao = bancoDeDados.pessoaComputadorDao

        /*----------------------------------------------
        | Botões                                       |
        -----------------------------------------------*/
        binding.btnSalvar.setOnClickListener {
            // Exemplo - Salvar Usuario
            //salvarUsuario()

            // Exemplo - Salvar Produto (one to one)
            //salvarProduto()

            // Exemplo - Salvar Cliente e Pedido  (one to Many)
            //salvarClientePedido()

            // Exemplo - Salvar Pessoa e Computador  (Many to Many)
            salvarPessoaCompurador()

            binding.txtListaDeUsuarios.text = "Salvo"
        }

        binding.btnAtualizar.setOnClickListener {
            atualizarUsuario()

            binding.txtListaDeUsuarios.text = "Atualizado"
        }

        binding.btnRemover.setOnClickListener {
            removerUsuario()

            binding.txtListaDeUsuarios.text = "Removido"
        }

        binding.btnListar.setOnClickListener{
            //listarUsuario()
            //listarProdutos()
            //listarClientesComPedidos()
            listarPessoaComComputadores()
        }

        binding.btnFiltrar.setOnClickListener{
            listarUsuarioFiltro()
        }
    }

    /*------------------------------------------------
    | Metodos remover                                |
     -----------------------------------------------*/
    private fun removerUsuario() {
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

    /*------------------------------------------------
    | Metodos atualizar                              |
     -----------------------------------------------*/
    private fun atualizarUsuario() {
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
        binding.txtListaDeUsuarios.text = "Atualizar"
    }

    /*------------------------------------------------
    | Metodos Listar                                 |
     -----------------------------------------------*/
    private fun listarPessoaComComputadores(){
        CoroutineScope(Dispatchers.IO).launch {
            val listaPessoasComComputadores = pessaoComputadorDao.listarPessoaComComputadores()
            var textPessoas = ""
            listaPessoasComComputadores.forEach {
                textPessoas += "\n* ${it.pessoa.id}-${it.pessoa.nome}"
                it.computadores.forEach { it2->
                    textPessoas += "\n   # (${it2.id})${it2.marca} - R$ ${it2.modelo}"
                }
                textPessoas += "\n----------------------------------------"
            }
            withContext( Dispatchers.Main) {
                binding.txtListaDeUsuarios.text = textPessoas
            }
        }
    }

    private fun listarClientesComPedidos() {
        CoroutineScope(Dispatchers.IO).launch {
            val listaClientes = clientePedidoDao.listarClienteComPedidod()
            var textClientes = ""
            listaClientes.forEach {
                textClientes += "\n* ${it.cliente.id}-${it.cliente.nome}"
                it.pedidos.forEach { it2->
                    textClientes += "\n   # (${it2.id})${it2.produto} - R$ ${it2.preco}"
                }
                textClientes += "\n----------------------------------------"
            }
            withContext( Dispatchers.Main) {
                binding.txtListaDeUsuarios.text = textClientes
            }
        }
    }

    private fun listarProdutos() {
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

    private fun listarUsuario() {

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

    }

    /*-----------------------------------------------
    | Metodos Listar com Filtro                     |
    -----------------------------------------------*/
    private fun listarUsuarioFiltro() {
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


    /*------------------------------------------------
    | Metodos salvar                                 |
     -----------------------------------------------*/
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

    private fun salvarPessoaCompurador() {
        val nome = binding.editNome.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            val pessoa = Pessoa(0,nome,"M")
            val pessoaId = pessaoComputadorDao.salvarPessoa(pessoa)

            val computador1 = Computador(0,"Computador1","Dell")
            val computadorId1 = pessaoComputadorDao.salvarComputador(computador1)

            val computador2 = Computador(0,"Computador2","Avell")
            val computadorId2 = pessaoComputadorDao.salvarComputador(computador2)

            val pessoaComputador1 = PessoaComputador(pessoaId,computadorId1)
            val pessoaComputador2 = PessoaComputador(pessoaId,computadorId2)

            pessaoComputadorDao.salvarPessoaComputador(pessoaComputador1)
            pessaoComputadorDao.salvarPessoaComputador(pessoaComputador2)

        }
    }
}