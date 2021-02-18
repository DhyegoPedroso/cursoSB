package com.dhyegopedroso.cursosb.services;

import org.springframework.mail.SimpleMailMessage;

import com.dhyegopedroso.cursosb.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);

}
