package no.comp.finalproject.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {
	private Map<Long, Integer> parts;
	
	public Cart() {
		this.parts = new HashMap<Long, Integer>();
	}
	
	public void addProduct(Long productId, Integer count) {
		if (this.parts.containsKey(productId)) {
			int inCartCount = this.parts.get(productId);
			inCartCount += count;
			this.parts.replace(productId, inCartCount);
		} else {
			this.parts.put(productId, count);
		}
	}
	
	public void removeFromCart(Long productId, Integer count) {
		if (this.parts.containsKey(productId)) {
			Integer inCartCount = this.parts.get(productId);
			inCartCount -= count;
			this.parts.replace(productId, inCartCount);
		}
	}
	
	public void removeFromCart(Long productId) {
		if (this.parts.containsKey(productId)) {
			this.parts.remove(productId);
		}
	}
	
	public Map<Long, Integer> getParts() {
		return this.parts;
	}
	
	public void clearCart() {
		this.parts.clear();
	}
		
	public Integer getPartsCount() {
		return this.parts.size();
	}
	
	public Integer getItemsCount() {
		Set<Map.Entry<Long, Integer>> parts = this.parts.entrySet();
		Integer itemsCount = 0;
		for (Map.Entry<Long, Integer> part: parts) {
			itemsCount += part.getValue();
		}
		return itemsCount;
	}
	
	public Integer getPartItemsCount(Long productId) {
		return this.parts.get(productId);
	}
}
