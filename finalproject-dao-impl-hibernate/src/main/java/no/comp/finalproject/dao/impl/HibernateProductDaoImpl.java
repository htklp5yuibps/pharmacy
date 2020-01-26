package no.comp.finalproject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import no.comp.finalproject.dao.ProductDao;
import no.comp.finalproject.entity.Product;
import no.comp.finalproject.entity.constant.ProductStatus;

@Repository
@Transactional
public class HibernateProductDaoImpl implements ProductDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Long create(Product entity) {
		entityManager.persist(entity);
		return entity.getId();
	}

	@Override
	public List<Product> read() {
		List<Product> products = entityManager.createQuery("from product", Product.class)
				.getResultList();
		
		System.out.println(">>>>>>>>>>>>>HibernateProductDaoImpl.read()>>>>>>>>>>>>");
		System.out.println(products.size());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		return products;
	}

	@Override
	public Integer update(Product entity) {
		entityManager.merge(entity);
		return 1;
	}

	@Override
	public Product delete(Long id) {
		Product product = entityManager.find(Product.class, id);
		product.setStatus(ProductStatus.UNAVAILABLE.toString().toLowerCase());
		entityManager.merge(product);
		return product;
	}

}
