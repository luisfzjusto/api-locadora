package br.com.fourcamp.api_locadora.domain.utils;

import br.com.fourcamp.api_locadora.domain.enums.Categoria;

public class CategoriaValidator {
    public static boolean validarCategoria(String categoria){
        try{
            Categoria.valueOf(categoria.toUpperCase());
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }
}
