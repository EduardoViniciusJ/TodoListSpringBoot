package com.projeto.todolist.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projeto.todolist.model.Tarefa;
import com.projeto.todolist.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefas")
public class TarefaController { 
	
	private final TarefaRepository repository;
	
	public TarefaController(TarefaRepository repository) {
	    this.repository = repository;
	}
	 
	@GetMapping
	public List<Tarefa> listarTodas() {
	    return repository.findAll();
	}
	 
	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
	    return repository.findById(id)
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	}
	 
	@PostMapping
	public Tarefa criar(@RequestBody Tarefa tarefa) {
	    return repository.save(tarefa);
	}
	 
	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
	    return repository.findById(id)
	            .map(tarefa -> {
	                tarefa.setNome(tarefaAtualizada.getNome());
	                tarefa.setDataEntrega(tarefaAtualizada.getDataEntrega());
	                tarefa.setResponsavel(tarefaAtualizada.getResponsavel());
	                return ResponseEntity.ok(repository.save(tarefa));
	            })
	            .orElse(ResponseEntity.notFound().build());
	}
	 
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
	    return repository.findById(id)
	            .map(tarefa -> {
	                repository.deleteById(id);
	                return ResponseEntity.ok().<Void>build();
	            })
	            .orElse(ResponseEntity.notFound().build());
	}	
}
