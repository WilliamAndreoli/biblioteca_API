package com.biblioteca.controllers;

import java.util.List;

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

import com.biblioteca.entities.Area;
import com.biblioteca.services.AreaService;

@RestController
@RequestMapping("/areas")
public class AreaController {

	@Autowired
	private AreaService areaService;

	@GetMapping
	public List<Area> getAllAreaes() {
		return areaService.findAll();
	}
	
	// Buscar por descricao
    @GetMapping("/descricao/{descricao}")
    public Area findByNome(@PathVariable String descricao) {
    	return areaService.findByDescricao(descricao);
    }
	
    @PostMapping
    public Area createArea(@RequestBody Area area) {
        return areaService.save(area);
    }
	
	@PutMapping("/{descricao}")
    public ResponseEntity<Area> updateArea(@PathVariable String descricao, @RequestBody Area areaDetails) {
        Area area = areaService.findByDescricao(descricao);
        if (area == null) {
            return ResponseEntity.notFound().build();
        }
        area.setDescricao(areaDetails.getDescricao());
        return ResponseEntity.ok(areaService.save(area));
    }
	
	@DeleteMapping("/descricao/{descricao}")
    public ResponseEntity<Void> deleteAreaByDescricao(@PathVariable String descricao) {
        Area area = areaService.findByDescricao(descricao);
        if (area == null) {
            return ResponseEntity.notFound().build();
        }
        areaService.deleteByDescricao(descricao);
        return ResponseEntity.noContent().build();
    }
    
}
