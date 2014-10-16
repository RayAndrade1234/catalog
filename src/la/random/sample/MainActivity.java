package la.random.sample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	private Class<?>[] activites;
	private String[] titles;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		activites = new Class[4];
		int idx = 0;
		activites[idx++] = EditProductsActivity.class;
		activites[idx++] = ShowJSONActivity.class;
		activites[idx++] = FormViewActivity.class;
		activites[idx++] = TakePictureActivity.class;
		titles = new String[activites.length];
		for (int i = 0; i < titles.length; i++) {
			titles[i] = activites[i].getName().replace("la.random.sample.", "");
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, 
				android.R.layout.simple_list_item_1, titles);

		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent intent = getIntent(activites[position]);
		startActivity(intent);
	}
	private Intent getIntent(Class<?> target) {
		Intent intent = new Intent(this, target);
		intent.putExtras(getBundle());
		return intent;
	}
	private Bundle getBundle() {
		Bundle bundle = new Bundle();
		//bundle.putString("key", value);
		return bundle;
	}
	
}
