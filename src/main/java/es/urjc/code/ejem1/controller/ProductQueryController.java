package es.urjc.code.ejem1.controller;

import java.util.Arrays;
import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.code.ejem1.domain.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductQueryController {
    
    private ProductService productService;
	private ModelMapper mapper = new ModelMapper();

	public ProductQueryController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public Collection<ProductResponseDTO> getProducts() {
		return Arrays.asList(mapper.map(productService.getProducts(), ProductResponseDTO[].class));
	}

	@GetMapping("/{id}")
	public ProductResponseDTO getProduct(@PathVariable Long id) {
		return mapper.map(productService.getProduct(id), ProductResponseDTO.class);
	}

}
