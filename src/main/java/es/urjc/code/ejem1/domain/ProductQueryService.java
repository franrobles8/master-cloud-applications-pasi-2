package es.urjc.code.ejem1.domain;

import java.util.Collection;

public interface ProductQueryService {
    
    public Collection<FullProductDTO> getProducts();
	public FullProductDTO getProduct(Long id);

}
