package es.urjc.code.ejem1.service;

import org.modelmapper.ModelMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import es.urjc.code.ejem1.domain.FullShoppingCartDTO;
import es.urjc.code.ejem1.domain.ShoppingCartProductAdded;
import es.urjc.code.ejem1.domain.ShoppingCartProductDeleted;
import es.urjc.code.ejem1.domain.ShoppingCartCreated;
import es.urjc.code.ejem1.domain.ShoppingCartDeleted;
import es.urjc.code.ejem1.domain.ShoppingCartRepository;
import es.urjc.code.ejem1.domain.ShoppingCartUpdated;

@Service
public class ShoppingCartEventHandlerImpl implements ShoppingCartEventHandler {

    private ShoppingCartRepository shoppingCartRepository;
    private ModelMapper mapper = new ModelMapper();

    public ShoppingCartEventHandlerImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    @EventListener
    public void handle(ShoppingCartCreated shoppingCart) {
        this.shoppingCartRepository.save(mapper.map(shoppingCart, FullShoppingCartDTO.class));
    }

    @Override
    @EventListener
    public void handle(ShoppingCartProductAdded shoppingCart) {
        this.shoppingCartRepository.save(mapper.map(shoppingCart, FullShoppingCartDTO.class));
    }

    @Override
    @EventListener
    public void handle(ShoppingCartUpdated shoppingCart) {
        this.shoppingCartRepository.save(mapper.map(shoppingCart, FullShoppingCartDTO.class));
    }

    @Override
    @EventListener
    public void handle(ShoppingCartProductDeleted shoppingCart) {
        this.shoppingCartRepository.save(mapper.map(shoppingCart, FullShoppingCartDTO.class));
    }

    @Override
    @EventListener
    public void handle(ShoppingCartDeleted shoppingCart) {
        this.shoppingCartRepository.deleteById(shoppingCart.getId());
    }
    
}
