package la.random.sample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class TakePictureActivity extends Activity implements OnClickListener{

	protected Button btnTakePicture, btnTPBack;
	protected ImageView imageViewer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_take_picture);
		btnTakePicture = (Button) findViewById(R.id.btnTakePicture);
		btnTakePicture.setOnClickListener(this);
		btnTPBack = (Button) findViewById(R.id.btnTPBack);
		btnTPBack.setOnClickListener(this);
		imageViewer = (ImageView) findViewById(R.id.imageViewer);
	}

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		moveTaskToBack(false);
		TakePictureActivity.this.finish();

	}
	
	private void takePicture(){
		Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent,0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(requestCode==0){
			Bitmap image =(Bitmap)data.getExtras().get("data");
			imageViewer.setImageBitmap(image);
		}
	}
	
	
	
	@Override
	public void onClick(View v) {
		int idClicked = v.getId();
		switch(idClicked){
		case R.id.btnTPBack :{
			onBackPressed();
			break;
		}
		case R.id.btnTakePicture : {
			takePicture();
			break;
		}
		default:{
			Toast toast = Toast.makeText(this, "This choice is not implmented", 3000);
			toast.show();
		}
		}
		
	}
	
}
