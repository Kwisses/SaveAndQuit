package ca.kwisses.saveandquit.db_handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    private String[] userData;

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        userData = new String[5];
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                CIGPACKCOST + " TEXT, " +
                CIGSINPACK + " TEXT, " +
                CIGSPERDAY + " TEXT, " +
                DAYS + " TEXT " + ");";
        executeQuery(db, query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public boolean executeQuery(SQLiteDatabase db, String query) {
        try {
            if(query.length() > 0) {
                db.execSQL(query);
                return true;
            }
            return false;
        } catch(SQLException e) {
            throw new SQLException();
        } catch(NullPointerException e) {
            throw new NullPointerException();
        }
    }

    @Override
    public ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.get_id());
        values.put(CIGPACKCOST, user.getCigPackCost());
        values.put(CIGSINPACK, user.getCigsInPack());
        values.put(CIGSPERDAY, user.getCigsPerDay());
        values.put(DAYS, user.getDays());
        return values;
    }

    @Override
    public void addUser(User user) {
        ContentValues values = getContentValues(user);

        try(SQLiteDatabase db = getReadableDatabase()) {
            db.insert(TABLE_NAME, null, values);
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    @Override
    public void deleteUser(User user) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            String query = "DELETE FROM " + TABLE_NAME +
                    " WHERE " + COLUMN_ID + " = \"" + user.get_id() + "\";";
            db.execSQL(query);
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    @Override
    public String[] getUserData() {
        return this.userData;
    }

    @Override
    public String[] getUserDataFromDatabase() {
        String[] data = new String[5];
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";

        // Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst(); // Move to the first row in your results

        while(!c.isAfterLast()) {
            if(c.getString(c.getColumnIndex(COLUMN_ID)) != null) {
                data[0] = c.getString(c.getColumnIndex(COLUMN_ID));
            }
            if(c.getString(c.getColumnIndex(CIGPACKCOST)) != null) {
                data[1] = c.getString(c.getColumnIndex(CIGPACKCOST));
            }
            if(c.getString(c.getColumnIndex(CIGSINPACK)) != null) {
                data[2] = c.getString(c.getColumnIndex(CIGSINPACK));
            }
            if(c.getString(c.getColumnIndex(CIGSPERDAY)) != null) {
                data[3] = c.getString(c.getColumnIndex(CIGSPERDAY));
            }
            if(c.getString(c.getColumnIndex(DAYS)) != null) {
                data[4] = c.getString(c.getColumnIndex(DAYS));
            }
            c.moveToNext();
        }

        db.close();
        return data;
    }

    @Override
    public void setUserData(String[] userData) {
        this.userData = userData;
    }
}
