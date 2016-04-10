package com.akaver.CalculatorSql;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by akaver on 03/04/16.
 */
// Step over cursor (SQLite result set for example), create subview for every item
public class OperationAdapter extends CursorAdapter{

    private final LayoutInflater layoutInflater;
    private UOW uow;
    private ViewGroup parentViewGroup;
    int counter = 0;

    public OperationAdapter(Context context, Cursor c, UOW uow) {
        super(context, c, 0);
        layoutInflater = LayoutInflater.from(context);
        this.uow = uow;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final View view=layoutInflater.inflate(R.layout.operation_content,parent,false);
        parentViewGroup = parent;
        return view;
    }


    // this can be called several times by the system!!!
    // first pass - initial draw, get measurements
    // second pass - final draw
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        //TextView textViewName =(TextView) view.findViewById(R.id.name);

        Operation operation = uow.operationRepo.cursorToEntity(cursor);
        //textViewName.setText(operation.getOperation());
        displayOperationView(view, context, operation);
    }


    private void displayOperationView(View view, Context context, Operation operation) {
        // Get the operations list
        RelativeLayout operationContent = (RelativeLayout) view.findViewById(R.id.operationContent);

        // if this gets called multiple times, first clean all up
        // otherwise you will add same childs several times
        operationContent.removeAllViews();

        for (Operation op : uow.operationRepo.getOperations(operation.getId())) {
            counter += 1;
            // load the xml structure of your row
            View child = layoutInflater.inflate(R.layout.operation_content,parentViewGroup,false);

            TextView firstNumber =(TextView) child.findViewById(R.id.firstNumber);
            TextView operationType =(TextView) child.findViewById(R.id.operationType);
            TextView secondNumber =(TextView) child.findViewById(R.id.secondNumber);
            TextView operationSolution =(TextView) child.findViewById(R.id.operationSolution);
            TextView operationDate =(TextView) child.findViewById(R.id.operationDate);

            firstNumber.setText(op.getNumber1());
            operationType.setText(uow.operationTypeRepo.getById(operation.getOperationId()).getOperation());
            secondNumber.setText(op.getNumber2());
            operationSolution.setText(op.getSolution());
            operationDate.setText(op.getDate());

            Log.d("Counter", String.valueOf(counter));

            operationContent.addView(child);
        }



    }
}
