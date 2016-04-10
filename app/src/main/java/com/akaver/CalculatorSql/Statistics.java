package com.akaver.CalculatorSql;

/**
 * Created by akaver on 03/04/16.
 */
public class Statistics implements IEntity {

    private long id;
    private long operandId;
    private String daystamp;
    private int dayCounter;

    public Statistics() { }

    public Statistics(int operandId, String daystamp, int dayCounter){
        setOperandId(operandId);
        setDaystamp(daystamp);
        setDayCounter(dayCounter);
    }

    // ID get/set
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    // Operand ID get/set
    public long getOperandId() {
        return this.operandId;
    }
    public void setOperandId(long operandId) { this.operandId = operandId; }

    // Daystamp get/set
    public String getDaystamp() {
        return this.daystamp;
    }
    public void setDaystamp(String daystamp) {
        this.daystamp = daystamp;
    }

    // Daycounter get/set
    public int getDayCounter() {
        return this.dayCounter;
    }
    public void setDayCounter(int dayCounter) {
        this.dayCounter = dayCounter;
    }

    @Override
    public String toString() {
        return daystamp + " " + operandId + " " + dayCounter;
    }
}
