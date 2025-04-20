package com.example.app.Controller;

import static com.example.app.Db.SqliteDatabase.Cname;
import static com.example.app.Db.SqliteDatabase.Complainerid;
import static com.example.app.Db.SqliteDatabase.Complaint;
import static com.example.app.Db.SqliteDatabase.Dt;
import static com.example.app.Db.SqliteDatabase.Reply;
import static com.example.app.Db.SqliteDatabase.Status;
import static com.example.app.Db.SqliteDatabase.Table_Complaint;
import static com.example.app.Db.SqliteDatabase.Type;
import static com.example.app.Db.SqliteDatabase.cid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app.Db.SqliteDatabase;
import com.example.app.Model.Complain;

import java.util.ArrayList;
import java.util.List;

public class ComplaintController {
    public SqliteDatabase sqliteDatabase;

    public ComplaintController(Context context) {
        this.sqliteDatabase = new SqliteDatabase(context);
    }

    public void addComplaint(Complain farmer) {
        ContentValues values = new ContentValues();
        values.put(Type, farmer.getType());
        values.put(Cname, farmer.getName());
        values.put(Complaint, farmer.getComplain());
        values.put(Complainerid, farmer.getComplainerid());
        values.put(Dt, farmer.getDt());
        values.put(Reply, farmer.getReply());
        values.put(Status, farmer.getStatus());
        SQLiteDatabase db = this.sqliteDatabase.writableDatabase();
        db.insert(Table_Complaint, null, values);
    }

    public ArrayList<Complain> getComplaint(String fid, String type) {

        List<Complain> discountList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase;
        Cursor cursor = null;
        sqLiteDatabase = sqliteDatabase.readableDatabase();
        String selection = null;
        String[] selectionArgs = null;

        try {
//            String selection = "fid = ?  ";
//            // Define the selection arguments
//            String[] selectionArgs = {fid};

            selection = "Type LIKE '%" + type + "%' And complainerid  LIKE '%" + fid + "%' ";
            // Define the selection arguments
            selectionArgs = new String[]{"%" + type + "%", "%" + fid + "%"};

            cursor = sqLiteDatabase.query(Table_Complaint, null,
                    selection, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext())
                    discountList.add(getCompalinFromCursor(cursor));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {

        }
        return (ArrayList<Complain>) discountList;

    }

    private Complain getCompalinFromCursor(Cursor cursor) {
        Complain discount = new Complain();
        discount.setCid(String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow(cid))));
        discount.setType(String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(Type))));
        discount.setName(cursor.getString(cursor.getColumnIndexOrThrow(Cname)));
        discount.setComplain(cursor.getString(cursor.getColumnIndexOrThrow(Complaint)));
        discount.setDt(cursor.getString(cursor.getColumnIndexOrThrow(Dt)));
        discount.setReply(cursor.getString(cursor.getColumnIndexOrThrow(Reply)));
        discount.setComplainerid(cursor.getString(cursor.getColumnIndexOrThrow(Complainerid)));
        discount.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(Status)));
        return discount;
    }

    public ArrayList<Complain> getallComplaint() {

        List<Complain> discountList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase;
        Cursor cursor = null;
        sqLiteDatabase = sqliteDatabase.readableDatabase();
        String selection = null;
        String[] selectionArgs = null;

        try {
            cursor = sqLiteDatabase.query(Table_Complaint, null,
                    selection, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext())
                    discountList.add(getCompalinFromCursor(cursor));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {

        }
        return (ArrayList<Complain>) discountList;
    }

    public void update(Complain complain) {
        ContentValues values = new ContentValues();
        values.put(cid, complain.getCid());
        values.put(Reply, complain.getReply());
        SQLiteDatabase db = this.sqliteDatabase.writableDatabase();
        db.update(Table_Complaint, values, cid + " = ?", new String[]{String.valueOf(complain.getCid())});

    }

}
