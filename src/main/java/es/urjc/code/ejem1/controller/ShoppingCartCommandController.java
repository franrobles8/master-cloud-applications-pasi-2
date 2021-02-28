package es.urjc.code.ejem1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.urjc.code.ejem1.domain.ShoppingCartDTO;
import es.urjc.code.ejem1.domain.ShoppingCartService;

@RestController
@RequestMapping("/api/shoppingcarts")
public class ShoppingCartCommandController {

	private ShoppingCartService shoppingService;
	private ModelMapper mapper = new ModelMapper();

	public ShoppingCartCommandController(ShoppingCartService shoppingService) {
		this.shoppingService = shoppingService;
	}

	@PostMapping("/{idShoppingCart}/product/{idProduct}/quantity/{quantity}")
	public ResponseEntity<String> addProductToCart(
	        @PathVariable Long idShoppingCart,
	        @PathVariable Long idProduct,
	        @PathVariable int quantity) {

		shoppingService.addProduct(idShoppingCart, idProduct, quantity);
		
		return new ResponseEntity<>("Product was added to the shopping cart", HttpStatus.OK);
	}

	@DeleteMapping("/{idShoppingCart}/product/{idProduct}")
	public ResponseEntity<String> deleteProductInShoppingCart(
	        @PathVariable Long idShoppingCart,
	        @PathVariable Long idProduct) {
		
		shoppingService.deleteProduct(idShoppingCart, idProduct);

		return new ResponseEntity<>("Product was deleted from the shopping cart", HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createShoppingCart() {
		shoppingService.createShoppingCart();
		return new ResponseEntity<>("Shopping cart created", HttpStatus.CREATED);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<String> updateShoppingCart(
	        @PathVariable Long id,
	        @Validated @RequestBody ShoppingCartRequestDTO shoppingCartRequestDTO) {
		
		shoppingService.updateShoppingCart(id,
		        mapper.map(shoppingCartRequestDTO, ShoppingCartDTO.class));

		return new ResponseEntity<>("Shopping cart updated", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteShoppingCart(@PathVariable Long id) {

		shoppingService.deleteShoppingCart(id);
		
		return new ResponseEntity<>("Shopping cart deleted", HttpStatus.OK);
	}
}
