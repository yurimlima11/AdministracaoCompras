package com.yurimiranda.administracaocompras.services;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.yurimiranda.administracaocompras.entities.PagamentoBoleto;

@Service
public class BoletoService {

	public void preencherPagBoleto(PagamentoBoleto pagto, Date instantePedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instantePedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
