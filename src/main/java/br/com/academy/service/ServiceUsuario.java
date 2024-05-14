package br.com.academy.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.Exceptions.CriptoExistsException;
import br.com.academy.Exceptions.EmailExistsException;
import br.com.academy.Exceptions.UserNameExistsException;
import br.com.academy.dao.UsuarioDao;
import br.com.academy.model.Usuario;
import br.com.academy.util.Util;


@Service
public class ServiceUsuario {

    @Autowired
    private UsuarioDao usuarioRepositorio;

    public void salvarUsuario(Usuario user) throws Exception {

        try {
            if(usuarioRepositorio.findByEmail(user.getEmail()) != null ) {
                throw new EmailExistsException("Já existe um email cadastro para: " + user.getEmail());
            }

            if (usuarioRepositorio.findByUserName(user.getUserName()) != null) {
                throw new UserNameExistsException("O nome " + user.getUserName() + " ja está em uso");
            }

            user.setSenha(Util.md5(user.getSenha()));

        } catch (NoSuchAlgorithmException e) {
            
            throw new CriptoExistsException("Erro na criptografia da senha");
        }

        usuarioRepositorio.save(user);
    }
}
