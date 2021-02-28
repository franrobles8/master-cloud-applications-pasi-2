package es.urjc.code.ejem1.domain;

public interface ProductCommandService {
	public void createProduct(ProductDTO productDTO);
	public void deleteProduct(Long id);
}
