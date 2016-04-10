package com.akaver.CalculatorSql;

/**
 * Created by akaver on 03/04/16.
 */
public class OperationType implements IEntity {

    private long id;
    private String operation;
    private int lifetimeCounter;

    public OperationType() { }

    public OperationType(String operation){
        setOperation(operation);
        setLifetimeCounter(0);
    }

    // ID get/set
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    // Operation get/set
    public String getOperation() {
        return operation;
    }
    public void setOperation(String operation) {
        this.operation = operation.trim();
    }

    // Lifetime counter get/set
    public int getLifetimeCounter() {
        return this.lifetimeCounter;
    }
    public void setLifetimeCounter(int lifetimeCounter) {
        this.lifetimeCounter = lifetimeCounter;
    }

    @Override
    public String toString() {
        return this.operation + " " + this.lifetimeCounter;
    }
}
