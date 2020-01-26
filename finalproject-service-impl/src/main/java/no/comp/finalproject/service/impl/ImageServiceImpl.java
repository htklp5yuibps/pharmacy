package no.comp.finalproject.service.impl;

import java.util.List;

import no.comp.finalproject.dao.ImageDao;
import no.comp.finalproject.entity.Image;
import no.comp.finalproject.service.ImageService;

public class ImageServiceImpl implements ImageService {

	private ImageDao idao;
	
	public void setImageDao(ImageDao idao) {
		this.idao = idao;
	}
	
	@Override
	public Long create(Image entity) {
		if (entity != null) {
			if (entity.getImage() != null) {
				return idao.create(entity);
			}
		} 
		throw new RuntimeException();
	}

	@Override
	public List<Image> read() {
		return idao.read();
	}

	@Override
	public Integer update(Image entity) {
		return null;
	}

	@Override
	public Image delete(Long id) {
		if (id != null && id >= 1) {
			return idao.delete(id);			
		}
		throw new RuntimeException();
	}

	@Override
	public Image read(Long imageId) {
		if (imageId != null && imageId >= 1) {
			return idao.read(imageId);
		}
		throw new RuntimeException();
	}

}
