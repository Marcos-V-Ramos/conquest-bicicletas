package com.conquestbicicletas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping("/conquest")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * Lista todos os produtos cadastrados no backOffice com imagens
	 * 
	 * @return Lista de produtos
	 */
	@GetMapping(value = "/backoffice/product", produces = "application/json")
	public ResponseEntity<List<ProductModelDAO>> visualizeListAllProduct() {

		List<ProductModelDAO> response = productService.visualizeListAllProduct();

		if (response != null && response.size() > 0) {
			log.info("[INFO] Success in list products");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else if (response.size() == 0) {
			log.info("[INFO] List without content");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
		log.error("[ERROR] Unable to list product");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	/**
	 * Lista todos os produtos cadastrados no backOffice
	 * 
	 * @return Lista de produtos
	 */
	@GetMapping(value = "/backoffice/product/list", produces = "application/json")
	public ResponseEntity<List<ProductModelDAO>> getListAllProduct() {

		List<ProductModelDAO> response = productService.getAllListProduct();

		if (response != null && response.size() > 0) {
			log.info("[INFO] Success in list products");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else if (response.size() == 0) {
			log.info("[INFO] List without content");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
		log.error("[ERROR] Unable to list product");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	/**
	 * Visualiza o produto
	 * 
	 * @param requestUserSearch
	 * @return
	 */
	@GetMapping(value = "/backoffice/product/visualize", produces = "application/json")
	public ResponseEntity<ProductModelDAO> visualizeProduct(@RequestParam(value = "product_id") int productId) {

		ProductModelDAO response = productService.visualizeProduct(productId);

		if (response != null) {
			log.info("[INFO] Success in viewing the product");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		log.error("[ERROR] Error when viewing the product");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	/**
	 * Registra um produto
	 * 
	 * @param requestRegisterUser
	 * @return
	 */
	@PostMapping(value = "/backoffice/product/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> registerProduct(@RequestBody ProductModelDAO requestRegisterProduct) {
		boolean isRegister = productService.registerProduct(requestRegisterProduct);

		if (isRegister != false) {
			log.info("[INFO] Success in registering product");
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStatusLogDAO(201, "O produto foi registrado com sucesso!"));
		}
		log.error("[ERROR] Error in registering product");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseStatusLogDAO(500, "Não foi possivel registrar o produto"));
	}

	/**
	 * Registra um produto
	 * 
	 * @param requestRegisterUser
	 * @return
	 */
	@DeleteMapping(value = "/backoffice/product/image", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> deleteImage(@RequestParam(value = "img_id") int idImage) {
		boolean isDeleting = productService.deleteImage(idImage);

		if (isDeleting != false) {
			log.info("[INFO] Success in deleting image");
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStatusLogDAO(201, "A imagem foi deletado com sucesso!"));
		}
		log.error("[ERROR] Error in deleting image");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseStatusLogDAO(500, "Não foi possivel deletar a imagem"));
	}

	/**
	 * Solicita o update de um produto
	 * 
	 * @param requestUpdateProduct
	 * @return se o produto foi atualizado ou não
	 */

	// Faltam as inmagens
	@PutMapping(value = "/backoffice/product/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> updateProduct(@RequestBody ProductModelDAO requestUpdateProduct) {

		boolean isUpdated = productService.updateProduct(requestUpdateProduct);

		if (isUpdated) {
			log.info("[INFO] Success in updating product");
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new ResponseStatusLogDAO(202, "O produto foi alterado com sucesso!"));
		}
		log.error("[ERROR] Error updating product");
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
				.body(new ResponseStatusLogDAO(406, "Erro ao alterar produto"));
	}

	/**
	 * 
	 * @param requestUpdateStatusProduct
	 * @return
	 */
	@PutMapping(value = "/backoffice/product/update/status", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UpdateStatusProductDAO> updateStatusProduct(
			@RequestBody UpdateStatusProductDAO requestUpdateStatusProduct) {

		boolean isUpdatedStatus = productService.updateStatusProduct(requestUpdateStatusProduct);

		if (isUpdatedStatus) {
			log.info("[INFO] Success in updating status product");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(requestUpdateStatusProduct);
		}
		log.error("[ERROR] Error updating status product");
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(requestUpdateStatusProduct);
	}

	/**
	 * Retorna a pesquisa de um produto pesquisado pelo nome, com busca parcial
	 * 
	 * @param requestUserSearch
	 * @return
	 */
	@GetMapping(value = "/backoffice/product/search", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<ProductModelDAO>> getListSearchProduct(@RequestBody ProductModelDAO requestUserSearch) {

		List<ProductModelDAO> response = productService.getListProductSearch(requestUserSearch);

		if (response != null && response.size() > 0) {
			log.info("[INFO] Success in list products");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else if (response.size() == 0) {
			log.info("[INFO] List without content");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
		log.error("[ERROR] Unable to list product");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

}
