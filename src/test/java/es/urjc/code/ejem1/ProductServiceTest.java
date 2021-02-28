package es.urjc.code.ejem1;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;

import es.urjc.code.ejem1.domain.FullProductDTO;
import es.urjc.code.ejem1.domain.Product;
import es.urjc.code.ejem1.domain.ProductCreated;
import es.urjc.code.ejem1.domain.ProductDTO;
import es.urjc.code.ejem1.domain.ProductDeleted;
import es.urjc.code.ejem1.domain.ProductRepository;
import es.urjc.code.ejem1.domain.ProductCommandServiceImpl;

public class ProductServiceTest {

	private ProductRepository productRepository;
	private ProductCommandServiceImpl productService;
	private ApplicationEventPublisher applicationEventPublisher;
	private ModelMapper mapper = new ModelMapper();

	Product product;

	@BeforeEach
	void setUp() {
		productRepository = mock(ProductRepository.class);
		applicationEventPublisher = mock(ApplicationEventPublisher.class);
		productService = new ProductCommandServiceImpl(productRepository, applicationEventPublisher);

		product = new Product(
		        "PLUMÍFERO MONTAÑA Y SENDERISMO FORCLAZ TREK100 AZUL CAPUCHA",
		        "Esta chaqueta acolchada de plumón y plumas, con certificado RDS, abriga bien durante un vivac entre +5 °C y -5 °C.",
				49.99);
	}

	@Test
	void productCanBeAdded() {
		ProductDTO productDTO = mapper.map(product, ProductDTO.class);

		productService.createProduct(productDTO);
		verify(applicationEventPublisher, times(1)).publishEvent(any(ProductCreated.class));
	}

	@Test
	void productCanBeDeleted() {
		product.setId(1L);
		FullProductDTO fullProduct = mapper.map(product, FullProductDTO.class);
		when(productRepository.findById(product.getId())).thenReturn(fullProduct);
		productService.deleteProduct(product.getId());
		verify(applicationEventPublisher, times(1)).publishEvent(any(ProductDeleted.class));
	}
}
