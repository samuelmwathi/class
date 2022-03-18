package databaseModule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;


import student_model.Product;

public class Db extends SQLiteOpenHelper {
    public static final String PRODUCT_TABLE = "products";
    public static final String ID = "id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String BUYING_PRICE = "buying_price";
    public static final String SELLING_PRICE = "selling_price";
    public static final String PRODUCT_URL = "Product_url";

    public Db(Context context) {
        super(context, "product.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = " create table " + PRODUCT_TABLE + "(" + ID + " INTEGER primary key autoincrement, " + PRODUCT_NAME + " text," + BUYING_PRICE + " text," + SELLING_PRICE +
                " text, " + PRODUCT_URL + " text )";
        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean post(Product product) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_NAME, product.getName());
        contentValues.put(BUYING_PRICE, product.getBuying_price());
        contentValues.put(SELLING_PRICE, product.getSelling_pice());
        contentValues.put(PRODUCT_URL,product.getImg_url().toString());
        long insert = database.insert(PRODUCT_TABLE, null, contentValues);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updata(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_NAME, product.getName());
        contentValues.put(BUYING_PRICE, product.getBuying_price());
        contentValues.put(SELLING_PRICE, product.getSelling_pice());

        String select_statement = "select * from " + PRODUCT_TABLE + "where" + product.getName() + " =? ";
        Cursor cursor = db.rawQuery(select_statement, new String[]{PRODUCT_NAME});
        if (cursor.getCount() > 0) {
            int update = db.update(PRODUCT_TABLE, contentValues, select_statement, new String[]{PRODUCT_NAME});
            if (update == -1) {
                return false;
            } else return true;
        } else return false;
    }

    public boolean delete(String regno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String select_statement = "select * from " + PRODUCT_TABLE + "where" + regno + " = ? ";
        Cursor cursor = db.rawQuery(select_statement, new String[]{PRODUCT_NAME});
        if (cursor.getCount() > 0) {
            int delete = db.delete(PRODUCT_TABLE, select_statement, new String[]{PRODUCT_NAME});
            if (delete == -1) {
                return false;
            } else return true;
        } else return false;
    }
    public ArrayList<Product> fetch() {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<Product> products = new ArrayList<>();
        String select_statement = "select * FROM " + PRODUCT_TABLE;
        Cursor cursor = database.rawQuery(select_statement, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String buying = cursor.getString(2);
                String selling = cursor.getString(3);
                String url = cursor.getString(4);
                ///add image
                Product product = new Product(name, buying, selling,Uri.parse(url));
                products.add(product);
            } while (cursor.moveToNext());
        }
        return products;
    }

}
