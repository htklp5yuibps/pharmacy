package no.comp.finalproject.dao;

import no.comp.finalproject.entity.Image;

public interface ImageDao extends BaseDao<Image> {
	Image read(Long imageId);
}
