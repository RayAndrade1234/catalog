package la.random.sample.tests;

import junit.framework.Assert;
import android.content.Context;
import la.random.sample.FormViewActivity;
import la.random.sample.model.Product;
import la.random.sample.slqite.DbHelper;
import la.random.sample.slqite.ProductService;

public class ProductServicesTestCase extends android.test.ActivityInstrumentationTestCase2<FormViewActivity> {

	DbHelper dbHelper;
	Context context;
	ProductService productService;
	
	public ProductServicesTestCase(String name) {
		super(FormViewActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		context = this.getInstrumentation().getContext();
		dbHelper = new DbHelper(context);
		productService = new ProductService(context);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	public void testNull(){
		//Assert.assertNotNull(!null);
	}

	public void testCountAfterDelete(){
		productService.deleteAll();
		int iCount = productService.getNumOfRecords();
		Assert.assertEquals(0, iCount);
		//Assert.assertNotNull(!null);
	}
	public void testOneAfterDeleteAndAdd(){
		productService.deleteAll();
		Product product = GetMockEnitiy.getProduct();
		productService.insertProduct(product);
		int iCount = productService.getNumOfRecords();
		Assert.assertEquals(1, iCount);
	}
	
	public void testModelsAreEqual(){
		productService.deleteAll();
		Product product_in = GetMockEnitiy.getProduct();
		productService.insertProduct(product_in);
		Product product_out = productService.getProductById(product_in.getId());
		
		Assert.assertNotNull(product_out);
		
		Product product_not_there = productService.getProductById(product_in.getId()+1);
		if(product_not_there==null){
			//good
		} else {
			throw new AssertionError(" product does not exist ");
		}
		
		Product product_out_by_name = productService.getProductByName(product_in.getProductName());
		Assert.assertNotNull(product_out_by_name);
		
		Assert.assertEquals(product_in.getId(), product_out.getId());
		Assert.assertEquals(product_in.getProductName(), product_out.getProductName());
		Assert.assertEquals(product_in.getDescription(), product_out.getDescription());
		Assert.assertEquals(product_in.getRegularPrice(), product_out.getRegularPrice());
		Assert.assertEquals(product_in.getSalePrice(), product_out.getSalePrice());
		Assert.assertEquals(product_in.getColors(), product_out.getColors());
		Assert.assertEquals(product_in.getColors(), product_out.getStores());
		
		
		
		Assert.assertEquals(product_in.getId(), product_out_by_name.getId());
		Assert.assertEquals(product_in.getProductName(), product_out_by_name.getProductName());
		Assert.assertEquals(product_in.getDescription(), product_out_by_name.getDescription());
		Assert.assertEquals(product_in.getRegularPrice(), product_out_by_name.getRegularPrice());
		Assert.assertEquals(product_in.getSalePrice(), product_out_by_name.getSalePrice());
		Assert.assertEquals(product_in.getColors(), product_out_by_name.getStores());
		
		
		//Assert.assertEquals(product_in.getProductName(), product_out.getProductName());
		
		
		
		
	}

}
