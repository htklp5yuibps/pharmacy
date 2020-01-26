package no.comp.finalproject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import no.comp.finalproject.dao.ImageDao;
import no.comp.finalproject.entity.Image;

@Repository
@Transactional
public class HibernateImageDaoImpl implements ImageDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Long create(Image entity) {
		entityManager.persist(entity);
		return entity.getId();
	}

	@Override
	public List<Image> read() {
		return entityManager.createQuery("from image", Image.class)
				.getResultList();
	}

	@Override
	public Integer update(Image entity) {
		entityManager.merge(entity);
		return 1;
	}

	@Override
	public Image delete(Long id) {
		Image image = entityManager.find(Image.class, id);
		entityManager.detach(image);
		entityManager.createQuery("delete from image where id = :id")
			.setParameter("id", id)
			.executeUpdate();
		
		return image;
	}

	@Override
	public Image read(Long imageId) {
		return entityManager.find(Image.class, imageId);
	}

}
