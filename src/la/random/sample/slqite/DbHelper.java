package la.random.sample.slqite;

import java.text.SimpleDateFormat;
import java.util.Date;

import la.random.sample.model.ProductFieldList;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

	public DbHelper getDbHelper() {
		return dbHelper;
	}

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		DbHelper.context = context;
	}

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "cataloge";
	public static final String TABLE_NAME = "products";
	
	private static CursorFactory FACTORY = null;
	private DbHelper dbHelper;
	private static Context context;
	private SQLiteDatabase database;
	
	public SQLiteDatabase getDatabase(){
		return database;
	}

 	public DbHelper(Context context) {
		super(context, DATABASE_NAME, FACTORY, DATABASE_VERSION);
		dbHelper = this;
	}
	
	public static String getDateForDb(Date date){
		// TODO Error check
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-DD HH:mm:SS.SSS");
		String ret = formater.format(date);
		return ret;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql ="CREATE TABLE " + TABLE_NAME + " (";
		sql += ProductFieldList.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "; 
		
		int numFields = ProductFieldList.ALL_FIELDS.length;
		
		for(int i = 0; i < numFields; i++){
			
			if(i== (numFields-1)){
				sql += ProductFieldList.ALL_FIELDS[i] + " " + ProductFieldList.ALL_FTYPES[i] + ")";
			} else {
				sql += ProductFieldList.ALL_FIELDS[i] + " " + ProductFieldList.ALL_FTYPES[i] + ", ";
			}
		}
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);

	}
	
	protected DbHelper openWrite() throws SQLException{
		dbHelper = new DbHelper(context);
		database = dbHelper.getWritableDatabase();
		return this;
	}
	protected DbHelper openRead() throws SQLException{
		dbHelper = new DbHelper(context);
		database = dbHelper.getReadableDatabase();
		return this;
	}
	
	public void close(){
		database.close();
	}

}
