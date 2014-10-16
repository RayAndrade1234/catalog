package la.random.sample.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Product {
	
	
	private int id;
	private String productName; // A unique value for name lookup!!
	private String description;
	private Integer regularPrice;
	private Integer salePrice;
	private String photoPath;
	private int[] colors;
	private Set<Store> stores;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRegularPrice() {
		return regularPrice;
	}
	public void setRegularPrice(Integer regularPrice) {
		this.regularPrice = regularPrice;
	}
	public Integer getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public int[] getColors() {
		return colors;
	}
	public void setColors(int[] colors) {
		this.colors = colors;
	}
	//public void setColors(String s) {
	//	int[] colors = null;
	//	this.colors = colors;
	//}
	
	public Set<Store> getStores() {
		return stores;
	}
	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}
}
