package es.urjc.code.ejem1.service;

import org.springframework.context.ApplicationEventPublisher;

import es.urjc.code.ejem1.domain.CloseShoppingCartService;
import es.urjc.code.ejem1.domain.ShoppingCartClosed;

public class CloseShoppingCartServiceImpl implements CloseShoppingCartService {

    private final ApplicationEventPublisher applicationEventPublisher;

    public CloseShoppingCartServiceImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void close(ShoppingCartClosed shoppingCartClosed) {
        applicationEventPublisher.publishEvent(shoppingCartClosed);
    }
    
}
