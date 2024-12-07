package com.example.databaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static SQLiteHandler sqLiteHandler;
    private static final String DATABASE_NAME = "peopleDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "People";
    private static final String COUNTER = "Counter";

    private static final String ID_FIELD = "Id";
    private static final String NAME_FIELD = "Name";
    private static final String LAST_NAME_FIELD = "Last_Name";
    private static final String AGE_FIELD = "Age";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteHandler instanceOfDatabase(Context context) {
        if(sqLiteHandler == null) {
            sqLiteHandler = new SQLiteHandler(context);
        }
        return sqLiteHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" INT, ")
                .append(NAME_FIELD)
                .append(" TEXT, ")
                .append(LAST_NAME_FIELD)
                .append(" TEXT, ")
                .append(AGE_FIELD)
                .append(" INT)");
        sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addPeopleToDB(Person person){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, person.getId());
        contentValues.put(NAME_FIELD, person.getName());
        contentValues.put(LAST_NAME_FIELD, person.getLast_name());
        contentValues.put(AGE_FIELD, person.getAge());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void populatePeopleListArray()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null))
        {
            if(result.getCount() != 0)
            {
                while (result.moveToNext())
                {
                    int id = result.getInt(1);
                    String name = result.getString(2);
                    String last_name = result.getString(3);
                    int age = Integer.parseInt(result.getString(4));
                    Person person = new Person(id,name,last_name,age);
                    Person.peopleArrayList.add(person);
                }
            }
        }
    }

    public void updatePersonInDB(Person person)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, person.getId());
        contentValues.put(NAME_FIELD, person.getName());
        contentValues.put(LAST_NAME_FIELD, person.getLast_name());
        contentValues.put(AGE_FIELD, person.getAge());

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =? ", new String[]{String.valueOf(person.getId())});
    }
}
