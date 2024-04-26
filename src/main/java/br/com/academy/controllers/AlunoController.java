package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.AlunoDao;
import br.com.academy.model.Aluno;

import jakarta.validation.Valid;

@Controller
public class AlunoController {

    @Autowired
    private AlunoDao alunorepositorio;

    @GetMapping("/inserirAlunos")
    public ModelAndView insertAluno(Aluno aluno) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/formAluno");
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    @PostMapping("insertAlunos")
    public ModelAndView inserirAluno(@Valid Aluno aluno, BindingResult br ) {
        ModelAndView mv = new ModelAndView();
        if (br.hasErrors()) {
            mv.setViewName("aluno/formAluno");
            mv.addObject("aluno", aluno);
        } else {
            mv.setViewName("redirect:/alunos-adicionados");
            alunorepositorio.save(aluno);
        }
        return mv;
    }

    @GetMapping("alunos-adicionados")
    public ModelAndView listagemAlunos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/listAlunos");
        mv.addObject("alunosList", alunorepositorio.findAll());
        return mv;
    }
    
    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        Aluno aluno = alunorepositorio.findById(id).orElse(null);
        mv.addObject("aluno", aluno);
        mv.setViewName("aluno/alterar");
        return mv;
    }

    @PostMapping("alterar")
    public ModelAndView alterarAluno(Aluno aluno) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/alunos-adicionados");
        alunorepositorio.save(aluno);
        return mv;
    }

    @GetMapping("/deletar/{id}")
    public String exluirAluno(@PathVariable("id") Integer id) {
        alunorepositorio.deleteById(id);
        return "redirect:/alunos-adicionados";
    }
}
