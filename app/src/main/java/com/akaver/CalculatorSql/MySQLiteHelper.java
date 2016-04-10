package com.akaver.CalculatorSql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by akaver on 03/04/16.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "calculator.db";
    private static final int DATABASE_VERSION = 1;

    // Statistics table
    public static final String TABLE_STATISTICS = "statistics";
    public static final String STATISTICS_COLUMN_ID = "_id";
    public static final String STATISTICS_COLUMN_DAYSTAMP = "daystamp";
    public static final String STATISTICS_COLUMN_OPERAND_ID = "operandId";
    public static final String STATISTICS_COLUMN_DAYCOUNTER = "dayCounter";
    public static final String[] STATISTICS_ALL_COLUMNS = {
            STATISTICS_COLUMN_ID,
            STATISTICS_COLUMN_DAYSTAMP,
            STATISTICS_COLUMN_OPERAND_ID,
            STATISTICS_COLUMN_DAYCOUNTER
    };

    // Operation table
    public static final String TABLE_OPERATION = "operations";
    public static final String OPERATION_COLUMN_ID = "_id";
    public static final String OPERATION_OPERATION_TYPE_ID = "operationTypeId";
    public static final String OPERATION_COLUMN_NUM_1 = "num1";
    public static final String OPERATION_COLUMN_NUM_2 = "num2";
    public static final String OPERATION_COLUMN_RES = "res";
    public static final String OPERATION_COLUMN_TIMESTAMP = "timeStamp";
    public static final String[] OPERATION_ALL_COLUMNS = {
            OPERATION_COLUMN_ID,
            OPERATION_OPERATION_TYPE_ID,
            OPERATION_COLUMN_NUM_1,
            OPERATION_COLUMN_NUM_2,
            OPERATION_COLUMN_RES,
            OPERATION_COLUMN_TIMESTAMP
    };

    // Operation types table
    public static final String TABLE_OPERATION_TYPE = "operationTypes";
    public static final String OPERATION_TYPE_COLUMN_ID = "_id";
    public static final String OPERATION_TYPE_COLUMN_OPERATION = "operation";
    public static final String OPERATION_TYPE_COLUMN_LIFETIME_COUNTER = "lifetimeCounter";
    public static final String[] OPERATION_TYPE_ALL_COLUMNS = {
            OPERATION_TYPE_COLUMN_ID,
            OPERATION_TYPE_COLUMN_OPERATION,
            OPERATION_TYPE_COLUMN_LIFETIME_COUNTER
    };

    // table statistics create
    private static final String TABLE_STATISTICS_CREATE =
            "create table " + TABLE_STATISTICS + "("
                    + STATISTICS_COLUMN_ID + " integer primary key autoincrement, "
                    + STATISTICS_COLUMN_DAYSTAMP + " text not null, "
                    + STATISTICS_COLUMN_OPERAND_ID + " integer not null, "
                    + STATISTICS_COLUMN_DAYCOUNTER + " integer not null);";

    // table operation create
    private static final String TABLE_OPERATION_CREATE =
            "create table " + TABLE_OPERATION + "("
                    + OPERATION_COLUMN_ID + " integer primary key autoincrement, "
                    + OPERATION_OPERATION_TYPE_ID + " integer not null, "
                    + OPERATION_COLUMN_NUM_1 + " real not null, "
                    + OPERATION_COLUMN_NUM_2 + " real not null, "
                    + OPERATION_COLUMN_RES + " real not null, "
                    + OPERATION_COLUMN_TIMESTAMP + " text not null);";

    // table operation types
    private static final String TABLE_OPERATION_TYPES_CREATE =
            "create table " + TABLE_OPERATION_TYPE + "("
                    + STATISTICS_COLUMN_ID + " integer primary key autoincrement, "
                    + OPERATION_TYPE_COLUMN_OPERATION + " text not null, "
                    + OPERATION_TYPE_COLUMN_LIFETIME_COUNTER + " integer not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void dropCreateDatabase(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTICS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATION_TYPE);

        db.execSQL(TABLE_STATISTICS_CREATE);
        db.execSQL(TABLE_OPERATION_CREATE);
        db.execSQL(TABLE_OPERATION_TYPES_CREATE);
    }

    public void resetStatistics(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTICS);
        db.execSQL(TABLE_STATISTICS_CREATE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_STATISTICS_CREATE);
        db.execSQL(TABLE_OPERATION_CREATE);
        db.execSQL(TABLE_OPERATION_TYPES_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTICS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATION_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATION);
        onCreate(db);
    }



}
