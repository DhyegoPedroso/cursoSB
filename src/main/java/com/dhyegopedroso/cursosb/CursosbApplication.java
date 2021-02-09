package com.dhyegopedroso.cursosb;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dhyegopedroso.cursosb.domain.Categoria;
import com.dhyegopedroso.cursosb.domain.Cidade;
import com.dhyegopedroso.cursosb.domain.Cliente;
import com.dhyegopedroso.cursosb.domain.Endereco;
import com.dhyegopedroso.cursosb.domain.Estado;
import com.dhyegopedroso.cursosb.domain.ItemPedido;
import com.dhyegopedroso.cursosb.domain.Pagamento;
import com.dhyegopedroso.cursosb.domain.PagamentoComBoleto;
import com.dhyegopedroso.cursosb.domain.PagamentoComCartao;
import com.dhyegopedroso.cursosb.domain.Pedido;
import com.dhyegopedroso.cursosb.domain.Produto;
import com.dhyegopedroso.cursosb.domain.enums.EstadoPagamento;
import com.dhyegopedroso.cursosb.domain.enums.TipoCliente;
import com.dhyegopedroso.cursosb.repositories.CategoriaRepository;
import com.dhyegopedroso.cursosb.repositories.CidadeRepository;
import com.dhyegopedroso.cursosb.repositories.ClienteRepository;
import com.dhyegopedroso.cursosb.repositories.EnderecoRepository;
import com.dhyegopedroso.cursosb.repositories.EstadoRepository;
import com.dhyegopedroso.cursosb.repositories.ItemPedidoRepository;
import com.dhyegopedroso.cursosb.repositories.PagamentoRepository;
import com.dhyegopedroso.cursosb.repositories.PedidoRepository;
import com.dhyegopedroso.cursosb.repositories.ProdutoRepository;

@SpringBootApplication
public class CursosbApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursosbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Santa Catarina");
		Estado est2 = new Estado(null, "Paraná");
		Estado est3 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Florianópolis", est1);
		Cidade c2 = new Cidade(null, "São José", est1);
		Cidade c3 = new Cidade(null, "Palhoça", est1);
		Cidade c4 = new Cidade(null, "Curitiba", est2);
		Cidade c5 = new Cidade(null, "São Paulo", est3);
		Cidade c6 = new Cidade(null, "Campinas", est3);

		est1.getCidades().addAll(Arrays.asList(c1, c2, c3));
		est2.getCidades().addAll(Arrays.asList(c4));
		est3.getCidades().addAll(Arrays.asList(c5, c6));

		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));

		Cliente cli1 = new Cliente(null, "Dhyego Pedroso", "dhyego.pedroso@outlook.com", "36378912377",
				TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Mathias Schell", "132", "Cond. Bem-Ti-Vi AP403A", "Sertão do Maruim",
				"88122400", cli1, c2);
		Endereco e2 = new Endereco(null, "Rua Joaquim Carneiro", "460", "Casa", "Capoeiras", "38777012", cli1, c1);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		Pedido ped1 = new Pedido(null, sdf.parse("20/01/2021 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("01/02/2021 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("28/02/2021 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
