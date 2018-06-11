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

import co.edu.eafit.dis.analisisnumerico.one_variable_equation.method_class.MultipleRootsMethod;
import co.edu.eafit.dis.analisisnumerico.R;
import co.edu.eafit.dis.analisisnumerico.utility_class.DataUtil;

public class MultipleRootsActivity extends AppCompatActivity {

    private EditText expText, exp2Text, exp3Text, iterText, tolText, iniValText,resultText;

    private TextView nVal, xnVal, yVal, dyVal, ddyVal, errVal;
    private TableLayout table;
    private TableRow tableRow,tableRow1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_roots);
        expText = (EditText)findViewById(R.id.expText);
        exp2Text = (EditText)findViewById(R.id.exp2Text);
        exp3Text = (EditText)findViewById(R.id.exp3Text);
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
            String[] data = new String[6];
            data[0] = expText.getText().toString();
            data[1] = exp2Text.getText().toString();
            data[2] = exp3Text.getText().toString();
            data[3] = iterText.getText().toString();
            data[4] = tolText.getText().toString();
            data[5] = iniValText.getText().toString();

            //clean table and Arraylist
            table.removeAllViews();
            table.addView(tableRow1);
            MultipleRootsMethod.tableArray.clear();
            getDataHashMap();

            MultipleRootsMethod.multipleRoots(data[0], data[1], data[2], Integer.parseInt(data[3]),
                    new BigDecimal(data[4]), new BigDecimal(data[5]));
            createTableWithData();
        }catch (Exception e){
            Toast.makeText(this,R.string.ExceptionE,Toast.LENGTH_LONG).show();
        }
    }

    public void createTableWithData(){
        try {
            for (int i = 0; i < MultipleRootsMethod.tableArray.size(); i++) {
                tableRow = new TableRow(this);

                nVal = new TextView(this);
                xnVal = new TextView(this);
                yVal = new TextView(this);
                dyVal = new TextView(this);
                ddyVal = new TextView(this);
                errVal = new TextView(this);

                nVal.setPadding(5,5,5,5);
                xnVal.setPadding(5,5,5,5);
                yVal.setPadding(5,5,5,5);
                dyVal.setPadding(5,5,5,5);
                ddyVal.setPadding(5,5,5,5);
                errVal.setPadding(5,5,5,5);


                nVal.setGravity(Gravity.START);
                xnVal.setGravity(Gravity.START);
                yVal.setGravity(Gravity.START);
                dyVal.setGravity(Gravity.START);
                ddyVal.setGravity(Gravity.START);
                errVal.setGravity(Gravity.START);

                nVal.setTextSize(15);
                xnVal.setTextSize(15);
                yVal.setTextSize(15);
                dyVal.setTextSize(15);
                ddyVal.setTextSize(15);
                errVal.setTextSize(15);

                nVal.setText(MultipleRootsMethod.tableArray.get(i).get(0));
                xnVal.setText(MultipleRootsMethod.tableArray.get(i).get(1));
                yVal.setText(MultipleRootsMethod.tableArray.get(i).get(2));
                dyVal.setText(MultipleRootsMethod.tableArray.get(i).get(3));
                ddyVal.setText(MultipleRootsMethod.tableArray.get(i).get(4));
                errVal.setText(MultipleRootsMethod.tableArray.get(i).get(5));
                tableRow.addView(nVal);
                tableRow.addView(xnVal);
                tableRow.addView(yVal);
                tableRow.addView(dyVal);
                tableRow.addView(ddyVal);
                tableRow.addView(errVal);
                table.addView(tableRow);
            }
        }catch(Exception e){
            Toast.makeText(this,R.string.ExceptionL,Toast.LENGTH_LONG).show();
        }

    }

    public void onHelpClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.multipleRootsHelp)
                .setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create().show();
    }

    public void setDataHashMap(){
        expText.setText(DataUtil.dataMap.get("Function_fx"));
        exp2Text.setText(DataUtil.dataMap.get("First_Derivative"));
        exp3Text.setText(DataUtil.dataMap.get("Second_Derivative"));
        iterText.setText(DataUtil.dataMap.get("Iterations"));
        tolText.setText(DataUtil.dataMap.get("Tolerance"));
        iniValText.setText(DataUtil.dataMap.get("Initial_Value"));
    }

    public void getDataHashMap(){
        DataUtil.dataMap.put("Function_fx",expText.getText().toString());
        DataUtil.dataMap.put("First_Derivative",exp2Text.getText().toString());
        DataUtil.dataMap.put("Second_Derivative",exp3Text.getText().toString());
        DataUtil.dataMap.put("Iterations",iterText.getText().toString());
        DataUtil.dataMap.put("Tolerance",tolText.getText().toString());
        DataUtil.dataMap.put("Initial_Value",iniValText.getText().toString());

    }
}
