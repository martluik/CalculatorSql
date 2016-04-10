package com.akaver.CalculatorSql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by akaver on 03/04/16.
 */
public class UOW {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private final Context context;

    public OperationRepo operationRepo;
    public OperationTypeRepo operationTypeRepo;
    public StatisticsRepo statisticsRepo;

    public UOW(Context context){
        this.context = context;

        dbHelper = new MySQLiteHelper(context);
        database = dbHelper.getWritableDatabase();

        operationRepo = new OperationRepo(database, dbHelper.TABLE_OPERATION, dbHelper.OPERATION_ALL_COLUMNS);
        operationTypeRepo = new OperationTypeRepo(database, dbHelper.TABLE_OPERATION_TYPE, dbHelper.OPERATION_TYPE_ALL_COLUMNS);
        statisticsRepo = new StatisticsRepo(database, dbHelper.TABLE_STATISTICS, dbHelper.STATISTICS_ALL_COLUMNS);
    }

    public void DropCreateDatabase(){
        dbHelper.dropCreateDatabase(database);
    }

    public void resetStatistics() {
        dbHelper.resetStatistics(database);
    }

    public void DummyData() {
        operationTypeRepo.add(new OperationType("*"));
        operationTypeRepo.add(new OperationType("/"));
        operationTypeRepo.add(new OperationType("+"));
        operationTypeRepo.add(new OperationType("-"));

        operationRepo.add(new Operation(1, "5", "5", "25", "01/01/2016"));
        operationRepo.add(new Operation(2, "27", "3", "9", "01/01/2016"));
        operationRepo.add(new Operation(3, "47", "3", "50", "01/01/2016"));
        operationRepo.add(new Operation(4, "6", "6", "0", "01/01/2016"));

        statisticsRepo.add(new Statistics(1, "01/01/2016", 1));
        statisticsRepo.add(new Statistics(2, "01/01/2016", 1));
        statisticsRepo.add(new Statistics(3, "01/01/2016", 1));
        statisticsRepo.add(new Statistics(4, "01/01/2016", 1));
    }

}
