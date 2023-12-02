package org.senai.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nomeUsuario;
    private String senha;
    private TipoUsuario tipoUsuario;

    public Usuario(String nomeUsuario, String senha, TipoUsuario tipoUsuario){
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAutorizacoes(){
        if (this.tipoUsuario == TipoUsuario.ADMIN) return List.of(new SimpleGrantedAuthority("TIPO_ADMIN", new SimpleGrantedAuthority("TIPO_COMUM")));
        else return List.of(new SimpleGrantedAuthority("TIPO_COMUM"));
    }

    @Override
    public String getNomeUsuario(){
        return nomeUsuario;
    }

    @Override
    public boolean contaNaoExpirada(){
        return true;
    }

    @Override
    public boolean contaBloqueada(){
        return true;
    }

    @Override
    public boolean credenciaisExpiradas(){
        return true;
    }

    @Override
    public boolean contaAtiva(){
        return true;
    }
}
