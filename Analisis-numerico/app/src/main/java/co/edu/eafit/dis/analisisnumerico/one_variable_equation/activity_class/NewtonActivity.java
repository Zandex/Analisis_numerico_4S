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

import co.edu.eafit.dis.analisisnumerico.one_variable_equation.method_class.NewtonMethod;
import co.edu.eafit.dis.analisisnumerico.R;
import co.edu.eafit.dis.analisisnumerico.utility_class.DataUtil;

public class NewtonActivity extends AppCompatActivity {

    private EditText expText, exp2Text, iterText, tolText, iniValText, resultText;

    private TextView nVal, xnVal, yVal, dyVal, errVal;
    private TableLayout table;
    private TableRow tableRow,tableRow1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newton);
        expText = (EditText)findViewById(R.id.expText);
        exp2Text = (EditText)findViewById(R.id.exp2Text);
        iterText = (EditText)findViewById(R.id.iterText);
        tolText = (EditText)findViewById(R.id.tolText);
        iniValText = (EditText)findViewById(R.id.iniValText);
        resultText = (EditText)findViewById(R.id.resultText);
        table = (TableLayout) findViewById(R.id.table);
        tableRow1 = (TableRow) findViewById(R.id.tableRow1);
        setDataHashMap();
    }

    public void onCalculateClick(View view){
        try {
            String[] data = new String[5];
            data[0] = expText.getText().toString();
            data[1] = exp2Text.getText().toString();
            data[2] = iterText.getText().toString();
            data[3] = tolText.getText().toString();
            data[4] = iniValText.getText().toString();

            table.removeAllViews();
            table.addView(tableRow1);
            NewtonMethod.tableArray.clear();
            getDataHashMap();

            resultText.setText(NewtonMethod.newton(data[0], data[1], Integer.parseInt(data[2]),
                    new BigDecimal(data[3]), new BigDecimal(data[4])));

            createTableWithData();
        }catch (Exception e){
            Toast.makeText(this,R.string.ExceptionE,Toast.LENGTH_LONG).show();
        }
    }



    public void createTableWithData(){
        try {
            for (int i = 0; i < NewtonMethod.tableArray.size(); i++) {
                tableRow = new TableRow(this);

                nVal = new TextView(this);
                xnVal = new TextView(this);
                yVal = new TextView(this);
                dyVal = new TextView(this);
                errVal = new TextView(this);

                nVal.setPadding(5,5,5,5);
                xnVal.setPadding(5,5,5,5);
                yVal.setPadding(5,5,5,5);
                dyVal.setPadding(5,5,5,5);
                errVal.setPadding(5,5,5,5);


                nVal.setGravity(Gravity.START);
                xnVal.setGravity(Gravity.START);
                yVal.setGravity(Gravity.START);
                dyVal.setGravity(Gravity.START);
                errVal.setGravity(Gravity.START);

                nVal.setTextSize(15);
                xnVal.setTextSize(15);
                yVal.setTextSize(15);
                dyVal.setTextSize(15);
                errVal.setTextSize(15);

                nVal.setText(NewtonMethod.tableArray.get(i).get(0));
                xnVal.setText(NewtonMethod.tableArray.get(i).get(1));
                yVal.setText(NewtonMethod.tableArray.get(i).get(2));
                dyVal.setText(NewtonMethod.tableArray.get(i).get(3));
                errVal.setText(NewtonMethod.tableArray.get(i).get(4));
                tableRow.addView(nVal);
                tableRow.addView(xnVal);
                tableRow.addView(yVal);
                tableRow.addView(dyVal);
                tableRow.addView(errVal);
                table.addView(tableRow);
            }
        }catch(Exception e){
            Toast.makeText(this,R.string.ExceptionL,Toast.LENGTH_LONG).show();
        }

    }

    public void onHelpClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.newtonHelp)
                .setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create().show();
    }

    public void setDataHashMap(){
        expText.setText(DataUtil.dataMap.get("Function_fx"));
        exp2Text.setText(DataUtil.dataMap.get("First_Derivative"));
        iterText.setText(DataUtil.dataMap.get("Iterations"));
        tolText.setText(DataUtil.dataMap.get("Tolerance"));
        iniValText.setText(DataUtil.dataMap.get("Initial_Value"));
    }

    public void getDataHashMap(){
        DataUtil.dataMap.put("Function_fx",expText.getText().toString());
        DataUtil.dataMap.put("First_Derivative",exp2Text.getText().toString());
        DataUtil.dataMap.put("Iterations",iterText.getText().toString());
        DataUtil.dataMap.put("Tolerance",tolText.getText().toString());
        DataUtil.dataMap.put("Initial_Value",iniValText.getText().toString());
    }
}
