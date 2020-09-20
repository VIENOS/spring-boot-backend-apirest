package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;

public interface IClienteService {

	//listar datos
	public List<Cliente> findAll();
	
	//obtener por id
	public Cliente findById(Long id);
	
	//guardar
	public Cliente save(Cliente cliente);
	
	//eliminar
	public void delete(Long id);
}
