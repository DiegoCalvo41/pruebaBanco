package com.banco.Banco.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.banco.Banco.exception.ResourceNotFoundException;
import com.banco.Banco.model.Banco;
import com.banco.Banco.model.Usuario;
import com.banco.Banco.repository.usuarioRepository;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/v1")
public class usuarioController {
	
	@Autowired
	private usuarioRepository usuarioRepo;

	
	@GetMapping("/usuario")
	public List<Usuario> listarUsuarios(){
		
		return usuarioRepo.findAll();
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> buscarBancoId(@PathVariable Integer id){
		Usuario usuario = usuarioRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(("No existe el emplado con el ID: " + id)));
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping("/usuario/")
	public Usuario registrarBanco(@RequestBody Usuario usuario){
		Banco banco = new Banco();
		banco.agregarUsuario(usuario);
		return usuarioRepo.save(usuario);
		
	}
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<Usuario> editarBanco(@PathVariable Integer id, @RequestBody Usuario usuario){
		Usuario usuario1 = usuarioRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(("No existe el emplado con el ID: " + id)));
		usuario1.setNombre(usuario.getNombre());
		usuario1.setDireccion(usuario.getDireccion());
		usuario1.setCedula(usuario.getCedula());
		usuario1.setBanco(usuario.getBanco());
		
		
		Usuario usuario2 = usuarioRepo.save(usuario1);
		return ResponseEntity.ok(usuario2);
	}
	
	@DeleteMapping("/usuario/{id}")
	public void eliminarBanco(@PathVariable Integer id) {
		usuarioRepo.deleteById(id);
	}
}
