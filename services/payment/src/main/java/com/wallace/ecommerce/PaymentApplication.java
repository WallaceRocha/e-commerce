package com.wallace.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*
 * Isso aqui ajuda a observar mudanças em entidades, me permitindo saber quando algo foi criado ou modificado
 * Verificar a classe Payment.java para entender melhor
 */
@EnableJpaAuditing
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

}
