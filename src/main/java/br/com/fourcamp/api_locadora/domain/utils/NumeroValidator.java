package br.com.fourcamp.api_locadora.domain.utils;

public class NumeroValidator {
    public static boolean validarNumero(String numero){
        // verifica se número possui apenas números
        return numero != null && numero.matches("\\d{1,6}");
    }
}
