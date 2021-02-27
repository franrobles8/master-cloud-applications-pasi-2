package es.urjc.code.ejem1.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJPAShoppingCartExpenditureRepository extends JpaRepository<ShoppingCartExpenditureEntity, Long> {
    
}
