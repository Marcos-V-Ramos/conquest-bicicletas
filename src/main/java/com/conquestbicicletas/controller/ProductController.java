package com.conquestbicicletas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.conquestbicicletas.model.dao.ProductModelDAO;
import com.conquestbicicletas.model.dao.ResponseStatusLogDAO;
import com.conquestbicicletas.model.dao.UpdateStatusProductDAO;
import com.conquestbicicletas.service.ProductService;

@RestController
@CrossOrigin(value="*")
@RequestMapping("/conquest")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	/**
	 * Lista todos os produtos cadastrados no backOffice
	 * 
	 * @return Lista de produtos
	 */
	
	//// ARRUMAR A ROTA
	@GetMapping(value = "/backoffice/product/listproduct", produces = "application/json")
    public ResponseEntity<List<ProductModelDAO>> getListAllProduct() {
    	
		List<ProductModelDAO> response = productService.getAllListProduct();
    	
    	if (response != null) {
    		return ResponseEntity.status(HttpStatus.OK).body(response);
    	}
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
	
	
	
	/**
	 * Retorna a pesquisa de um produto pesquisado pelo nome, com busca parcial
	 * 
	 * @param requestUserSearch
	 * @return
	 */
	@GetMapping(value = "/backoffice/product/visualize", produces = "application/json")
	public ResponseEntity<ProductModelDAO> visualizeProduct(@RequestParam(value="product_id") int productId){
		
		ProductModelDAO response = productService.visualizeProduct(productId);
		
		if(response != null) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	
	
	/**
	 * Registra um produto
	 * 
	 * @param requestRegisterUser
	 * @return
	 */
	@PostMapping(value = "/backoffice/product/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> registerUser(@RequestBody ProductModelDAO requestRegisterProduct){
		boolean isRegister =  productService.registerProduct(requestRegisterProduct);
		
		if (isRegister != false) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStatusLogDAO(200, "O produto foi registrado com sucesso!") );
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseStatusLogDAO(500, "Não foi possivel registrar o produto"));
    }
	
	
	
	/**
	 * Solicita o update de um produto
	 * 
	 * @param requestUpdateProduct
	 * @return se o produto foi atualizado ou não
	 */
	
	//Faltam as inmagens 
	@PutMapping(value = "/backoffice/product/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ProductModelDAO> updateProduct(@RequestBody ProductModelDAO requestUpdateProduct) {
		
		boolean isUpdated = productService.updateProduct(requestUpdateProduct);
		
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK).body(requestUpdateProduct);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(requestUpdateProduct);
	}
	
	
	
	/**
	 * 
	 * @param requestUpdateStatusProduct
	 * @return
	 */
	@PutMapping(value = "/backoffice/product/updatestatusproduct", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UpdateStatusProductDAO> updateStatusProduct(@RequestBody UpdateStatusProductDAO requestUpdateStatusProduct) {
		
		boolean isUpdatedStatus = productService.updateStatusProduct(requestUpdateStatusProduct);
		
		if (isUpdatedStatus) {
			return ResponseEntity.status(HttpStatus.OK).body(requestUpdateStatusProduct);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(requestUpdateStatusProduct);
	}
	
	
	
	/**
	 * Retorna a pesquisa de um produto pesquisado pelo nome, com busca parcial
	 * 
	 * @param requestUserSearch
	 * @return
	 */
	@PostMapping(value = "/backoffice/product/search", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<ProductModelDAO>> getListSearchProduct(@RequestBody ProductModelDAO requestUserSearch){
		
		List<ProductModelDAO> response = productService.getListProductSearch(requestUserSearch);
		
		if(response != null) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

}
