package ca.kwisses.saveandquit.db_handler;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import ca.kwisses.saveandquit.user.User;

public class DBHandler extends SQLiteOpenHelper implements DBHandlerContract.Presenter {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "users";

    private final String COLUMN_ID = "_id";
    private final String CIGPACKCOST = "_cigPackCost";
    private final String CIGSINPACK = "_cigsInPack";
    private final String CIGSPERDAY = "_cigsPerDay";
    private final String DAYS = "_days";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CIGPACKCOST + " TEXT, " +
                CIGSINPACK + " TEXT, " +
                CIGSPERDAY + " TEXT, " +
                DAYS + " TEXT " + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.get_id());
        values.put(CIGPACKCOST, user.getCigPackCost());
        values.put(CIGSINPACK, user.getCigsInPack());
        values.put(CIGSPERDAY, user.getCigsPerDay());
        values.put(DAYS, user.getDays());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = \"" + user.get_id() + "\";");
    }

    public String[] getUserData() {
        String[] userData = new String[5];
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";

        // Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst(); // Move to the first row in your results

        while(!c.isAfterLast()) {
            if(c.getString(c.getColumnIndex(COLUMN_ID)) != null) {
                userData[0] = c.getString(c.getColumnIndex(COLUMN_ID));
            }
            if(c.getString(c.getColumnIndex(CIGPACKCOST)) != null) {
                userData[1] = c.getString(c.getColumnIndex(CIGPACKCOST));
            }
            if(c.getString(c.getColumnIndex(CIGSINPACK)) != null) {
                userData[2] = c.getString(c.getColumnIndex(CIGSINPACK));
            }
            if(c.getString(c.getColumnIndex(CIGSPERDAY)) != null) {
                userData[3] = c.getString(c.getColumnIndex(CIGSPERDAY));
            }
            if(c.getString(c.getColumnIndex(DAYS)) != null) {
                userData[4] = c.getString(c.getColumnIndex(DAYS));
            }
            c.moveToNext();
        }

        db.close();
        return userData;
    }
}
