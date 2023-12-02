package org.senai.ecommerce.entity;

public enum TipoUsuario {

    ADMIN("admin"), COMUM("comum");

    private String tipoUsuario;

    TipoUsuario(String tipoUsuario){
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario(){
        return tipoUsuario;
    }
}
