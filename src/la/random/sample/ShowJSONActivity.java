package la.random.sample;

import la.random.sample.model.Product;
import la.random.sample.tests.GetMockEnitiy;
import la.random.sample.json.JSONhelper;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowJSONActivity extends Activity {
	TextView tvJSON;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_json);
		tvJSON = (TextView) findViewById(R.id.tvJSON);
		initValues();
	}
	
	private void initValues(){
		// This will use bundle and persistence...
		Product p = GetMockEnitiy.getProduct();
		
		String s = JSONhelper.toJSON(p);
		tvJSON.setText(s);
		
	}
	
}
