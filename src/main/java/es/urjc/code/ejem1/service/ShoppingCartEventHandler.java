package es.urjc.code.ejem1.service;

import es.urjc.code.ejem1.domain.ShoppingCartProductAdded;
import es.urjc.code.ejem1.domain.ShoppingCartProductDeleted;
import es.urjc.code.ejem1.domain.ShoppingCartCreated;
import es.urjc.code.ejem1.domain.ShoppingCartDeleted;
import es.urjc.code.ejem1.domain.ShoppingCartUpdated;

public interface ShoppingCartEventHandler {

    public void handle(ShoppingCartCreated shoppingCart);

    public void handle(ShoppingCartProductAdded shoppingCart);

    public void handle(ShoppingCartUpdated shoppingCart);

    public void handle(ShoppingCartProductDeleted shoppingCart);

    public void handle(ShoppingCartDeleted shoppingCart);

}
