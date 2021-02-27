package es.urjc.code.ejem1.domain;

import java.util.List;

public interface ShoppingCartExpenditureRepository {

    public void save(ShoppingCartExpenditure shoppingCartExpenditure);

    public List<ShoppingCartExpenditure> findAll();
    
}
