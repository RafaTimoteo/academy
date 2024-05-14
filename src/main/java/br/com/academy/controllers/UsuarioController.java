package br.com.academy.controllers;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.Exceptions.ServiceExc;
// import br.com.academy.dao.UsuarioDao;
import br.com.academy.model.Usuario;
import br.com.academy.service.ServiceUsuario;
import br.com.academy.util.Util;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UsuarioController {

    // @Autowired
    // private UsuarioDao usuarioRepositorio;

    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home/index");
        return mv;
    }

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

    @PostMapping("logarUsuario")
    public ModelAndView logarUsuario(@Valid Usuario user, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceExc {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new Usuario());
    
        if (br.hasErrors()) {
            mv.setViewName("login/login");
        }
    
        Usuario userLogin = serviceUsuario.loginUser(user.getUserName(), Util.md5(user.getSenha()));
        if (userLogin == null) {
            mv.addObject("msg", "Usuário não encontrado. Tente novamente");
        } else {
            session.setAttribute("usuarioLogado", userLogin);
            return index();
        }

        return mv;
    }

    @PostMapping("logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return login();
    }
}  
