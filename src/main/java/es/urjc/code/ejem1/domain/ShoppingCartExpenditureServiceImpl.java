package es.urjc.code.ejem1.domain;

import java.util.List;

public class ShoppingCartExpenditureServiceImpl implements ShoppingCartExpenditureService {

    private ShoppingCartExpenditureRepository shoppingCartExpenditureRepository;

    public ShoppingCartExpenditureServiceImpl(ShoppingCartExpenditureRepository shoppingCartExpenditureRepository) {
        this.shoppingCartExpenditureRepository = shoppingCartExpenditureRepository;
    }

    @Override
    public List<ShoppingCartExpenditure> getAllExpenditures() {
        return shoppingCartExpenditureRepository.findAll();
    }
    
}
