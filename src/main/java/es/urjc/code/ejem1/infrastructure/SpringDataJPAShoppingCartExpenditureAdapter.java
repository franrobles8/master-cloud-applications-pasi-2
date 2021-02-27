package es.urjc.code.ejem1.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import es.urjc.code.ejem1.domain.ShoppingCartExpenditure;
import es.urjc.code.ejem1.domain.ShoppingCartExpenditureRepository;

@Component
public class SpringDataJPAShoppingCartExpenditureAdapter implements ShoppingCartExpenditureRepository {

    private SpringDataJPAShoppingCartExpenditureRepository cartExpenditureRepository;
    private ModelMapper mapper = new ModelMapper();

    public SpringDataJPAShoppingCartExpenditureAdapter(
            SpringDataJPAShoppingCartExpenditureRepository cartExpenditureRepository) {
        this.cartExpenditureRepository = cartExpenditureRepository;
    }

    @Override
    public void save(ShoppingCartExpenditure shoppingCartExpenditure) {
        this.cartExpenditureRepository.save(mapper.map(shoppingCartExpenditure, ShoppingCartExpenditureEntity.class));
    }

    @Override
    public List<ShoppingCartExpenditure> findAll() {
        return this.cartExpenditureRepository
            .findAll()
            .stream()
            .map(expenditure -> mapper.map(expenditure, ShoppingCartExpenditure.class))
            .collect(Collectors.toList());
    }
    
}
