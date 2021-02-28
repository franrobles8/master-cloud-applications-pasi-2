package es.urjc.code.ejem1.domain;

import org.modelmapper.ModelMapper;

public class ShoppingCartQueryServiceImpl implements ShoppingCartQueryService {

    private ShoppingCartRepository shoppingCartRepository;
    private ModelMapper mapper = new ModelMapper();

    public ShoppingCartQueryServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }
    
    @Override
	public FullShoppingCartDTO getShoppingCart(Long id) {
		return mapper.map(shoppingCartRepository.findById(id), FullShoppingCartDTO.class);
	}

}
