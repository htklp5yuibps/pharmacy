package no.comp.finalproject.service;

import no.comp.finalproject.entity.Image;

public interface ImageService extends BaseService<Image> {
	Image read(Long imageId);
}
