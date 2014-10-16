package la.random.sample.json;

import la.random.sample.model.Product;
import la.random.sample.model.ProductFieldList;
import la.random.sample.model.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JSONhelper {
	
	public static Product fromJSON(String json) throws JSONException{
		JSONObject jsonObj = new JSONObject(json);
		
		Product product = new Product();
		product.setId(Integer.parseInt(jsonObj.getString(ProductFieldList.KEY_ID)));
		product.setProductName(jsonObj.getString(ProductFieldList.FIELD_PRODUCT_NAME));
		product.setDescription(jsonObj.getString(ProductFieldList.FIELD_DESCRIPTION));
		product.setRegularPrice(Integer.parseInt(jsonObj.getString(ProductFieldList.FIELD_REGULAR_PRICE)));
		product.setSalePrice(Integer.parseInt(jsonObj.getString(ProductFieldList.FIELD_SALE_PRICE)));
		product.setPhotoPath(jsonObj.getString(ProductFieldList.FIELD_PHOTO_PATH));
		
		
		JSONArray jColors = jsonObj.getJSONArray(ProductFieldList.FIELD_COLORS);
		int[] iColors = new int[jColors.length()];
		
		for(int i =0; i < jColors.length(); i++){
			JSONObject jcolor = jColors.getJSONObject(i); 
			String sColor = jcolor.getString("color");
			int icolor = Integer.parseInt(sColor);
			iColors[i] = icolor;
		}
		product.setColors(iColors);
		
		JSONArray jStores = jsonObj.getJSONArray(ProductFieldList.FIELD_STORES);
		
		Set<Store> stores = new HashSet<Store>();
		
		for(int i =0; i < jStores.length(); i++){
			JSONObject jstore = jStores.getJSONObject(i);
			String sId = jstore.getString("store_id");
			String sName = jstore.getString("store_name");
			
			Store store = new Store();
			Integer id = Integer.parseInt(sId);
			store.setId(id);
			store.setStoreName(sName);
			stores.add(store);
		}
		product.setStores(stores);
		return product;
	}
	
	public static String toJSON(Product product) {
		JSONObject jsonObj = new JSONObject();
		
		try {
			jsonObj.put(ProductFieldList.KEY_ID, product.getId());
			jsonObj.put(ProductFieldList.FIELD_PRODUCT_NAME, product.getProductName());
			jsonObj.put(ProductFieldList.FIELD_DESCRIPTION, product.getDescription());
			jsonObj.put(ProductFieldList.FIELD_REGULAR_PRICE, product.getRegularPrice());
			jsonObj.put(ProductFieldList.FIELD_SALE_PRICE, product.getSalePrice());
			jsonObj.put(ProductFieldList.FIELD_PHOTO_PATH, product.getPhotoPath());
			
			JSONArray colorArr = new JSONArray();
			for (int icolor : product.getColors() ) {
				JSONObject colorObj = new JSONObject();
				colorObj.put("color", icolor);
	            colorArr.put(colorObj);
	        }
			jsonObj.put(ProductFieldList.FIELD_COLORS, colorArr);
			
			
			JSONArray storeList = new JSONArray();
			Set<Store> stores = product.getStores();
			
			for (Store s : stores) {
				JSONObject storeObj = new JSONObject();
				storeObj.put("store_id", s.getId());
				storeObj.put("store_name", s.getStoreName());
				storeList.put(storeObj);
			}
			jsonObj.put(ProductFieldList.FIELD_STORES, storeList);
			

			return jsonObj.toString();
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

}
