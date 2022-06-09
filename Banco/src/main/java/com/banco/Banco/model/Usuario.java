package com.banco.Banco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="cedula", nullable = false, length = 60)
	private String cedula;
	
	@Column(name="nombre", nullable = false, length = 100)
	private String nombre;
	
	@Column (name="direccion")
	private String direccion;
	
	@ManyToOne
	@Column(name="banco_id")
	private Banco banco;

	public Usuario(Integer id, String cedula, String nombre, String direccion, Banco banco) {
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.direccion = direccion;
		this.banco = banco;
	}

	public Usuario() {
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
		

}
