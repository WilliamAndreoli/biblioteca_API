package com.biblioteca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entities.Emprestimo;
import com.biblioteca.services.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

	@Autowired
	private EmprestimoService emprestimoService;

	@GetMapping
	public List<Emprestimo> getAllEmprestimos() {
		return emprestimoService.findAll();
	}

	@GetMapping("/id/{id}")
	public Optional<Emprestimo> getEmprestimoById(@PathVariable Integer id) {
		return emprestimoService.findById(id);
	}

	/*
	@PostMapping
	public Emprestimo createEmprestimo(@RequestBody Emprestimo emprestimo) throws Exception {
		return emprestimoService.save(emprestimo);
	}*/

	/*
	@PutMapping("/{id}")
	public ResponseEntity<Emprestimo> updateEmprestimo(@PathVariable Integer id,
			@RequestBody Emprestimo emprestimoDetails) throws Exception {
		Optional<Emprestimo> optionalEmprestimo = emprestimoService.findById(id);

		if (!optionalEmprestimo.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Emprestimo emprestimo = optionalEmprestimo.get();
		emprestimo.setData_emprestimo(emprestimoDetails.getData_emprestimo());
		emprestimo.setData_entrega(emprestimoDetails.getData_entrega());
		emprestimo.setData_previsao(emprestimoDetails.getData_previsao());
		emprestimo.setLivro(emprestimoDetails.getLivro());
		emprestimo.setMulta(emprestimoDetails.getMulta());
		emprestimo.setUsuario(emprestimoDetails.getUsuario());

		return ResponseEntity.ok(emprestimoService.save(emprestimo));
	}*/

	@DeleteMapping("/id/{id}")
	public ResponseEntity<Void> deleteEmprestimoById(@PathVariable Integer id) {
		Optional<Emprestimo> optionalEmprestimo = emprestimoService.findById(id);

		if (!optionalEmprestimo.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		emprestimoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
