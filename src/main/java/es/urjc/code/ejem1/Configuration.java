package es.urjc.code.ejem1;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

import es.urjc.code.ejem1.domain.ProductQueryService;
import es.urjc.code.ejem1.domain.ProductQueryServiceImpl;
import es.urjc.code.ejem1.domain.ProductCommandService;
import es.urjc.code.ejem1.domain.ProductCommandServiceImpl;
import es.urjc.code.ejem1.domain.ShoppingCartExpenditureService;
import es.urjc.code.ejem1.domain.ShoppingCartExpenditureServiceImpl;
import es.urjc.code.ejem1.domain.ShoppingCartQueryService;
import es.urjc.code.ejem1.domain.ShoppingCartQueryServiceImpl;
import es.urjc.code.ejem1.domain.ShoppingCartCommandService;
import es.urjc.code.ejem1.domain.ShoppingCartCommandServiceImpl;
import es.urjc.code.ejem1.infrastructure.SpringDataJPAProductRepositoryAdapter;
import es.urjc.code.ejem1.infrastructure.SpringDataJPAShoppingCartExpenditureAdapter;
import es.urjc.code.ejem1.infrastructure.SpringDataJPAShoppingCartRepositoryAdapter;
import es.urjc.code.ejem1.service.CloseShoppingCartServiceImpl;
import es.urjc.code.ejem1.service.ValidationServiceImpl;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	public ShoppingCartQueryService shoppingCartQueryService(
	        SpringDataJPAShoppingCartRepositoryAdapter shoppingCartRepositoryAdapter,
			SpringDataJPAProductRepositoryAdapter productRepositoryAdapter) {
		return new ShoppingCartQueryServiceImpl(
		        shoppingCartRepositoryAdapter);
	}

	@Bean
	public ShoppingCartCommandService shoppingCartCommandService(
	        SpringDataJPAShoppingCartRepositoryAdapter shoppingCartRepositoryAdapter,
			SpringDataJPAProductRepositoryAdapter productRepositoryAdapter,
			ApplicationEventPublisher appEventPublisher) {
		return new ShoppingCartCommandServiceImpl(
		        shoppingCartRepositoryAdapter,
				productRepositoryAdapter,
				new ValidationServiceImpl(),
				new CloseShoppingCartServiceImpl(appEventPublisher),
				appEventPublisher);
	}

	@Bean
	public ProductQueryService productQueryService(SpringDataJPAProductRepositoryAdapter repositoryAdapter,
	ApplicationEventPublisher appEventPublisher) {
		return new ProductQueryServiceImpl(repositoryAdapter);
	}

	@Bean
	public ProductCommandService productCommandService(SpringDataJPAProductRepositoryAdapter repositoryAdapter,
	ApplicationEventPublisher appEventPublisher) {
		return new ProductCommandServiceImpl(repositoryAdapter, appEventPublisher);
	}

	@Bean
	public ShoppingCartExpenditureService shoppingCartExpenditureService(SpringDataJPAShoppingCartExpenditureAdapter shoppingCartExpenditureAdapter) {
		return new ShoppingCartExpenditureServiceImpl(shoppingCartExpenditureAdapter);
	}

}
