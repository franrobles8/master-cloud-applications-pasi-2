package es.urjc.code.ejem1.domain;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;

public class ShoppingCartServiceImpl implements ShoppingCartService {

	private ShoppingCartRepository shoppingCartRepository;
	private ProductRepository productRepository;
	private ValidationService validationService;
	private CloseShoppingCartService closeShoppingCartService;
	private final ApplicationEventPublisher applicationEventPublisher;
	
	private ModelMapper mapper = new ModelMapper();

	public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
			ProductRepository productRepository,
			ValidationService validationService,
			CloseShoppingCartService closeShoppingCartService,
			ApplicationEventPublisher applicationEventPublisher) {
		this.shoppingCartRepository = shoppingCartRepository;
		this.productRepository = productRepository;
		this.validationService = validationService;
		this.closeShoppingCartService = closeShoppingCartService;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public FullShoppingCartDTO getShoppingCart(Long id) {
		return mapper.map(shoppingCartRepository.findById(id), FullShoppingCartDTO.class);
	}

	@Override
	public void createShoppingCart() {
		ShoppingCart shoppingCart = new ShoppingCart();
		FullShoppingCartDTO fullShoppingCartDTO = mapper.map(shoppingCart, FullShoppingCartDTO.class);
		this.applicationEventPublisher.publishEvent(mapper.map(fullShoppingCartDTO, ShoppingCartCreated.class));
	}

	@Override
	public void updateShoppingCart(Long id, ShoppingCartDTO shoppingCartDTO) {
		FullShoppingCartDTO fullShoppingCartDTO = shoppingCartRepository.findById(id);

		ShoppingCart shoppingCart = mapper.map(fullShoppingCartDTO, ShoppingCart.class);
		ShoppingCart updateShoppingCart = mapper.map(shoppingCartDTO, ShoppingCart.class);

		if (updateShoppingCart.getStatus() != null &&
		        updateShoppingCart.getStatus() == ShoppingCartStatus.COMPLETED) {
			shoppingCart.setValidationService(validationService);
			shoppingCart.validate();

			shoppingCart.setCloseShoppingCartService(closeShoppingCartService);
			shoppingCart.close();
		}

		FullShoppingCartDTO newShoppingCartDTO = mapper.map(shoppingCart, FullShoppingCartDTO.class);
		
		this.applicationEventPublisher.publishEvent(mapper.map(newShoppingCartDTO, ShoppingCartUpdated.class));

	}

	@Override
	public void deleteShoppingCart(Long id) {
		FullShoppingCartDTO fullShoppingCartDTO = shoppingCartRepository.findById(id);

		this.applicationEventPublisher.publishEvent(mapper.map(fullShoppingCartDTO, ShoppingCartDeleted.class));

	}

	@Override
	public void addProduct(Long idShoppingCart, Long idProduct, int quantity) {
		FullProductDTO fullProductDTO = productRepository.findById(idProduct);
		FullShoppingCartDTO fullShoppingCartDTO = shoppingCartRepository.findById(idShoppingCart);

		addProduct(fullProductDTO, fullShoppingCartDTO, quantity);
	}

	public void addProduct(FullProductDTO fullProductDTO, FullShoppingCartDTO fullShoppingCartDTO,
	        int quantity) {

		ShoppingCart shoppingCart = mapper.map(fullShoppingCartDTO, ShoppingCart.class);
		shoppingCart.removeItem(fullProductDTO.getId());

		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(
		        mapper.map(fullProductDTO, Product.class),
		        quantity);
		shoppingCart.addItem(shoppingCartItem);

		FullShoppingCartDTO newFullProductDTO = mapper.map(shoppingCart, FullShoppingCartDTO.class);

		this.applicationEventPublisher.publishEvent(mapper.map(newFullProductDTO, ShoppingCartProductAdded.class));

	}

	@Override
	public void deleteProduct(Long idShoppingCart, Long idProduct) {
		FullShoppingCartDTO fullShoppingCartDTO = shoppingCartRepository.findById(idShoppingCart);

		ShoppingCart shoppingCart = mapper.map(fullShoppingCartDTO, ShoppingCart.class);
		shoppingCart.removeItem(idProduct);

		FullShoppingCartDTO newFullProductDTO = mapper.map(shoppingCart, FullShoppingCartDTO.class);

		this.applicationEventPublisher.publishEvent(mapper.map(newFullProductDTO, ShoppingCartProductDeleted.class));

	}
}
