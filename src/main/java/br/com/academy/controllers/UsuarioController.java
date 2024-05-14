package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

// import br.com.academy.dao.UsuarioDao;
import br.com.academy.model.Usuario;
import br.com.academy.service.ServiceUsuario;

@Controller
public class UsuarioController {

    // @Autowired
    // private UsuarioDao usuarioRepositorio;

    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new Usuario());
        mv.setViewName("login/login");
        return mv;
    }
    
    @GetMapping("/cadastrar")
    public ModelAndView cadastro() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/cadastro");
        mv.addObject("user", new Usuario());
        return mv;
    }

    @PostMapping("salvarUsuario")
    public ModelAndView salvarUser(Usuario user) throws Exception {
        ModelAndView mv = new ModelAndView();
        serviceUsuario.salvarUsuario(user);
        mv.setViewName("redirect:/");
        return mv;
    }
}  
