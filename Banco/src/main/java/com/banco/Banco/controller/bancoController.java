package com.banco.Banco.controller;

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
	
	@PostMapping("/banco/")
	public Banco registrarBanco(@RequestBody Banco banco){
		return bancoRepo.save(banco);
	}
	
	@PutMapping("/banco/{id}")
	public ResponseEntity<Banco> editarBanco(@PathVariable Integer id, @RequestBody Banco banco){
		Banco banco1 = bancoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(("No existe el emplado con el ID: " + id)));
		banco1.setNombre(banco.getNombre());
		banco1.setDireccion(banco.getDireccion());
		
		Banco banco2 = bancoRepo.save(banco1);
		return ResponseEntity.ok(banco2);
	}
	
	@DeleteMapping("/banco/{id}")
	public void eliminarBanco(@PathVariable Integer id) {
		usuarioController userC = new usuarioController();
		List<Usuario> usuario = userC.listarUsuarios();
		for (int i = 0; i < usuario.size(); i++) {
			if(usuario.get(i).getBanco().getId() == id) {
				userC.eliminarBanco(usuario.get(i).getId());
			}
		}
		bancoRepo.deleteById(id);
		
	}
}
