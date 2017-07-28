package br.com.assertsistemas.util;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.math.NumberUtils;

public class Validacao {

	public static void main(String[] args) {

	}

	public boolean validNomeSobrenome(String valor) {
		if (valor == null || valor.isEmpty() == true) {
			return false;
		}

		char[] numeros = "1,2,3,4,5,6,7,8,9,0".toCharArray();
		char[] value = valor.toCharArray();

		for (int i = 0; i < value.length; i++) {
			char caracter = value[i];

			for (int j = 0; j < numeros.length; j++) {
				if (caracter == numeros[j]) {
					return false;
				}
			}
		}

		return true;
	}

	public boolean validCPF(String i) {
		try {
			String parseValue = i.replaceAll("\\/", "").replaceAll("\\.", "");
			return (parseValue.length() == 11) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean validIntLong(String i) {
		return NumberUtils.isNumber(i);
	}

	public boolean validChar(String i) {
		int tamanho = i.length();
		String genero = i.toLowerCase();

		if (tamanho == 1) {
			if (genero.contains("m") || genero.contains("f")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean validDouble(String i) {
		try {
			String parseValue = i.replace("\\,", ".");
			Double.valueOf(parseValue);
			if (parseValue.contains(".")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return (false);
		}
	}

	public String repeticao(String key) {
		int tentando = 0;
		while (tentando < 5) {
			tentando++;
			JOptionPane.showInputDialog("Erro no sistema.");
			String sair = JOptionPane.showInputDialog("Sair do programa? S/N");
			sair = sair.toUpperCase();

			if (sair.contains("S")) {
				System.exit(0);
			}

			String parse = this.MetodoInputMessage(key);
			if (parse != null && parse.isEmpty() == false) {
				return parse;
			}

			if (tentando == 4) {
				JOptionPane.showInputDialog("Você tem apenas mais uma tentativa antes do bloqueio.");

				if (tentando == 5) {
					JOptionPane.showInputDialog("Sistema encerrado.");
					System.exit(0);
				}
			}
		}
		return null;
	}

	private String MetodoInputMessage(String key) {
		String valor = "";
		switch (key) {
		case "nome":
			valor = JOptionPane.showInputDialog("Insira o nome (somente letras são permitidas):");
			if (validNomeSobrenome(valor)) {
				return valor;
			}
		case "sexo":
			valor = JOptionPane.showInputDialog("Insira o seu sexo somente M/F]:");
			if (validChar(valor)) {
				return valor;
			}
			break;
		case "idade":
			valor = JOptionPane.showInputDialog("Insira a sua idade (somente números inteiros):");
			if (validIntLong(valor)) {
				return valor;
			}
			break;
		case "matricula":
			valor = JOptionPane.showInputDialog("Insira a matrícula somente em números:");
			if (validIntLong(valor)) {
				return valor;
			}
			break;
		case "qualificacao":
			valor = JOptionPane.showInputDialog("Insira o título, somente letras!");
			if (validNomeSobrenome(valor)) {
				return valor;
			}
		}
		return null;
	}
}