package es.urjc.code.ejem1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.urjc.code.ejem1.domain.ProductDTO;
import es.urjc.code.ejem1.domain.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductCommandController {

	private ProductService productService;
	private ModelMapper mapper = new ModelMapper();

	public ProductCommandController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<String> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
		ProductDTO productDTO = mapper.map(productRequestDTO, ProductDTO.class);
		productService.createProduct(productDTO);

		return new ResponseEntity<>("Product created", HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>("Product deleted", HttpStatus.OK);
	}

}
