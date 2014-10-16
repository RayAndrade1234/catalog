package la.random.sample.model;

public class ProductFieldList {
	public static final String KEY_ID = "_id";
	
	
	public static final String FIELD_PRODUCT_NAME = "product_name";   // TEXT
	public static final String FIELD_DESCRIPTION = "description";     // TEXT
	public static final String FIELD_REGULAR_PRICE = "regular_price"; // INTEGER divide by 100
	public static final String FIELD_SALE_PRICE = "sale_price";       // or move the decimal
	public static final String FIELD_PHOTO_PATH = "photo_path";       //?? BLOB
	public static final String FIELD_COLORS = "aval_colors";       //?? TEXT
	public static final String FIELD_STORES = "aval_stores";       //?? TEXT
	public static final String FIELD_CREATED = "product_created";     // TEXT "YYYY-MM-DD HH:MM:SS.SSS"
	public static final String FIELD_LAST_UPDATE = "product_updated"; // TEXT "YYYY-MM-DD HH:MM:SS.SSS"  // Also note that this allows you to compare easy by alpha

	public static final String FTYPE_PRODUCT_NAME = "TEXT NOT NULL UNIQUE";   // TEXT
	public static final String FTYPE_DESCRIPTION = "TEXT";     // TEXT
	public static final String FTYPE_REGULAR_PRICE = "INTEGER"; // INTEGER divide by 100
	public static final String FTYPE_SALE_PRICE = "INTEGER";       // or move the decimal
	public static final String FTYPE_PHOTO_PATH = "TEXT";       //?? BLOB
	public static final String FTYPE_COLORS = "TEXT";       //?? TEXT
	public static final String FTYPE_STORES = "TEXT";       //?? TEXT
	public static final String FTYPE_CREATED = "TEXT";     // TEXT "YYYY-MM-DD HH:MM:SS.SSS"
	public static final String FTYPE_LAST_UPDATE = "TEXT"; // TEXT "YYYY-MM-DD HH:MM:SS.SSS"  // Also note that this allows you to compare easy by alpha

	
	public static final String[] ALL_FIELDS ={FIELD_PRODUCT_NAME, FIELD_DESCRIPTION, FIELD_REGULAR_PRICE, FIELD_SALE_PRICE, FIELD_PHOTO_PATH,FIELD_COLORS, FIELD_STORES, FIELD_CREATED, FIELD_LAST_UPDATE};
	public static final String[] ALL_FTYPES ={FTYPE_PRODUCT_NAME, FTYPE_DESCRIPTION, FTYPE_REGULAR_PRICE, FTYPE_SALE_PRICE, FTYPE_PHOTO_PATH,FTYPE_COLORS, FTYPE_STORES, FTYPE_CREATED, FTYPE_LAST_UPDATE};
	

}
