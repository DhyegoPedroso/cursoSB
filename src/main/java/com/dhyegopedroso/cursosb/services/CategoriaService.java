package com.dhyegopedroso.cursosb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhyegopedroso.cursosb.domain.Categoria;
import com.dhyegopedroso.cursosb.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria buscar(Integer id) {

		Optional<Categoria> obj = repository.findById(id);
		return obj.orElse(null);
	}

}
