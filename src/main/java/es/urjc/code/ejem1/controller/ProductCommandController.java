package es.urjc.code.ejem1.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.code.ejem1.domain.FullProductDTO;
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
	public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
		ProductDTO productDTO = mapper.map(productRequestDTO, ProductDTO.class);
		FullProductDTO fullProductDTO = productService.createProduct(productDTO);

		URI location = fromCurrentRequest().path("/{id}")
		        .buildAndExpand(fullProductDTO.getId()).toUri();

		return ResponseEntity.created(location).body(
		        mapper.map(fullProductDTO, ProductResponseDTO.class));
	}

	@DeleteMapping("/{id}")
	public ProductResponseDTO deleteProduct(@PathVariable Long id) {
		return mapper.map(productService.deleteProduct(id), ProductResponseDTO.class);
	}

}
