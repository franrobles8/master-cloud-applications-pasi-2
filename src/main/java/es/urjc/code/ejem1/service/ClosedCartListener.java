package es.urjc.code.ejem1.service;

import org.modelmapper.ModelMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import es.urjc.code.ejem1.domain.ShoppingCartClosed;
import es.urjc.code.ejem1.domain.ShoppingCartExpenditure;
import es.urjc.code.ejem1.domain.ShoppingCartExpenditureRepository;

@Service
public class ClosedCartListener {
    
    private ShoppingCartExpenditureRepository shoppingCartExpenditureRepository;
    private ModelMapper mapper = new ModelMapper();

    public ClosedCartListener(ShoppingCartExpenditureRepository shoppingCartExpenditureRepository) {
        this.shoppingCartExpenditureRepository = shoppingCartExpenditureRepository;
    }

    @EventListener
    public void addExpenditure(ShoppingCartClosed shoppingCartClosed) {
        shoppingCartExpenditureRepository.save(mapper.map(shoppingCartClosed, ShoppingCartExpenditure.class));
    }

}
