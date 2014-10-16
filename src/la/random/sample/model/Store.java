package la.random.sample.model;

public class Store {
	
	@Override
	public boolean equals(Object o) {
		Store s = (Store)o;
		boolean idsAreSame = (this.id == s.getId());
		boolean namesAreSame = (this.storeName.compareTo(s.storeName)==0);
		return namesAreSame && idsAreSame;
		
	}
	private int id;
	private String storeName;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
}
