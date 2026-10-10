package org.semesperanca;

public class CalculadoraIR {

    public static double calcularIR(double renda) {
        if (renda < 0) {
            throw new IllegalArgumentException("Renda inválida");
        }
        double imposto = renda * 0.275;
        double desconto;
        if (renda <= 5000) {
            desconto = 1.0;
        } else if (renda <= 5500) {
            desconto = 0.75;
        } else if (renda <= 6000) {
            desconto = 0.50;
        } else if (renda <= 6500) {
            desconto = 0.25;
        } else if (renda <= 7350) {
            desconto = 0.10;
        } else {
            desconto = 0.0;
        }
        return imposto * (1 - desconto);
    }
}