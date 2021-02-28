package es.urjc.code.ejem1.service;

import org.modelmapper.ModelMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import es.urjc.code.ejem1.domain.ShoppingCartClosed;
import es.urjc.code.ejem1.domain.ShoppingCartExpenditure;
import es.urjc.code.ejem1.domain.ShoppingCartExpenditureRepository;

@Service
public class ClosedCartEventHandlerImpl implements ClosedCartEventHandler {
    
    private ShoppingCartExpenditureRepository shoppingCartExpenditureRepository;
    private ModelMapper mapper = new ModelMapper();

    public ClosedCartEventHandlerImpl(ShoppingCartExpenditureRepository shoppingCartExpenditureRepository) {
        this.shoppingCartExpenditureRepository = shoppingCartExpenditureRepository;
    }

    @Override
    @EventListener
    public void handle(ShoppingCartClosed shoppingCartClosed) {
        shoppingCartExpenditureRepository.save(mapper.map(shoppingCartClosed, ShoppingCartExpenditure.class));
    }

}
