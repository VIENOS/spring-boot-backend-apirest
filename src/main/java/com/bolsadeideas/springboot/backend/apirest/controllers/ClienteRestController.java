package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.services.IClienteService;

@CrossOrigin(origins = {"http://localhost:4200"})//Cross es para compartir recursos API REST
@RestController//es para que sea ApiRest
@RequestMapping("/api")//es para mapear el rescontroller
public class ClienteRestController {
	
	@Autowired //es una anotación para inyectar el servicio
	private IClienteService clienteService;
	
	@GetMapping("/clientes") //mapea la url	@GetMapping para listar
	public List<Cliente> index(){
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/{id}")
	public Cliente show(@PathVariable Long id) { //el @PathVariable se usa cuando se pasa por id
		return clienteService.findById(id);
	}
	
	@PostMapping("/clientes") // @PostMapping para ingresar
	@ResponseStatus(HttpStatus.CREATED)//Retorna 201: significa que creó correctamente
	public Cliente create(@RequestBody Cliente cliente) { //como viene en json se pone el @RequestBody: lo tranforma el JSON y lo tranforma en objeto cliente
		return clienteService.save(cliente);
	}
	
	@PutMapping("/clientes/{id}") //@PutMapping para editar
	@ResponseStatus(HttpStatus.CREATED)//Retorna 201: significa que modificó correctamente
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente clienteActual = clienteService.findById(id);
		clienteActual.setApellido(cliente.getApellido());
		clienteActual.setNombre(cliente.getNombre());
		clienteActual.setEmail(cliente.getEmail());
				
		return clienteService.save(clienteActual);//el save lo actualiza porque ya existe el id
	}
	
	@DeleteMapping("/clientes/{id}") //@@DeleteMapping para eliminar
	@ResponseStatus(HttpStatus.NO_CONTENT)//Retorna 204: que no hay contenido
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}
	
	
}
