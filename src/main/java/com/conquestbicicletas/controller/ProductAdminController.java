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
import org.springframework.web.bind.annotation.RestController;

import com.conquestbicicletas.model.ProductModel;
import com.conquestbicicletas.model.dao.ProductDAO;
import com.conquestbicicletas.service.ProductAdminService;

@RestController
@CrossOrigin(value="*")
@RequestMapping("/conquest")
public class ProductAdminController {
	
	@Autowired
	private ProductAdminService userServiceAdminProduct;
	
	
	/**
	 * Lista todos os produtos cadastrados no backOffice
	 * 
	 * @return Lista de produtos
	 */
	
	//// ARRUMAR A ROTA
	@GetMapping(value = "/user/listproduct", produces = "application/json")
    public ResponseEntity<List<ProductModel>> getListAllProduct() {
    	
		List<ProductModel> response = userServiceAdminProduct.getListProduct();
    	
    	if (response != null) {
    		return ResponseEntity.status(HttpStatus.OK).body(response);
    	}
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
	
	
	/**
	 * Solicita o update de um produto
	 * 
	 * @param requestUpdateProduct
	 * @return se o produto foi atualizado ou não
	 */
	
	//Faltam as inmagens 
	@PutMapping(value = "/user/adm/updateproduto", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ProductDAO> updateProduct(@RequestBody ProductDAO requestUpdateProduct) {
		
		boolean isUpdated = userServiceAdminProduct.updateProduct(requestUpdateProduct);
		
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK).body(requestUpdateProduct);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(requestUpdateProduct);
	}
	
	
	/**
	 * Retorna a pesquisa de um produto pesquisado pelo nome, com busca parcial
	 * 
	 * @param requestUserSearch
	 * @return
	 */
	@PostMapping(value = "/user/adm/searchname", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<ProductModel>> getListSearchName(@RequestBody ProductModel requestUserSearch){
		
		List<ProductModel> response = userServiceAdminProduct.getListProductSearchName(requestUserSearch);
		
		if(response != null) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

}
