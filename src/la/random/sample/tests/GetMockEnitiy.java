package la.random.sample.tests;

import java.util.HashSet;
import java.util.Set;

import la.random.sample.model.Product;
import la.random.sample.model.Store;
import android.util.Log;

public class GetMockEnitiy{
	public static Product getProduct(){
		int iKey  = 8;
		Product p = new Product();;
		p.setId(4);
		p.setProductName("Sleg-o-matic v:" + iKey);
		p.setDescription("The Ebola virus is killing 70 of the people who contract the disease the World Health Organization said Tuesday and as many as 10000 new cases a week could be reported by early December");
		p.setPhotoPath("/storage/emulated/0/DCIM/Camera/20141016_001240.jpg");
		p.setRegularPrice(12345);
		p.setSalePrice(4321);
		int [] i = { 1,2,3,4,5 };
		p.setColors(i);
		
		
		Set<Store> stores = new HashSet<Store>();
		
		Integer key = 0;
		int idxC = 0;
		
		key = idxC++;
		Store s1 = new Store();
		s1.setId(key);
		s1.setStoreName("Here");
		stores.add(s1);

		key = idxC++;
		Store s2 = new Store();
		s2.setId(key);
		s2.setStoreName("There");
		stores.add(s2);

		key = idxC++;
		Store s3 = new Store();
		s3.setId(key);
		s3.setStoreName("Same Bat Place");
		stores.add(s3);

		key = idxC++;
		Store s4 = new Store();
		s4.setId(key);
		s4.setStoreName("Everywhere");
		stores.add(s4);
		p.setStores(stores);

		return p;
	}
}
