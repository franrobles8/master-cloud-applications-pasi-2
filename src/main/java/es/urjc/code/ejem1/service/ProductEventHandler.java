package es.urjc.code.ejem1.service;

import es.urjc.code.ejem1.domain.ProductCreated;
import es.urjc.code.ejem1.domain.ProductDeleted;

public interface ProductEventHandler {
    
    public void handle(ProductCreated product);

    public void handle(ProductDeleted product);

}
