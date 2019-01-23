package com.nokia.test.mobile.model.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nokia.test.mobile.R;
import com.nokia.test.mobile.model.Batter;
import com.nokia.test.mobile.model.Batters;
import com.nokia.test.mobile.model.DessertResponse;
import com.nokia.test.mobile.model.Topping;

import java.util.ArrayList;
import java.util.List;


public class DessertResponseControl {
    private SQLiteDatabase db;
    private Context context;
    static String tableName = "dessert";
    static String batterTable = "batter";
    static String toppingTable = "topping";

    /**
     * constructor class, instance a database
     */
    public DessertResponseControl(Context context) {
        this.context = context;
        SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter
                .getInstance(this.context);
        this.db = aSQLiteDatabaseAdapter.getWritableDatabase();
    }

    public long create(DessertResponse obj) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("id", obj.getId());
            cv.put("name", obj.getName());
            cv.put("type", obj.getType());
            cv.put("ppu", obj.getPpu());

            long rowid = db.insert(tableName,
                    context.getString(R.string.db_name), cv);
            createAllBatters(obj.getBatters().getBatter(), rowid);
            createAllTopping(obj.getTopping(), rowid);
            return rowid;
        } catch (Exception ex) {
            return -1;
        }
    }

    public boolean createAll(List<DessertResponse> objs) {
        boolean success = true;
        for (int i = 0; i < objs.size(); i++) {
            long result = create(objs.get(i));
            if (success && result == -1) {
                success = false;
            }
        }
        return success;
    }

    public DessertResponse getById(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = " + id;
        if (sql == null) {
            DessertResponse entry = null;
            return entry;
        }
        Cursor c = db.rawQuery(sql, null);
        DessertResponse entry = null;
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            entry = new DessertResponse(c.getInt(0), c.getString(1),
                    c.getString(2), c.getDouble(3), getBattersByIdDessert(c.getInt(4)),
                    getToppingByIdDessert(c.getInt(4)));
        }
        c.close();
        return entry;
    }

    public List<DessertResponse> getAll() {
        String sql = "SELECT * FROM " + tableName;
        Cursor c = db.rawQuery(sql, null);
        List<DessertResponse> objs = new ArrayList<DessertResponse>();
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                DessertResponse entry = new DessertResponse(c.getInt(0), c.getString(1),
                        c.getString(2), c.getDouble(3), getBattersByIdDessert(c.getInt(4)),
                        getToppingByIdDessert(c.getInt(4)));
                objs.add(entry);
                c.moveToNext();
            }
        }
        c.close();
        return objs;
    }

    public List<DessertResponse> getFiltered(String type) {
        String sql = "SELECT * FROM " + tableName + " WHERE type= '" + type+"'";
        Cursor c = db.rawQuery(sql, null);
        List<DessertResponse> objs = new ArrayList<DessertResponse>();
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                DessertResponse entry = new DessertResponse(c.getInt(0), c.getString(1),
                        c.getString(2), c.getDouble(3), getBattersByIdDessert(c.getInt(4)),
                        getToppingByIdDessert(c.getInt(4)));
                objs.add(entry);
                c.moveToNext();
            }
        }
        c.close();
        return objs;
    }

    public boolean removeDessert(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE id= " + id;
        try {
            db.execSQL(sql);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean removeAllDessert() {
        String sql = "DELETE * FROM " + tableName;
        try {
            db.execSQL(sql);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /*
     *
     * Batters
     * **/
    public long createBatters(Batter obj, long idDessert) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("id", obj.getId());
            cv.put("type", obj.getType());
            cv.put("id_dessert", idDessert);
            long rowid = db.insert(batterTable,
                    context.getString(R.string.db_name), cv);
            return rowid;
        } catch (Exception ex) {
            return -1;
        }
    }

    public boolean createAllBatters(List<Batter> objs, long idDessert) {
        boolean success = true;
        for (int i = 0; i < objs.size(); i++) {
            long result = createBatters(objs.get(i), idDessert);
            if (success && result == -1) {
                success = false;
            }
        }
        return success;
    }

    public Batters getBattersByIdDessert(int id) {
        String sql = "SELECT * FROM " + batterTable + " WHERE id_dessert = " + id;
        Cursor c = db.rawQuery(sql, null);
        List<Batter> objs = new ArrayList<Batter>();
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Batter entry = new Batter(c.getInt(0), c.getString(2));
                objs.add(entry);
                c.moveToNext();
            }
        }
        c.close();
        return new Batters(objs);
    }

    /*
     *
     * Toppings
     * **/
    public long createTopping(Topping obj, long idDessert) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("id", obj.getId());
            cv.put("type", obj.getType());
            cv.put("id_dessert", idDessert);
            long rowid = db.insert(toppingTable,
                    context.getString(R.string.db_name), cv);
            return rowid;
        } catch (Exception ex) {
            return -1;
        }
    }

    public boolean createAllTopping(List<Topping> objs, long idDessert) {
        boolean success = true;
        for (int i = 0; i < objs.size(); i++) {
            long result = createTopping(objs.get(i), idDessert);
            if (success && result == -1) {
                success = false;
            }
        }
        return success;
    }

    public List<Topping> getToppingByIdDessert(int id) {
        String sql = "SELECT * FROM " + toppingTable + " WHERE id_dessert = " + id;
        Cursor c = db.rawQuery(sql, null);
        List<Topping> objs = new ArrayList<Topping>();
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Topping entry = new Topping(c.getInt(0), c.getString(2));
                objs.add(entry);
                c.moveToNext();
            }
        }
        c.close();
        return objs;
    }
}
