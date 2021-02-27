package es.urjc.code.ejem1.domain;

public class ShoppingCartExpenditure {

    private Long cartId;

    private Double expenditure;

    public ShoppingCartExpenditure() {}

    public ShoppingCartExpenditure(Long cartId, Double expenditure) {
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
