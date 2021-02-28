package es.urjc.code.ejem1.service;

import org.modelmapper.ModelMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import es.urjc.code.ejem1.domain.FullProductDTO;
import es.urjc.code.ejem1.domain.ProductCreated;
import es.urjc.code.ejem1.domain.ProductDeleted;
import es.urjc.code.ejem1.domain.ProductRepository;

@Service
public class ProductEventHandlerImpl implements ProductEventHandler {

    private ProductRepository productRepository;
    private ModelMapper mapper = new ModelMapper();

    public ProductEventHandlerImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @EventListener
    public void handle(ProductCreated product) {
        this.productRepository.save(mapper.map(product, FullProductDTO.class));
    }

    @Override
    @EventListener
    public void handle(ProductDeleted product) {
        this.productRepository.deleteById(product.getId());
    }
    
}
