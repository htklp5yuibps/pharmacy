package no.comp.finalproject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@javax.persistence.Entity(name = "product")
@Table(name = "product")
public class Product extends Entity {
	@Column(name = "name")
	private String name;
	@Column(name = "info")
	private String info;
	@Column(name = "cost")
	private Double cost;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(
			name = "productimage",
			joinColumns = {
					@JoinColumn(name = "product_id")
			}
	)
	@Column(name = "image_id")
	private List<Long> images = new ArrayList<Long>();
	@Column(name = "status", insertable = false)
	private String status;
	@Column(name = "type")
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Long[] getImages() {
		if (images != null) {
			Long[] resultArray = new Long[images.size()];
			for (int i = 0; i < resultArray.length; i++) {
				resultArray[i] = images.get(i);
			}
			return resultArray;
		}
		
		return new Long[0];
	}

	public void setImages(Long[] images) {
		this.images = new ArrayList<Long>();
		for (Long _imageId: images) {
			this.images.add(_imageId);
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Product other = (Product) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", info=" + info + ", cost=" + cost + ", images=" + images + ", status=" + status
				+ ", type=" + type + ", id=" + id + "]";
	}

}
