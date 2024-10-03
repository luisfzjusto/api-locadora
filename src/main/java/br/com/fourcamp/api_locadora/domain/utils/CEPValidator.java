package br.com.fourcamp.api_locadora.domain.utils;

public class CEPValidator {
    public static boolean validarCEP(String cep){
        // verifica se cep está no formato 00000-000
        return cep != null && cep.matches("\\d{5}-\\d{3}");
    }
}
