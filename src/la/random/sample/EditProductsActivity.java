package la.random.sample;

import java.util.Set;

import la.random.sample.model.Product;
import la.random.sample.model.ProductFieldList;
import la.random.sample.model.Store;
import la.random.sample.dialogs.TakePictureDialog;
import la.random.sample.dialogs.interfaces.IEditProductDialogListener;
import la.random.sample.slqite.DbHelper;
import la.random.sample.slqite.ProductService;
import la.random.sample.tests.GetMockEnitiy;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditProductsActivity extends FragmentActivity implements OnClickListener, IEditProductDialogListener {

	protected EditText etId, etProductName, etDescription, etRegularPrice, etSalePrice, etColors, etStores;
	protected Button btSave_ep, btTakePicture;
	protected ImageView ivPhotoPath;
	protected String s_PhotoPath;
	
	private ProductFieldList p;
	// For leaving the screen
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Context context = this.getApplicationContext();
		SharedPreferences prefs = context.getSharedPreferences("mypref", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		
		editor.putString(p.KEY_ID, etId.getText().toString());
		editor.putString(p.FIELD_PRODUCT_NAME, etProductName.getText().toString());
		editor.putString(p.FIELD_DESCRIPTION, etDescription.getText().toString());
		editor.putString(p.FIELD_REGULAR_PRICE, etRegularPrice.getText().toString());
		editor.putString(p.FIELD_SALE_PRICE, etSalePrice.getText().toString());
		editor.putString(p.FIELD_PHOTO_PATH, s_PhotoPath);
		editor.putString(p.FIELD_COLORS, etColors.getText().toString());
		editor.putString(p.FIELD_STORES, etStores.getText().toString());
		editor.commit();

	}

	@Override
	protected void onResume() {
		super.onResume();
		Context context = this.getApplicationContext();

		SharedPreferences prefs = context.getSharedPreferences(DbHelper.TABLE_NAME, Context.MODE_PRIVATE);
		String sId = prefs.getString(p.KEY_ID, "");
		String sProductName = prefs.getString(p.FIELD_PRODUCT_NAME, "");
		String sDescription = prefs.getString(p.FIELD_DESCRIPTION, "");
		String sRegularPrice = prefs.getString(p.FIELD_REGULAR_PRICE, "");
		String sSalePrice = prefs.getString(p.FIELD_SALE_PRICE, "");
		String sColors = prefs.getString(p.FIELD_COLORS, "");
		String sPhotoPath = prefs.getString(p.FIELD_PHOTO_PATH, "");
		String sStores = prefs.getString(p.FIELD_STORES, "");
		
		etId.setText(sId);
		etProductName.setText(sProductName);
		etDescription.setText(sDescription);
		etRegularPrice.setText(sRegularPrice);
		etSalePrice.setText(sSalePrice);
		etColors.setText(sColors);
		etStores.setText(sStores);
		s_PhotoPath = sPhotoPath;
		ivPhotoPath.setImageBitmap(BitmapFactory.decodeFile(s_PhotoPath));
		
	}
	
	// For screen rotation -- and when dialog is active
	@Override
	protected void onSaveInstanceState(Bundle bundle) {
		super.onSaveInstanceState(bundle);
		
		bundle.putString(p.KEY_ID, etId.getText().toString());
		bundle.putString(p.FIELD_PRODUCT_NAME, etProductName.getText().toString());
		bundle.putString(p.FIELD_DESCRIPTION, etDescription.getText().toString());
		bundle.putString(p.FIELD_REGULAR_PRICE, etRegularPrice.getText().toString());
		bundle.putString(p.FIELD_SALE_PRICE, etSalePrice.getText().toString());
		bundle.putString(p.FIELD_COLORS, etColors.getText().toString());
		bundle.putString(p.FIELD_PHOTO_PATH, s_PhotoPath);
		bundle.putString(p.FIELD_STORES, etStores.getText().toString());
		
	}

	@Override
	protected void onRestoreInstanceState(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(bundle);
		// etId.setText(bundle.getString("Id", ""));
		etId.setText(bundle.getString(p.KEY_ID));
		etProductName.setText(bundle.getString(p.FIELD_PRODUCT_NAME));
		etDescription.setText(bundle.getString(p.FIELD_DESCRIPTION));
		etRegularPrice.setText(bundle.getString(p.FIELD_REGULAR_PRICE));
		etSalePrice.setText(bundle.getString(p.FIELD_SALE_PRICE));
		//ivPhotoPath
		s_PhotoPath= bundle.getString(p.FIELD_PHOTO_PATH);
		etColors.setText(bundle.getString(p.FIELD_COLORS));
		etStores.setText(bundle.getString(p.FIELD_STORES));
	}

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_edit_product);
		etId = (EditText) findViewById(R.id.etId);
		etProductName = (EditText) findViewById(R.id.etProductName);
		etDescription = (EditText) findViewById(R.id.etDescription);
		etRegularPrice = (EditText) findViewById(R.id.etRegularPrice);
		etSalePrice = (EditText) findViewById(R.id.etSalePrice);
		ivPhotoPath = (ImageView) findViewById(R.id.ivPhotoPath);
		etColors = (EditText) findViewById(R.id.etColors);
		etStores = (EditText) findViewById(R.id.etStores);
		btSave_ep = (Button) findViewById(R.id.btSave_ep);
		btSave_ep.setOnClickListener(this);
		
		btTakePicture = (Button) findViewById(R.id.btTakePicture);
		btTakePicture.setOnClickListener(this);
		Log.wtf("la.random.msg", "made it this");
		
	}

	private void showDialogTakePicture() {
		FragmentManager fm = getSupportFragmentManager();
		TakePictureDialog takePictureDialog = new TakePictureDialog();
		takePictureDialog.show(fm, "fragment_take_picture");
	}

	@Override
	public void onFinishTakePicture(String path) {
		s_PhotoPath = path;
		Log.wtf("la.random.msg", "path" + path);
		ivPhotoPath.setImageBitmap(BitmapFactory.decodeFile(s_PhotoPath));
	}

	private boolean isValid(){
		return false;
	}
	private int getIntValOfPrice(String s){
		int iReturn = 0;
		String sValue = s.replace("$","").replace("$",".");
		try{
			iReturn = Integer.parseInt(sValue);
		} catch(Exception e){
			// Bad value return 0;
		}
		return iReturn;
	}
	
	private void clearForm(){
		etId.setText("");
		etProductName.setText("");
		etDescription.setText("");
		etRegularPrice.setText("");
		etSalePrice.setText("");
		ivPhotoPath.setImageBitmap(null);
		s_PhotoPath = "";
		etColors.setText("");
		etStores.setText("");
		
	}
	
	private void save(){
		loadViewWithMockObject();
		
		if(isValid()){
			Product p = new Product();
			p.setProductName(etProductName.getText().toString());
			p.setDescription(etDescription.getText().toString());
			p.setRegularPrice(getIntValOfPrice(etRegularPrice.getText().toString()));
			p.setSalePrice(getIntValOfPrice(etSalePrice.getText().toString()));
			
			int[] iArrayColors = ProductService.getColors(etColors.getText().toString());
			p.setColors(iArrayColors);
			p.setPhotoPath(s_PhotoPath);
			
			Set<Store> stores = ProductService.getStores(etStores.getText().toString());
			p.setStores(stores);

			ProductService ps = new ProductService(getApplicationContext());
			ps.insertProduct(p);
			Toast toast = Toast.makeText(this, "Saved", 3000);
			toast.show();
		}
	}
	
	@Override
	public void onClick(View v) {
		int idClicked = v.getId();
		switch (idClicked) {
		case R.id.btTakePicture: {
			showDialogTakePicture();
			break;
		}
		case R.id.btSave_ep: {
			save();
			break;
		}
		default: {
			Toast toast = Toast.makeText(this, "This choice is not implmented", 3000);
			toast.show();
		}
		}

	}

	@Override
	public void onFinishEditDescription(String inputText) {
		// etDescription.setText(inputText);
	}
	
	private void loadViewWithMockObject(){
		Product p = GetMockEnitiy.getProduct();
		String sVal = "";
		
		sVal = "" + p.getId();
		etId.setText(sVal);
		
		sVal = "" + p.getProductName();
		etProductName.setText(sVal);
		
		sVal = "" + p.getDescription();
		etDescription.setText(sVal);
		
		sVal = "" + p.getRegularPrice();
		etRegularPrice.setText(sVal);
		
		sVal = "" + p.getSalePrice();
		etSalePrice.setText(sVal);
		
		s_PhotoPath = p.getPhotoPath();
		
		ivPhotoPath.setImageBitmap(BitmapFactory.decodeFile(s_PhotoPath));
		
		sVal = ProductService.setColors(p.getColors());
		etColors.setText(sVal);
		
		sVal = ProductService.setStores(p.getStores());
		etStores.setText(sVal);
		
		
	}

}
