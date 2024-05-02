package br.com.academy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academy.model.Aluno;

public interface AlunoDao extends JpaRepository<Aluno, Integer> {

    @Query("select t from Aluno t where t.status = 'ATIVO' ")
    public List<Aluno> findByStatusAtivos();

    @Query("select t from Aluno t where t.status = 'INATIVO' ")
    public List<Aluno> findByStatusInativos();

    @Query("select t from Aluno t where t.status = 'CANCELADO' ")
    public List<Aluno> findByStatusCancelados();
    
    @Query("select t from Aluno t where t.status = 'TRANCADO' ")
    public List<Aluno> findByStatusTrancados();
}
