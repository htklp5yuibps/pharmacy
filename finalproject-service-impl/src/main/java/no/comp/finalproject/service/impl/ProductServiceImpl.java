package no.comp.finalproject.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import no.comp.finalproject.dao.ProductDao;
import no.comp.finalproject.entity.Product;
import no.comp.finalproject.entity.constant.ProductStatus;
import no.comp.finalproject.service.ProductService;

public class ProductServiceImpl implements ProductService {

	private ProductDao pdao;
	
	public void setProductDao(ProductDao pdao) {
		this.pdao = pdao;
	}
	
	@Override
	public Long create(Product entity) {
		return this.pdao.create(entity);
	}

	@Override
	public List<Product> read() {
		return this.pdao.read();
	}

	@Override
	public Integer update(Product entity) {
		return this.pdao.update(entity);
	}

	@Override
	public Product delete(Long id) {
		return this.pdao.delete(id);
	}

	@Override
	public Product read(Long id) {
		return this.pdao.read().stream()
				.filter(product -> product.getId() == id)
				.findFirst().get();
	}

	@Override
	public List<Product> read(String part) {
		return this.filter(this.pdao.read(), part);
	}

	@Override
	public List<Product> read(ProductStatus status) {
		return this.filter(this.pdao.read(), status);
	}

	@Override
	public List<Product> read(ProductStatus status, String part) {
		List<Product> products = this.pdao.read();
		System.out.println(">>>>>>>>>>>>>ProductServiceImpl.read(ProductsStatus, String)>>>>>>>>>>>>");
		System.out.println(products.size());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		return this.filter(
				this.filter(products, status), part);
	}
	
	private List<Product> filter(List<Product> products, ProductStatus status) {
		if (products != null && !products.isEmpty() & status != null) {
			return products.stream()
					.filter(product -> product.getStatus().equals(status.toString().toLowerCase()))
					.collect(Collectors.toList());
		}
		return products;
	}
	
	private List<Product> filter(List<Product> products, String namePart) {
		if (products != null) {
			if (!products.isEmpty()) {
				if (namePart != null && !"".equals(namePart) ) {
					return products.stream()
							.filter(product -> product.getName()
									.toLowerCase().contains(namePart.toLowerCase()))
							.collect(Collectors.toList());
				}
			}
			return products;
		}
		
		return new ArrayList<Product>();
	}

}
