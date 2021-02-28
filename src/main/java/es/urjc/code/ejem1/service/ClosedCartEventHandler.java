package es.urjc.code.ejem1.service;

import es.urjc.code.ejem1.domain.ShoppingCartClosed;

public interface ClosedCartEventHandler {
    
    public void handle(ShoppingCartClosed shoppingCartClosed);

}
