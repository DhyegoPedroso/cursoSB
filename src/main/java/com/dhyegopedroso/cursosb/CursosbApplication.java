package com.dhyegopedroso.cursosb;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dhyegopedroso.cursosb.domain.Categoria;
import com.dhyegopedroso.cursosb.domain.Cidade;
import com.dhyegopedroso.cursosb.domain.Estado;
import com.dhyegopedroso.cursosb.domain.Produto;
import com.dhyegopedroso.cursosb.repositories.CategoriaRepository;
import com.dhyegopedroso.cursosb.repositories.CidadeRepository;
import com.dhyegopedroso.cursosb.repositories.EstadoRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(CursosbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

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

	}

}
