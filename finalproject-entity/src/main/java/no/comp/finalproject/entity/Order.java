package no.comp.finalproject.entity;

import java.util.Date;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@javax.persistence.Entity(name = "order")
@Table(name = "`order`")
public class Order extends Entity {

	@Column(name = "user_id")
	private Long userId;
	@Column(name = "order_date", insertable = false)
	private Date orderDate;
	@Column(name = "end_date", insertable = false)
	private Date endDate;
	@Column(name = "status", insertable = false)
	private String status;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "orderdetail",
			joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
	@MapKeyColumn(name = "product_id")
	@Column(name = "count", table = "orderdetail")
	private Map<Long, Integer> parts;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<Long, Integer> getParts() {
		return parts;
	}

	public void setParts(Map<Long, Integer> parts) {
		this.parts = parts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((parts == null) ? 0 : parts.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Order other = (Order) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (parts == null) {
			if (other.parts != null)
				return false;
		} else if (!parts.equals(other.parts))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [userId=" + userId + ", orderDate=" + orderDate + ", endDate=" + endDate + ", status=" + status
				+ ", parts=" + parts + ", id=" + id + "]";
	}

}
