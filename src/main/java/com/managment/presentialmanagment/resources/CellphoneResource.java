package com.managment.presentialmanagment.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.managment.presentialmanagment.domain.Cellphone;
import com.managment.presentialmanagment.dto.CellphoneDTO;
import com.managment.presentialmanagment.services.CellphoneService;

@RestController
@RequestMapping(value="/cellphones")
public class CellphoneResource {
	
	@Autowired
	private CellphoneService service;
	
	@RequestMapping(value = "/{id}", method= RequestMethod.GET)
	public ResponseEntity<Cellphone> find(@PathVariable Integer id ) {
		
		Cellphone Cellphone = service.find(id);
	
		return ResponseEntity.ok().body(Cellphone);
	}
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Cellphone obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Cellphone obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<List<CellphoneDTO>> findAll() {
		
		List<Cellphone> list = service.findAll();
		List<CellphoneDTO> listDto  = list.stream().map(obj -> new CellphoneDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/page", method= RequestMethod.GET)
	public ResponseEntity<Page<CellphoneDTO>> findPage(
			@RequestParam(value = "page",  defaultValue = "0") Integer page, 
			
			@RequestParam(value = "linesPerPage",  defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy",  defaultValue = "model") String orderBy, 
			@RequestParam(value = "direction",  defaultValue = "ASC") String direction) {
		
		Page<Cellphone> list = service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<CellphoneDTO> listDto  = list.map(obj -> new CellphoneDTO(obj));
		
		return ResponseEntity.ok().body(listDto);
	}
}
