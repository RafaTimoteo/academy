package br.com.academy.enums;

public enum Curso {
    ADMINISTRACAO("Administracao"),
    INFORMATIVA("Informatica"),
    CONTABILIDADE("Contabilidade"),
    PROGRAMACAO("Programacao"),
    ENFERMAGEM("Enfermagem");

    private String curso;

    private Curso(String curso) {
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }
    
}
