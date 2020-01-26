package no.comp.finalproject.service;

import java.util.List;

import no.comp.finalproject.domain.Product;
import no.comp.finalproject.domain.constant.ProductStatus;

public interface ProductService extends BaseService<Product> {
	
	Product read(Long id);
	List<Product> read(String part);
	List<Product> read(ProductStatus status);
	List<Product> read(ProductStatus status, String part);
	
}
