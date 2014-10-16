package la.random.sample.tests;

import org.json.JSONException;

import android.content.Context;
import junit.framework.Assert;
import la.random.sample.FormViewActivity;
import la.random.sample.json.JSONhelper;
import la.random.sample.model.Product;
import la.random.sample.slqite.DbHelper;
import la.random.sample.slqite.ProductService;

public class JSONhelperTestCase extends android.test.ActivityInstrumentationTestCase2<FormViewActivity> {
	
	DbHelper dbHelper;
	Context context;
	ProductService productService;
	
	public JSONhelperTestCase(String name) {
		super(FormViewActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		context = this.getInstrumentation().getContext();
		dbHelper = new DbHelper(context);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testInOut(){
		Product product_in = GetMockEnitiy.getProduct();
		String s = JSONhelper.toJSON(product_in);
		Product product_out = null;
		try {
			product_out = JSONhelper.fromJSON(s);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(product_in.getId(), product_out.getId());
		Assert.assertEquals(product_in.getProductName(), product_out.getProductName());
		Assert.assertEquals(product_in.getDescription(), product_out.getDescription());
		Assert.assertEquals(product_in.getRegularPrice(), product_out.getRegularPrice());
		Assert.assertEquals(product_in.getSalePrice(), product_out.getSalePrice());
		Assert.assertEquals(product_in.getPhotoPath(), product_out.getPhotoPath());
		//Assert.assertEquals(product_in.getColors(), product_out.getColors());
		//Assert.assertEquals(product_in.getStores(), product_out.getStores());
		
	}
	
	public void testCompare(){
		productService.deleteAll();
		Product product_in = GetMockEnitiy.getProduct();
		productService.insertProduct(product_in);
		
		Product product_out_id = productService.getProductById(product_in.getId());
		Product product_out_name = productService.getProductByName(product_in.getProductName());
		String sJSON = JSONhelper.toJSON(product_in);
		
		Product product_out_json = null;
		try {
			product_out_json = JSONhelper.fromJSON(sJSON);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals(product_in.getId(), product_out_id.getId());
		Assert.assertEquals(product_in.getId(), product_out_name.getId());
		Assert.assertEquals(product_in.getId(), product_out_json.getId());
		
	}

}
