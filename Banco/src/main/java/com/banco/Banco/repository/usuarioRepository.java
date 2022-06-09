package com.banco.Banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.Banco.model.Usuario;

@Repository
public interface usuarioRepository extends JpaRepository<Usuario, Integer> {

}
