package com.banco.Banco.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.banco.Banco.exception.ResourceNotFoundException;
import com.banco.Banco.model.Banco;
import com.banco.Banco.model.Usuario;
import com.banco.Banco.repository.bancoRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/v1")
public class bancoController {

	@Autowired
	private bancoRepository bancoRepo;
	
	@GetMapping("/banco")
	public List<Banco> listarBancos(){
		return bancoRepo.findAll();
	}
	
	@GetMapping("/banco/{id}")
	public ResponseEntity<Banco> buscarBancoId(@PathVariable Integer id){
		Banco banco = bancoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(("No existe el emplado con el ID: " + id)));
		return ResponseEntity.ok(banco);
	}
	
	@PostMapping("/banco")
	public ResponseEntity<Banco> registrarBanco(@RequestBody Banco banco){
		System.out.println("hola");
		Banco bancoGuardado = bancoRepo.save(banco);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(bancoGuardado.getId()).toUri();
		return ResponseEntity.created(ubicacion).body(bancoGuardado);
	}
	
	@PutMapping("/banco/{id}")
	public ResponseEntity<Banco> editarBanco(@PathVariable Integer id, @RequestBody Banco banco){
		Optional<Banco> bancoOptional = bancoRepo.findById(id);
		if(!bancoOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		banco.setId(bancoOptional.get().getId());
		bancoRepo.save(banco);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/banco/{id}")
	public ResponseEntity<Object> eliminarBanco(@PathVariable Integer id) {
		Optional<Banco> bancoOptional = bancoRepo.findById(id);
		if(!bancoOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}	
		bancoRepo.delete(bancoOptional.get());
		return ResponseEntity.noContent().build();
	}
}
