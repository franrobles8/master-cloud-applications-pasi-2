package es.urjc.code.ejem1.controller;

public class ShoppingCartExpenditureResponseDTO {

    private Long cartId;

    private Double expenditure;

    public ShoppingCartExpenditureResponseDTO() {}

    public ShoppingCartExpenditureResponseDTO(Long cartId, Double expenditure) {
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
