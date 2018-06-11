package co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import co.edu.eafit.dis.analisisnumerico.R;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.method_class.SecantMethod;
import co.edu.eafit.dis.analisisnumerico.utility_class.DataUtil;

public class SecantActivity extends AppCompatActivity {

    private EditText expText, iterText, tolText, infText, supText, resultText;

    private TextView nVal, x0Val, y0Val, x1Val, y1Val, errVal;
    private TableLayout table;
    private TableRow tableRow,tableRow1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secant);
        expText = (EditText)findViewById(R.id.expText);
        iterText = (EditText)findViewById(R.id.iterText);
        tolText = (EditText)findViewById(R.id.tolText);
        infText = (EditText)findViewById(R.id.infText);
        supText = (EditText)findViewById(R.id.supText);
        resultText = (EditText)findViewById(R.id.resultText);
        table = (TableLayout) findViewById(R.id.table);
        tableRow1 = (TableRow) findViewById(R.id.tableRow1);
        setDataHashMap();
    }

    public void onCalculateClick(View view){
        try {
            String[] data = new String[5];
            data[0] = expText.getText().toString();
            data[1] = iterText.getText().toString();
            data[2] = tolText.getText().toString();
            data[3] = infText.getText().toString();
            data[4] = supText.getText().toString();

            //clean table and Arraylist
            table.removeAllViews();
            table.addView(tableRow1);
            SecantMethod.tableArray.clear();
            getDataHashMap();

            resultText.setText(SecantMethod.secant(data[0], Integer.parseInt(data[1]),
                    new BigDecimal(data[2]), new BigDecimal(data[3]),
                    new BigDecimal(data[4])));
            createTableWithData();
        }catch (Exception e){
            Toast.makeText(this,R.string.ExceptionE,Toast.LENGTH_LONG).show();
        }

    }

    public void createTableWithData(){
        try {
            for (int i = 0; i < SecantMethod.tableArray.size(); i++) {
                tableRow = new TableRow(this);

                nVal = new TextView(this);
                x0Val = new TextView(this);
                y0Val = new TextView(this);
                x1Val = new TextView(this);
                y1Val = new TextView(this);
                errVal = new TextView(this);

                nVal.setPadding(5,5,5,5);
                x0Val.setPadding(5,5,5,5);
                y0Val.setPadding(5,5,5,5);
                x1Val.setPadding(5,5,5,5);
                y1Val.setPadding(5,5,5,5);
                errVal.setPadding(5,5,5,5);


                nVal.setGravity(Gravity.START);
                x0Val.setGravity(Gravity.START);
                y0Val.setGravity(Gravity.START);
                x1Val.setGravity(Gravity.START);
                y1Val.setGravity(Gravity.START);
                errVal.setGravity(Gravity.START);

                nVal.setTextSize(15);
                x0Val.setTextSize(15);
                y0Val.setTextSize(15);
                x1Val.setTextSize(15);
                y1Val.setTextSize(15);
                errVal.setTextSize(15);

                nVal.setText(SecantMethod.tableArray.get(i).get(0));
                x0Val.setText(SecantMethod.tableArray.get(i).get(1));
                y0Val.setText(SecantMethod.tableArray.get(i).get(2));
                x1Val.setText(SecantMethod.tableArray.get(i).get(3));
                y1Val.setText(SecantMethod.tableArray.get(i).get(4));
                errVal.setText(SecantMethod.tableArray.get(i).get(5));
                tableRow.addView(nVal);
                tableRow.addView(x0Val);
                tableRow.addView(y0Val);
                tableRow.addView(x1Val);
                tableRow.addView(y1Val);
                tableRow.addView(errVal);
                table.addView(tableRow);
            }
        }catch(Exception e){
            Toast.makeText(this,R.string.ExceptionL,Toast.LENGTH_LONG).show();
        }

    }

    public void onHelpClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.secantHelp)
                .setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create().show();
    }

    public void setDataHashMap(){
        expText.setText(DataUtil.dataMap.get("Function_fx"));
        iterText.setText(DataUtil.dataMap.get("Iterations"));
        tolText.setText(DataUtil.dataMap.get("Tolerance"));
        infText.setText(DataUtil.dataMap.get("Inferior_Limit"));
        supText.setText(DataUtil.dataMap.get("Superior_Limit"));
    }

    public void getDataHashMap(){
        DataUtil.dataMap.put("Function_fx",expText.getText().toString());
        DataUtil.dataMap.put("Iterations",iterText.getText().toString());
        DataUtil.dataMap.put("Tolerance",tolText.getText().toString());
        DataUtil.dataMap.put("Inferior_Limit",infText.getText().toString());
        DataUtil.dataMap.put("Superior_Limit",supText.getText().toString());
    }
}
