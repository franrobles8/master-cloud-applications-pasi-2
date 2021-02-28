package es.urjc.code.ejem1.domain;

import java.util.Collection;

public interface ProductService {
	public Collection<FullProductDTO> getProducts();
	public FullProductDTO getProduct(Long id);
	public void createProduct(ProductDTO productDTO);
	public void deleteProduct(Long id);
}
