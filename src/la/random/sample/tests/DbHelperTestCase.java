package la.random.sample.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.json.JSONException;

import junit.framework.Assert;
import android.content.Context;
import android.util.Log;
import la.random.sample.json.JSONhelper;
import la.random.sample.model.Product;
import la.random.sample.model.ProductFieldList;
import la.random.sample.model.Store;
import la.random.sample.slqite.DbHelper;
import la.random.sample.slqite.ProductService;
import la.random.sample.FormViewActivity;

public class DbHelperTestCase extends android.test.ActivityInstrumentationTestCase2<FormViewActivity> {

	DbHelper dbHelper;
	Context context;
	ProductService productService;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		context = this.getInstrumentation().getContext();
		dbHelper = new DbHelper(context);
		productService = new ProductService(context);
		
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public DbHelperTestCase(){
		super(FormViewActivity.class);
	}
	
	
	public void testNotNull(){
		Assert.assertNotNull(dbHelper);
		Assert.assertNotNull(dbHelper.getDbHelper());
		Assert.assertNotNull(productService);
	}
	public void testDeleteAllWorks(){
		productService.deleteAll();
		int iCount = productService.getNumOfRecords();
		Assert.assertEquals(0, iCount);
		
	}

	public void testInsertWorks(){
		productService.deleteAll();
		int iCount = productService.getNumOfRecords();
		Assert.assertEquals(0, iCount);
		Product product = GetMockEnitiy.getProduct();
		productService.insertProduct(product);
	}
	
	public void testArrayOfInts(){
		productService.deleteAll();
		Product product_in = GetMockEnitiy.getProduct();
		productService.insertProduct(product_in);
		
		String s = JSONhelper.toJSON(product_in);
		Product product_db_id = productService.getProductById(product_in.getId());
		Product product_db_name = productService.getProductByName(product_in.getProductName());
		Product product_out_json = null;
		try {
			product_out_json = JSONhelper.fromJSON(s);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[] test_in = product_in.getColors();
		int[] test_json = product_out_json.getColors();
		int[] test_db_id = product_db_id.getColors();
		int[] test_db_name = product_db_name.getColors();
		
		
		for(int i =0; i < test_in.length; i++){
			Assert.assertEquals(test_in[i], test_json[i]);
			Assert.assertEquals(test_in[i], test_db_id[i]);
			Assert.assertEquals(test_in[i], test_db_name[i]);
		}
	}
	
	public void testRetriveObjectWorks(){
		productService.deleteAll();
		Assert.assertEquals(0, productService.getNumOfRecords());
		
		Product product_in = GetMockEnitiy.getProduct();
		productService.insertProduct(product_in);
		Assert.assertEquals(1, productService.getNumOfRecords());
		
		Assert.assertEquals(1, productService.getNumOfRecords(ProductFieldList.FIELD_PRODUCT_NAME, product_in.getProductName()));
		Assert.assertEquals(1, productService.getNumOfRecords(product_in.getId()));
		
		Assert.assertEquals(true, productService.recordExists(product_in.getProductName()));
		Assert.assertEquals(true, productService.recordExists(product_in.getId()));
		
		String s = JSONhelper.toJSON(product_in);
		
		// Setup the model objects
		Product product_db_id = productService.getProductById(product_in.getId());
		
		
		Product product_db_name = productService.getProductByName(product_in.getProductName());
		Product product_out_json = null;
		Assert.assertEquals(null, product_out_json);
		try {
			product_out_json = JSONhelper.fromJSON(s);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Not null
		Assert.assertNotNull(product_db_id);
		Assert.assertNotNull(product_db_name);
		Assert.assertNotNull(product_out_json);
		
		// Compare to the original Product
		Assert.assertEquals(product_in.getId(), product_db_id.getId());
		Assert.assertEquals(product_in.getId(), product_db_name.getId());
		Assert.assertEquals(product_in.getId(), product_out_json.getId());

		Assert.assertEquals(product_in.getProductName(), product_db_id.getProductName());
		Assert.assertEquals(product_in.getProductName(), product_db_name.getProductName());
		Assert.assertEquals(product_in.getProductName(), product_out_json.getProductName());

		Assert.assertEquals(product_in.getDescription(), product_db_id.getDescription());
		Assert.assertEquals(product_in.getDescription(), product_db_name.getDescription());
		Assert.assertEquals(product_in.getDescription(), product_out_json.getDescription());
		
		Assert.assertEquals(product_in.getRegularPrice(), product_db_id.getRegularPrice());
		Assert.assertEquals(product_in.getRegularPrice(), product_db_name.getRegularPrice());
		Assert.assertEquals(product_in.getRegularPrice(), product_out_json.getRegularPrice());
		
		Assert.assertEquals(product_in.getSalePrice(), product_db_id.getSalePrice());
		Assert.assertEquals(product_in.getSalePrice(), product_db_name.getSalePrice());
		Assert.assertEquals(product_in.getSalePrice(), product_out_json.getSalePrice());
		
		
	}

	
	public void testStoreComparison(){
		productService.deleteAll();
		Product product_in = GetMockEnitiy.getProduct();
		productService.insertProduct(product_in);
		
		String s = JSONhelper.toJSON(product_in);
		Product product_db_id = productService.getProductById(product_in.getId());
		Product product_db_name = productService.getProductByName(product_in.getProductName());
		Product product_out_json = null;
		try {
			product_out_json = JSONhelper.fromJSON(s);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<Store> store_in = product_in.getStores();
		Set<Store> store_json = product_out_json.getStores();
		Set<Store> store_db_id = product_db_id.getStores();
		Set<Store> store_db_name = product_db_name.getStores();
		
		int test_sb = 0;
		int test_is = 0;
		
		test_sb =store_in.size();
		test_is = store_json.size();
		Assert.assertEquals(test_sb, test_is);

		test_is = store_db_id.size();
		Assert.assertEquals(test_sb, test_is);
		
		test_is = store_db_name.size();
		Assert.assertEquals(test_sb, test_is);

		
		List<Store> list_in  = new ArrayList<Store>();
		list_in.addAll(store_in);
		
		List<Store> list_json  = new ArrayList<Store>();
		list_json.addAll(store_json);
		
		List<Store> list_db_id  = new ArrayList<Store>();
		list_db_id.addAll(store_db_id);
		
		List<Store> list_db_name  = new ArrayList<Store>();
		list_db_name.addAll(store_db_name);
		
		Assert.assertTrue(list_in.containsAll(list_json));
		Assert.assertTrue(list_in.containsAll(list_db_id));
		Assert.assertTrue(list_in.containsAll(list_db_name));

	}
}
