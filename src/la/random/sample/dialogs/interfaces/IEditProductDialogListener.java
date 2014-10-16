package la.random.sample.dialogs.interfaces;

import android.graphics.Bitmap;
import android.widget.ImageView;

public interface IEditProductDialogListener {
	void onFinishEditDescription(String inputText);
	void onFinishTakePicture(String path);
}
