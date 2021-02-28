package es.urjc.code.ejem1;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import es.urjc.code.ejem1.domain.FullProductDTO;
import es.urjc.code.ejem1.domain.FullShoppingCartDTO;
import es.urjc.code.ejem1.domain.ShoppingCartProductAdded;
import es.urjc.code.ejem1.domain.Product;
import es.urjc.code.ejem1.domain.ProductCreated;
import es.urjc.code.ejem1.domain.ProductDTO;
import es.urjc.code.ejem1.domain.ProductRepository;
import es.urjc.code.ejem1.domain.ProductCommandServiceImpl;
import es.urjc.code.ejem1.domain.ShoppingCart;
import es.urjc.code.ejem1.domain.ShoppingCartCreated;
import es.urjc.code.ejem1.domain.ShoppingCartDeleted;
import es.urjc.code.ejem1.domain.ShoppingCartRepository;
import es.urjc.code.ejem1.domain.ShoppingCartCommandServiceImpl;
import es.urjc.code.ejem1.service.CloseShoppingCartServiceImpl;
import es.urjc.code.ejem1.service.ValidationServiceImpl;

public class ShoppingCartServiceTest {
	
	private ProductRepository productRepository;
	private ProductCommandServiceImpl productService;

	private ShoppingCartRepository shoppingCartRepository;
	private ShoppingCartCommandServiceImpl shoppingCartService;

	private ApplicationEventPublisher applicationEventPublisher;

	private ModelMapper mapper = new ModelMapper();
	
	@BeforeEach
	void setUp() {
		productRepository = mock(ProductRepository.class);
		shoppingCartRepository = mock(ShoppingCartRepository.class);
		applicationEventPublisher = mock(ApplicationEventPublisher.class);

		productService = new ProductCommandServiceImpl(productRepository, applicationEventPublisher);
		shoppingCartService = new ShoppingCartCommandServiceImpl(
				shoppingCartRepository,
				productRepository,
				new ValidationServiceImpl(),
				new CloseShoppingCartServiceImpl(applicationEventPublisher),
				applicationEventPublisher);
	}
	
	@Test
	void shoppingCartCanBeAdded() {
		shoppingCartService.createShoppingCart();

		verify(applicationEventPublisher).publishEvent(any(ShoppingCartCreated.class));
	}
	
	@Test
	void productCanBeAddedToShoppingCart() {
		Product product = new Product(
		        "PLUMÍFERO MONTAÑA Y SENDERISMO FORCLAZ TREK100 AZUL CAPUCHA",
		        "Esta chaqueta acolchada de plumón y plumas, con certificado RDS, abriga bien durante un vivac entre +5 °C y -5 °C.",
		        49.99);
		ProductDTO productDTO = mapper.map(product, ProductDTO.class);

		productService.createProduct(productDTO);
		verify(applicationEventPublisher).publishEvent(any(ProductCreated.class));
		
		int items = Math.abs(new Random().nextInt());

		ShoppingCart shoppingCart = new ShoppingCart();
		FullShoppingCartDTO fullShoppingCartDTO = mapper.map(shoppingCart, FullShoppingCartDTO.class);
		
		FullProductDTO fullProductDTO = mapper.map(productDTO, FullProductDTO.class);
		shoppingCartService.addProduct(fullProductDTO, fullShoppingCartDTO, items);

		verify(applicationEventPublisher).publishEvent(any(ShoppingCartProductAdded.class));
	}
	
	@Test
	void shoppingCartCanBeDeleted() {
		FullShoppingCartDTO createdShoppingCart = new FullShoppingCartDTO();
		createdShoppingCart.setId(1L);

		when(shoppingCartRepository.findById(createdShoppingCart.getId())).thenReturn(createdShoppingCart);
		
		shoppingCartService.deleteShoppingCart(createdShoppingCart.getId());
		
		verify(applicationEventPublisher).publishEvent(any(ShoppingCartDeleted.class));
	}
}
