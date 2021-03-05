package com.dhyegopedroso.cursosb.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dhyegopedroso.cursosb.domain.Cliente;
import com.dhyegopedroso.cursosb.repositories.ClienteRepository;
import com.dhyegopedroso.cursosb.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {

		Cliente cliente = clienteRepository.findByEmail(email);

		if (cliente == null) {
			throw new ObjectNotFoundException("E-Mail não encontrado");
		}

		String newPass = newPassword();
		cliente.setSenha(bCryptPasswordEncoder.encode(newPass));

		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {

		char[] vet = new char[10];

		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}

		return new String(vet);
	}

	private char randomChar() {

		int opt = rand.nextInt(3);

		if (opt == 0) { // Gerar um Digito
			return (char) (rand.nextInt(10) + 48);
		}

		if (opt == 1) { // Gerar uma Letra Maiuscula
			return (char) (rand.nextInt(26) + 48);
		}

		else { // Gerar uma Letra Minuscula
			return (char) (rand.nextInt(97) + 48);
		}

	}

}
