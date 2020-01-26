package no.comp.finalproject.service;

import java.util.List;

import no.comp.finalproject.entity.Product;
import no.comp.finalproject.entity.constant.ProductStatus;

public interface ProductService extends BaseService<Product> {
	
	Product read(Long id);
	List<Product> read(String part);
	List<Product> read(ProductStatus status);
	List<Product> read(ProductStatus status, String part);
	
}
