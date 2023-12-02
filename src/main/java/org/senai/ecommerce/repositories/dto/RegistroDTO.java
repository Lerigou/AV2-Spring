package org.senai.ecommerce.repositories.dto;

import org.senai.ecommerce.entity.TipoUsuario;

public record RegistroDTO(String nomeUsuario, String senha, TipoUsuario tipoUsuario) {
}
