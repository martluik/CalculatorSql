package com.akaver.CalculatorSql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by akaver on 03/04/16.
 */
public class OperationTypeRepo extends Repo<OperationType> {

    public OperationTypeRepo(SQLiteDatabase database, String tableName, String[] allColumns) {
        super(database, tableName, allColumns);
    }

    @Override
    public OperationType cursorToEntity(Cursor cursor) {
        OperationType operationType = new OperationType();
        // ID
        operationType.setId(cursor.getLong(0));
        // Operation
        operationType.setOperation(cursor.getString(1));
        // Lifetime counter
        operationType.setLifetimeCounter(cursor.getInt(2));

        return operationType;
    }

    @Override
    public ContentValues entityToContentValues(OperationType operationType) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(MySQLiteHelper.OPERATION_TYPE_COLUMN_OPERATION, operationType.getOperation());
        contentValues.put(MySQLiteHelper.OPERATION_TYPE_COLUMN_LIFETIME_COUNTER, operationType.getLifetimeCounter());

        return contentValues;
    }


}
