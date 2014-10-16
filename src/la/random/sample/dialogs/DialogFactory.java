package la.random.sample.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Activity;
import android.content.DialogInterface;

public class DialogFactory {
	
	public static Dialog getNullDialog(){
		return null;
	}
	
	public static Dialog getAlert1Dialog(Activity activity){
		Dialog dialog;
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(activity);
        builder.setMessage("Danger Will Robinson!!!")
        	.setCancelable(false)
        	.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Do something here
            }
        });
        dialog = builder.create();
		return dialog;
	}
	public static Dialog getAlert2Dialog(Activity activity){
		Dialog dialog;
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(activity);
        builder.setMessage("Danger Will Robinson!!!")
        	.setCancelable(false)
        	.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Do something here
            }
        });
        dialog = builder.create();
		return dialog;
	}

}
