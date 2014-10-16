package la.random.sample.dialogs;

import la.random.sample.dialogs.interfaces.IEditProductDialogListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;

public class TakePictureDialog extends DialogFragment {

	@Override
	public void onDismiss(DialogInterface dialog) {
		// TODO Auto-generated method stub
		super.onDismiss(dialog);
	}

	protected Button bt_frag_take_picture, btnGoBack;
	protected ImageView iv_frag_take_picture;

	public TakePictureDialog() {
		// Empty constructor required for DialogFragment
		// ref:
		// http://android-developers.blogspot.com/2012/05/using-dialogfragments.html
	}

	OnClickListener takePicureListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, 0);
		}
	};

	OnClickListener goBackListener = new OnClickListener() {
		public void onClick(View v) {
			Uri selectedImageUri = data.getData();
			path = getPath(selectedImageUri);
			activity.onFinishTakePicture(path);
			getDialog().dismiss();
		}
	};
	IEditProductDialogListener activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
		View view = inflater.inflate(la.random.sample.R.layout.fragment_take_picture, container);
		bt_frag_take_picture = (Button) view.findViewById(la.random.sample.R.id.bt_frag_take_picture);
		bt_frag_take_picture.setOnClickListener(takePicureListener);

		btnGoBack = (Button) view.findViewById(la.random.sample.R.id.btnGoBack);
		btnGoBack.setOnClickListener(goBackListener);

		iv_frag_take_picture = (ImageView) view.findViewById(la.random.sample.R.id.iv_frag_take_picture);

		activity = (IEditProductDialogListener) getActivity();
		return view;
	}

	public String getPath(Uri uri) {
	   
		String[] projection = { MediaStore.Images.Media.DATA };
	    @SuppressWarnings("deprecation")
	    Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
	    int column_index = cursor
	            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	    cursor.moveToFirst();
	    return cursor.getString(column_index);
	}

	protected Bitmap image;
	protected String path;
	protected Intent data;

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		this.data = data;
		if (requestCode == 0) {
			image = (Bitmap) data.getExtras().get("data");
			iv_frag_take_picture.setImageBitmap(image);
		}
	}

	// @Override
	public boolean onEditorAction(Object textView, int actionId, KeyEvent event) {
		// Return input image to activity
		Log.wtf("la.random.msg", "TakePictureDialog.onEditorAction()");

		if (EditorInfo.IME_ACTION_DONE == actionId) {
			IEditProductDialogListener activity = (IEditProductDialogListener) getActivity();
			activity.onFinishTakePicture(path);
			this.dismiss();
			return true;
		}
		return false;

	}

}
