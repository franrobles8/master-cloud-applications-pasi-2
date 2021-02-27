package es.urjc.code.ejem1;

import org.springframework.context.annotation.Bean;

import es.urjc.code.ejem1.domain.ProductService;
import es.urjc.code.ejem1.domain.ProductServiceImpl;
import es.urjc.code.ejem1.domain.ShoppingCartExpenditureService;
import es.urjc.code.ejem1.domain.ShoppingCartExpenditureServiceImpl;
import es.urjc.code.ejem1.domain.ShoppingCartService;
import es.urjc.code.ejem1.domain.ShoppingCartServiceImpl;
import es.urjc.code.ejem1.infrastructure.SpringDataJPAProductRepositoryAdapter;
import es.urjc.code.ejem1.infrastructure.SpringDataJPAShoppingCartExpenditureAdapter;
import es.urjc.code.ejem1.infrastructure.SpringDataJPAShoppingCartRepositoryAdapter;
import es.urjc.code.ejem1.service.ValidationServiceImpl;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	public ShoppingCartService shoppingCartService(
	        SpringDataJPAShoppingCartRepositoryAdapter shoppingCartRepositoryAdapter,
			SpringDataJPAProductRepositoryAdapter productRepositoryAdapter,
			SpringDataJPAShoppingCartExpenditureAdapter shoppingCartExpenditureAdapter) {
		return new ShoppingCartServiceImpl(
		        shoppingCartRepositoryAdapter,
				productRepositoryAdapter,
				shoppingCartExpenditureAdapter,
		        new ValidationServiceImpl());
	}

	@Bean
	public ProductService productService(SpringDataJPAProductRepositoryAdapter repositoryAdapter) {
		return new ProductServiceImpl(repositoryAdapter);
	}

	@Bean
	public ShoppingCartExpenditureService shoppingCartExpenditureService(SpringDataJPAShoppingCartExpenditureAdapter shoppingCartExpenditureAdapter) {
		return new ShoppingCartExpenditureServiceImpl(shoppingCartExpenditureAdapter);
	}

}
