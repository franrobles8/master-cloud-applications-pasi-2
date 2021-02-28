package es.urjc.code.ejem1.domain;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;

public class ProductServiceImpl implements ProductService {

	private ProductRepository repository;
	private ModelMapper mapper = new ModelMapper();
	private final ApplicationEventPublisher applicationEventPublisher;

	public ProductServiceImpl(ProductRepository repository, ApplicationEventPublisher applicationEventPublisher) {
		this.repository = repository;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public Collection<FullProductDTO> getProducts() {
		return repository.finAll();
	}

	@Override
	public FullProductDTO getProduct(Long id) {
		return repository.findById(id);
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
