package es.urjc.code.ejem1.infrastructure;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShoppingCartExpenditureEntity {
    
    @Id
    private Long cartId;

    private Double expenditure;

    public ShoppingCartExpenditureEntity() {}

    public ShoppingCartExpenditureEntity(Long cartId, Double expenditure) {
        this.cartId = cartId;
        this.expenditure = expenditure;
    }

    public Long getCartId() {
        return this.cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Double getExpenditure() {
        return this.expenditure;
    }

    public void setExpenditure(Double expenditure) {
        this.expenditure = expenditure;
    }


}
