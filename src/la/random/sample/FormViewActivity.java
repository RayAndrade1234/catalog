package la.random.sample;

import java.math.BigDecimal;

import la.random.sample.model.Product;
import la.random.sample.tests.GetMockEnitiy;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FormViewActivity extends Activity {

	protected TextView tvId, tvProductName, tvDescription, tvRegularPrice, tvSalePrice, tvPhotoPath, tvColors, tvStores;
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_form_view);
		tvId = (TextView) findViewById(R.id.tvId);
		tvProductName = (TextView) findViewById(R.id.tvProductName);
		tvDescription = (TextView) findViewById(R.id.tvDescription);
		tvRegularPrice = (TextView) findViewById(R.id.tvRegularPrice);
		tvSalePrice = (TextView) findViewById(R.id.tvSalePrice);
		tvPhotoPath = (TextView) findViewById(R.id.tvPhoto);
		tvColors = (TextView) findViewById(R.id.tvColors);
		tvStores = (TextView) findViewById(R.id.tvStores);
		initValues();
	}
	
	
	private String getDollerSrt(int iVal){
		BigDecimal bg2 = new BigDecimal(String.valueOf(iVal));
		BigDecimal bg4 = bg2.movePointRight(-2);// 2 places left
		return "$" + String.valueOf(bg4);
	}
	
	private String intArrayToString(int[] iArray){
		String s = "[";
		for(int i = 0; i < iArray.length; i++){
			s += String.valueOf(i);
			if(i != iArray.length-1){
				s+= ",";
			}
		}
		s+= "]";
		return s;
	}
	
	
	
	private void initValues(){
		Product p = GetMockEnitiy.getProduct();
		tvId.setText( String.valueOf(p.getId()));
		tvProductName.setText(p.getProductName());
		tvDescription.setText(p.getDescription());
		tvRegularPrice.setText(getDollerSrt(p.getRegularPrice()));
		tvSalePrice.setText(getDollerSrt(p.getSalePrice()));
		tvPhotoPath.setText(p.getPhotoPath());
		String sColors = String.valueOf(intArrayToString(p.getColors()));
		tvColors.setText(sColors);
		tvStores.setText(p.getStores().toString());
	}
}
