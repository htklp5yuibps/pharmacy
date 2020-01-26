package no.comp.finalproject.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Table;

@javax.persistence.Entity(name = "image")
@Table(name = "image")
public class Image extends Entity {

	@Column(name = "image")
	private Blob image;

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		return true;
	}
	
}
