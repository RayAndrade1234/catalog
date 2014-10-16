package la.random.sample.dialogs;

import la.random.sample.dialogs.interfaces.IEditProductDialogListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
//import android.support.v4.view.ViewPager.LayoutParams;
//import android.support.v4.widget.DrawerLayout.LayoutParams;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class EditDescriptionDialog  extends DialogFragment implements OnEditorActionListener{
	private EditText editDiscription;

    public EditDescriptionDialog() {
    	// Empty constructor required for DialogFragment
    	// ref: http://android-developers.blogspot.com/2012/05/using-dialogfragments.html
    }
    
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
    	View view = inflater.inflate(la.random.sample.R.layout.fragment_description, container);
    	editDiscription = (EditText) view.findViewById(la.random.sample.R.id.et_frag_edit_discription);
        getDialog().setTitle("Hello");
        
        // Show soft keyboard automatically
        editDiscription.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        editDiscription.setOnEditorActionListener(this);
        return view;
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		// Return input text to activity
		IEditProductDialogListener activity = (IEditProductDialogListener)getActivity();
		activity.onFinishEditDescription(editDiscription.getText().toString());
		this.dismiss();
        return true;

	}
	
}
