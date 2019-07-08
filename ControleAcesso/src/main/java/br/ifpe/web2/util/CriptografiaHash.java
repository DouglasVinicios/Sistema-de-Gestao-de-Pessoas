package br.ifpe.web2.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.context.annotation.Bean;

public class CriptografiaHash {

	@Bean
	public String passwordEncoder(String senhaParaCriptografar)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
		byte messageDigestSenha[] = algorithm.digest(senhaParaCriptografar.getBytes("UTF-8"));

		StringBuilder hexStringSenha = new StringBuilder();
		for (byte b : messageDigestSenha) {
			hexStringSenha.append(String.format("%02X", 0xFF & b));
		}
		senhaParaCriptografar = hexStringSenha.toString();

		return senhaParaCriptografar;
	}
}
