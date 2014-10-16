package la.random.sample.slqite;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import la.random.sample.model.Product;
import la.random.sample.model.ProductFieldList;
import la.random.sample.model.Store;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ProductService extends DbHelper {

	public ProductService(Context context) {
		super(context);
		this.setContext(context);
	}

	public void deleteAll() {
		openWrite();
		SQLiteDatabase database = getDatabase();
		database.delete(TABLE_NAME, null, null);
		close();
	}

	public int getNumOfRecords() {
		String countQuery = "SELECT  * FROM " + TABLE_NAME;
		openRead();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int cnt = cursor.getCount();
		cursor.close();
		close();
		return cnt;
	}

	public int getNumOfRecords(String field, String value) {
		String select = "SELECT  * FROM " + TABLE_NAME;
		select += " WHERE " + field + "='" + value + "'";
		openRead();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(select, null);
		int cnt = cursor.getCount();
		cursor.close();
		close();
		return cnt;
	}

	public int getNumOfRecords(int Id) {
		String select = "SELECT  * FROM " + TABLE_NAME;
		select += " WHERE " + ProductFieldList.KEY_ID + "=" + Id;
		openRead();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(select, null);
		int cnt = cursor.getCount();
		cursor.close();
		close();
		return cnt;
	}

	public boolean recordExists(int id) {
		String countQuery = "SELECT  * FROM " + TABLE_NAME;
		countQuery += " WHERE " + ProductFieldList.KEY_ID + "=" + id + "";
		openRead();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int cnt = cursor.getCount();
		cursor.close();
		close();
		return cnt > 0;
	}

	public boolean recordExists(String name) {
		String countQuery = "SELECT  * FROM " + TABLE_NAME;
		countQuery += " WHERE " + ProductFieldList.FIELD_PRODUCT_NAME + "='" + name + "'";
		openRead();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int cnt = cursor.getCount();
		cursor.close();
		close();
		return cnt > 0;
	}

	public Product getProductById(int Id) {
		openRead();
		SQLiteDatabase db = getDatabase(); 
		String select = selectBase(); 
		select += " WHERE " + ProductFieldList.KEY_ID +"=" + Id; 
		
		Cursor cursor = db.rawQuery(select, null);
		Product product = null;
		
		if (cursor.moveToFirst()) {
			do {
				product = new Product();
				product.setId(cursor.getInt(cursor.getColumnIndex(ProductFieldList.KEY_ID)));
				product.setProductName(cursor.getString(cursor.getColumnIndex(ProductFieldList.FIELD_PRODUCT_NAME)));
				product.setDescription(cursor.getString(cursor.getColumnIndex(ProductFieldList.FIELD_DESCRIPTION)));
				product.setRegularPrice(cursor.getInt(cursor.getColumnIndex(ProductFieldList.FIELD_REGULAR_PRICE)));
				product.setSalePrice(cursor.getInt(cursor.getColumnIndex(ProductFieldList.FIELD_SALE_PRICE)));
				product.setPhotoPath(cursor.getString(cursor.getColumnIndex(ProductFieldList.FIELD_PHOTO_PATH)));
				
				int[] iArrayColors = getColors(cursor.getString(cursor.getColumnIndex(ProductFieldList.FIELD_COLORS)));
				
				product.setColors(iArrayColors);
				
				Set<Store> stores = getStores(cursor.getString(cursor.getColumnIndex(ProductFieldList.FIELD_STORES)));
				product.setStores(stores);
				
            } while (cursor.moveToNext());
		}
		close();
		return product;
	}

	private String selectBase() {
		String select = "SELECT " + ProductFieldList.KEY_ID + ", ";
		int numFields = ProductFieldList.ALL_FIELDS.length - 2 /*
																 * don't need
																 * create and
																 * update
																 */;

		for (int i = 0; i < numFields; i++) {
			if (i == (numFields - 1)) {
				select += ProductFieldList.ALL_FIELDS[i] + " ";
			} else {
				select += ProductFieldList.ALL_FIELDS[i] + ",";
			}
		}
		select += "FROM " + TABLE_NAME + " ";
		return select;
	}

	public Product getProductByName(String name) {
		openRead();
		SQLiteDatabase db = getDatabase();
		String select = selectBase();
		select += "WHERE  " + ProductFieldList.FIELD_PRODUCT_NAME + "='" + name + "'";

		Cursor cursor = db.rawQuery(select, null);

		Product product = null;
		if (cursor.moveToFirst()) {
			do {
				product = new Product();
				product.setId(cursor.getInt(cursor.getColumnIndex(ProductFieldList.KEY_ID)));
				product.setProductName(cursor.getString(cursor.getColumnIndex(ProductFieldList.FIELD_PRODUCT_NAME)));
				product.setDescription(cursor.getString(cursor.getColumnIndex(ProductFieldList.FIELD_DESCRIPTION)));
				product.setRegularPrice(cursor.getInt(cursor.getColumnIndex(ProductFieldList.FIELD_REGULAR_PRICE)));
				product.setSalePrice(cursor.getInt(cursor.getColumnIndex(ProductFieldList.FIELD_SALE_PRICE)));
				product.setPhotoPath(cursor.getString(cursor.getColumnIndex(ProductFieldList.FIELD_PHOTO_PATH)));

				int[] iArrayColors = getColors(cursor.getString(cursor.getColumnIndex(ProductFieldList.FIELD_COLORS)));
				product.setColors(iArrayColors);
				
				Set<Store> stores = getStores(cursor.getString(cursor.getColumnIndex(ProductFieldList.FIELD_STORES)));
				product.setStores(stores);
			} while (cursor.moveToNext());
		}
		close();
		return product;
	}

	public static int[] getColors(String json) {
		JSONObject jsonObj = null;
		JSONArray jColors = null;
		int[] iColors = null;
		try {
			jsonObj = new JSONObject(json);
			jColors = jsonObj.getJSONArray(ProductFieldList.FIELD_COLORS);
			iColors = new int[jColors.length()];
			for (int i = 0; i < jColors.length(); i++) {
				JSONObject jcolor = jColors.getJSONObject(i);
				String sColor = jcolor.getString("color");
				int icolor = Integer.parseInt(sColor);
				iColors[i] = icolor;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iColors;
	}

	public static Set<Store> getStores(String json){
		JSONObject jsonObj = null;
		JSONArray jStores = null;
		Set<Store> stores = new HashSet<Store>();
		Log.wtf("la.random.msg", "Set<Store> getStores(String json) "  + json);
		try{
			jsonObj = new JSONObject(json);
			jStores = jsonObj.getJSONArray(ProductFieldList.FIELD_STORES);
			for (int i = 0; i < jStores.length(); i++) {
				JSONObject jstore = jStores.getJSONObject(i);
				String sId = jstore.getString("store_id");
				String sName = jstore.getString("store_name");
				
				Store store = new Store();
				Integer id = Integer.parseInt(sId);
				store.setId(id);
				store.setStoreName(sName);
				stores.add(store);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stores;
	}

	public static String setStores(Set<Store> stores){
		
		JSONObject jsonStores = new JSONObject();
		try {
			JSONArray storeList = new JSONArray();
			for (Store s : stores) {
				JSONObject storeObj = new JSONObject();
				storeObj.put("store_id", s.getId());
				storeObj.put("store_name", s.getStoreName());
				storeList.put(storeObj);
			}
			jsonStores.put(ProductFieldList.FIELD_STORES, storeList);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.wtf("la.random.msg", "String setStores(Set<Store> stores) "  + jsonStores.toString());
		
		return jsonStores.toString();
	}
	
	public static String setColors(int[] colors){
		
		JSONObject jsonColors = new JSONObject();
		try {
			JSONArray colorArr = new JSONArray();
			for (int icolor : colors) {
				JSONObject colorObj = new JSONObject();
				colorObj.put("color", icolor);
				colorArr.put(colorObj);
			}
			jsonColors.put(ProductFieldList.FIELD_COLORS, colorArr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonColors.toString();
	}
	
	public ArrayList<Product> getProducts() {
		return null;
	}

	public void insertProduct(Product product) {
		String sColumnHack = null;
		ContentValues values = getProductContent(product);

		values.put(ProductFieldList.FIELD_LAST_UPDATE, getDateForDb(new Date()));
		values.put(ProductFieldList.KEY_ID, product.getId());

		if (productExists(product)) /* BY NAME OR BY ID */{
			int id = product.getId();
			updateExitingRecord(values, id);

		} else {
			insetNewRecord(values);
		}
	}

	private void updateExitingRecord(ContentValues values, int iD) {
		String sDate = getDateForDb(new Date());
		values.put(ProductFieldList.FIELD_LAST_UPDATE, sDate);

		openWrite();
		getDatabase().update(TABLE_NAME, values, ProductFieldList.KEY_ID + "= ?", new String[] { String.valueOf(iD) });
		close();

	}

	private void insetNewRecord(ContentValues values) {
		String sDate = getDateForDb(new Date());
		values.put(ProductFieldList.FIELD_LAST_UPDATE, sDate);
		values.put(ProductFieldList.FIELD_CREATED, sDate);
		openWrite();
		SQLiteDatabase database = getDatabase();
		database.insert(TABLE_NAME, null, values);
		close();
	}

	public void saveProduct(Product product) {
		ContentValues values = getProductContent(product);
		// note: ID is not included in the content values..
		// the question is "is the id in use? // do I just insert?? "
		values.put(ProductFieldList.FIELD_LAST_UPDATE, getDateForDb(new Date()));
		// It's a good practice to use parameter ?, instead of concatenate
		// string
		openWrite();
		getDatabase().update(TABLE_NAME, values, ProductFieldList.KEY_ID + "= ?",
				new String[] { String.valueOf(product.getId()) });
		close(); // Closing database connection

	}

	public void updateProduct(String field, String value, Product product) {
		ContentValues values = new ContentValues();
		values.put(field, value);
		openWrite();
		getDatabase().update(TABLE_NAME, values, ProductFieldList.KEY_ID + "= ?",
				new String[] { String.valueOf(product.getId()) });
		close();

	}

	public void updateProduct(String field, String value, int id) {
		ContentValues values = new ContentValues();
		Product product = getProductById(id);
		values.put(field, value);
		openWrite();
		getDatabase().update(TABLE_NAME, values, ProductFieldList.KEY_ID + "= ?",
				new String[] { String.valueOf(product.getId()) });
		close();
	}

	public void deleteProduct(Product product) {

	}

	public void deleteProduct(int id) {
		deleteProduct(getProductById(id));
	}

	public boolean productExists(Product product) {
		Product productById = getProductById(product.getId());
		Product productByName = getProductByName(product.getProductName());
		boolean bIdExists = (productById != null);
		boolean bNameExists = (productByName != null);
		return bIdExists || bNameExists;
	}

	public boolean productNameExists(String productName) {
		Product p = getProductByName(productName);
		if (p != null) {
			return true;
		}
		return false;
	}

	public ContentValues getProductContent(Product product) {
		ContentValues values = new ContentValues();
		values.put(ProductFieldList.FIELD_PRODUCT_NAME, product.getProductName());
		values.put(ProductFieldList.FIELD_DESCRIPTION, product.getDescription());
		values.put(ProductFieldList.FIELD_REGULAR_PRICE, product.getRegularPrice().toString());
		values.put(ProductFieldList.FIELD_SALE_PRICE, product.getSalePrice().toString());
		values.put(ProductFieldList.FIELD_PHOTO_PATH, product.getPhotoPath());
		
		values.put(ProductFieldList.FIELD_COLORS, setColors(product.getColors()));
		values.put(ProductFieldList.FIELD_STORES, setStores(product.getStores()));
		return values;
	}

}
