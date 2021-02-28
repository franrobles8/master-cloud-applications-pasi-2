package es.urjc.code.ejem1.domain;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;

public class ProductCommandServiceImpl implements ProductCommandService {

	private ProductRepository repository;
	private ModelMapper mapper = new ModelMapper();
	private final ApplicationEventPublisher applicationEventPublisher;

	public ProductCommandServiceImpl(ProductRepository repository, ApplicationEventPublisher applicationEventPublisher) {
		this.repository = repository;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void createProduct(ProductDTO productDTO) {
		FullProductDTO fullProductDTO = mapper.map(productDTO, FullProductDTO.class);
		applicationEventPublisher.publishEvent(mapper.map(fullProductDTO, ProductCreated.class));
	}

	@Override
	public void deleteProduct(Long id) {
		FullProductDTO product = repository.findById(id);
		applicationEventPublisher.publishEvent(mapper.map(product, ProductDeleted.class));
	}

}
