package co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import co.edu.eafit.dis.analisisnumerico.R;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.method_class.FixedPointMethod;
import co.edu.eafit.dis.analisisnumerico.utility_class.DataUtil;

public class FixedPointActivity extends AppCompatActivity {

    private EditText expFText,expGText,iterExp,tolText,iniValText,resultText;

    private TextView nVal, xnVal, ynVal, errVal;
    private TableLayout table;
    private TableRow tableRow,tableRow1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_point);

        expFText = (EditText)findViewById(R.id.expFText);
        expGText = (EditText)findViewById(R.id.expGText);
        iterExp = (EditText)findViewById(R.id.iterExp);
        tolText = (EditText)findViewById(R.id.tolText);
        iniValText = (EditText)findViewById(R.id.iniValText);
        resultText = (EditText)findViewById(R.id.resultText);
        table = (TableLayout) findViewById(R.id.table);
        tableRow1 = (TableRow) findViewById(R.id.tableRow1);
        setDataHashMap();
    }

    public void onCalculateClick(View view){
        try{

            String[] data = new String[5];
            data[0] = expFText.getText().toString();
            data[1] = expGText.getText().toString();
            data[2] = iterExp.getText().toString();
            data[3] = tolText.getText().toString();
            data[4] = iniValText.getText().toString();

            //clean table and Arraylist
            table.removeAllViews();
            table.addView(tableRow1);
            FixedPointMethod.tableArray.clear();
            getDataHashMap();

            resultText.setText(FixedPointMethod.fixedPoint(data[0], data[1], Integer.parseInt(data[2]),
                    new BigDecimal(data[3]), new BigDecimal(data[4])));

            createTableWithData();
        }catch (Exception e){
            Toast.makeText(this,R.string.ExceptionE,Toast.LENGTH_LONG).show();
        }
    }

    public void createTableWithData(){
        try {
            for (int i = 0; i < FixedPointMethod.tableArray.size(); i++) {
                tableRow = new TableRow(this);

                nVal = new TextView(this);
                xnVal = new TextView(this);
                ynVal = new TextView(this);
                errVal = new TextView(this);

                nVal.setPadding(5,5,5,5);
                xnVal.setPadding(5,5,5,5);
                ynVal.setPadding(5,5,5,5);
                errVal.setPadding(5,5,5,5);


                nVal.setGravity(Gravity.START);
                xnVal.setGravity(Gravity.START);
                ynVal.setGravity(Gravity.START);
                errVal.setGravity(Gravity.START);

                nVal.setTextSize(15);
                xnVal.setTextSize(15);
                ynVal.setTextSize(15);
                errVal.setTextSize(15);

                nVal.setText(FixedPointMethod.tableArray.get(i).get(0));
                xnVal.setText(FixedPointMethod.tableArray.get(i).get(1));
                ynVal.setText(FixedPointMethod.tableArray.get(i).get(2));
                errVal.setText(FixedPointMethod.tableArray.get(i).get(3));
                tableRow.addView(nVal);
                tableRow.addView(xnVal);
                tableRow.addView(ynVal);
                tableRow.addView(errVal);
                table.addView(tableRow);
            }
        }catch(Exception e){
            Toast.makeText(this,R.string.ExceptionL,Toast.LENGTH_LONG).show();
        }

    }

    public void onHelpClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.fixedPointHelp)
                .setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create().show();
    }

    public void setDataHashMap(){
        expFText.setText(DataUtil.dataMap.get("Function_fx"));
        expGText.setText(DataUtil.dataMap.get("Function_gx"));
        iterExp.setText(DataUtil.dataMap.get("Iterations"));
        tolText.setText(DataUtil.dataMap.get("Tolerance"));
        iniValText.setText(DataUtil.dataMap.get("Initial_Value"));
    }

    public void getDataHashMap(){
        DataUtil.dataMap.put("Function_fx",expFText.getText().toString());
        DataUtil.dataMap.put("Function_gx",expGText.getText().toString());
        DataUtil.dataMap.put("Iterations",iterExp.getText().toString());
        DataUtil.dataMap.put("Tolerance",tolText.getText().toString());
        DataUtil.dataMap.put("Initial_Value",iniValText.getText().toString());
    }

}
