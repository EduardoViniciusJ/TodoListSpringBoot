package com.projeto.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.todolist.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

	
	
}
