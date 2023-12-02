package org.senai.ecommerce.controller;

import jakarta.validation.Valid;
import org.senai.ecommerce.entity.Usuario;
import org.senai.ecommerce.repositories.UsuarioRepository;
import org.senai.ecommerce.repositories.dto.AutenticacaoDTO;
import org.senai.ecommerce.repositories.dto.EntrarDTO;
import org.senai.ecommerce.repositories.dto.RegistroDTO;
import org.senai.ecommerce.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticar")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/entrar")
    public ResponseEntity entrar(@RequestBody @Valid AutenticacaoDTO dados){
        var nomeUsuarioSenha = new UsernamePasswordAuthenticationToken(dados.nomeUsuario(), dados.senha());
        var autenticacao = this.authenticationManager.authenticate((nomeUsuarioSenha));

        var token = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(new EntrarDTO(token));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody @Valid RegistroDTO dados){
        if (this.usuarioRepository.findByNomeUsuario(dados.nomeUsuario()) != null) return ResponseEntity.badRequest().build();
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dados.senha());
        Usuario novoUsuario = new Usuario(
                dados.nomeUsuario(),
                senhaCriptografada, dados.tipoUsuario()
        );

        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
