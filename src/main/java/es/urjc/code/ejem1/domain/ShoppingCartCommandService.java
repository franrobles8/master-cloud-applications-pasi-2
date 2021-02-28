package es.urjc.code.ejem1.domain;

public interface ShoppingCartCommandService {

	public void createShoppingCart();

	public void updateShoppingCart(Long id, ShoppingCartDTO shoppingCartDTO);

	public void deleteShoppingCart(Long id);

	public void addProduct(Long idShoppingCart, Long idProduct, int nProducts);

	public void addProduct(FullProductDTO fullProductDTO, FullShoppingCartDTO fullShoppingCartDTO,
	        int quantity);

	public void deleteProduct(Long idShoppingCart, Long idProduct);
}
