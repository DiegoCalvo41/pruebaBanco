package com.banco.Banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.Banco.model.Banco;

@Repository
public interface bancoRepository extends JpaRepository<Banco, Integer>{

}
