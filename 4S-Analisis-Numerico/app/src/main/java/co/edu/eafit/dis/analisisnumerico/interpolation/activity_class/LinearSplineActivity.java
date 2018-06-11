package co.edu.eafit.dis.analisisnumerico.interpolation.activity_class;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import co.edu.eafit.dis.analisisnumerico.R;
import co.edu.eafit.dis.analisisnumerico.interpolation.method_class.LinearSpline;
import co.edu.eafit.dis.analisisnumerico.utility_class.DataUtil;

public class LinearSplineActivity extends AppCompatActivity {

    private EditText nText,yText;
    private TableLayout tableFuncF,tableVectorX,table;
    private TableRow tableRow,tableRow1;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_spline);
        nText = (EditText)findViewById(R.id.nText);
        yText = (EditText)findViewById(R.id.yText);
        tableFuncF = (TableLayout) findViewById(R.id.tableVectorF);
        tableVectorX = (TableLayout) findViewById(R.id.tableVectorX);
        table = (TableLayout) findViewById(R.id.table);
        tableRow1 = (TableRow) findViewById(R.id.tableRow1);
        try {
            if (DataUtil.funcFInterpolation != null && DataUtil.vectorXInterpolation.length != 0) {
                int n = DataUtil.vectorXInterpolation.length;
                createVectors(n);
                setVectorsData();
            }
        }catch (Exception e){

        }
    }

    public void onCreateClick(View view){
        try {
            String nValue = nText.getText().toString();
            int n= Integer.parseInt(nValue);
            createVectors(n);
        }catch (Exception e){
            Toast.makeText(LinearSplineActivity.this,"Error, please check " +
                    "the data",Toast.LENGTH_LONG).show();
        }
    }


    public void onCalculateClick(View view){
        try {
            getVectorsData();
            BigDecimal result = LinearSpline.linearSpline(DataUtil.funcFInterpolation,
                    DataUtil.vectorXInterpolation,
                    new BigDecimal(DataUtil.interpolation.get("Y_Value")),
                    DataUtil.vectorXInterpolation.length);
            createViewResult(result);

        }catch (Exception e){
            Toast.makeText(LinearSplineActivity.this,"Error, please check " +
                    "the data",Toast.LENGTH_LONG).show();
        }
    }


    public void createViewResult(BigDecimal result){
        table.removeAllViews();
        textView = new TextView(this);
        textView.setPadding(5,5,8,5);
        textView.setGravity(Gravity.START);
        textView.setTextSize(15);
        textView.setText(result.toString());
        tableRow1.addView(textView);
        table.addView(tableRow1);
        for(int i = 0; i< LinearSpline.tableArray.size()-1; i++){
            tableRow = new TableRow(this);
            for(int j=0;j<LinearSpline.tableArray.get(j).size();j++){
                textView = new TextView(this);
                textView.setPadding(5,5,8,5);
                textView.setGravity(Gravity.START);
                textView.setTextSize(15);
                textView.setText(LinearSpline.tableArray.get(i).get(j));
                tableRow.addView(textView);
            }
            table.addView(tableRow);
        }
    }



    public void createVectors(int n)throws Exception{
        tableFuncF.removeAllViews();
        tableVectorX.removeAllViews();

        TableRow.LayoutParams editTParams=
                new TableRow.LayoutParams
                        (180,70);
        editTParams.setMargins(10, 10, 10, 10);
        int idEditText =0;
        tableRow = new TableRow(this);
        editText= new EditText(this);
        editText.setId(idEditText);
        editText.setTextSize(15);
        editText.setLayoutParams(editTParams);
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        editText.setHint("f(x)");
        tableRow.addView(editText);
        idEditText++;
        tableFuncF.addView(tableRow);

        for(int i=0;i<n;i++){
            tableRow = new TableRow(this);
            for(int j=0;j<1;j++){
                editText= new EditText(this);
                editText.setId(idEditText);
                editText.setTextSize(15);
                editText.setLayoutParams(editTParams);
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                editText.setHint("0");
                tableRow.addView(editText);
                idEditText++;
            }
            tableVectorX.addView(tableRow);
        }
    }



    public void setVectorsData()throws Exception{
        if(DataUtil.vectorXInterpolation.length!=0){
            int n= DataUtil.vectorXInterpolation.length;
            nText.setText(""+n);
            yText.setText(DataUtil.interpolation.get("Y_Value"));
            EditText temp;
            int idEditText = 0;
            temp = (EditText) findViewById(idEditText);
            temp.setText(DataUtil.funcFInterpolation.toString());
            idEditText++;

            for (int i = 0; i < n; i++) {
                temp = (EditText) findViewById(idEditText);
                temp.setText(DataUtil.vectorXInterpolation[i].toString());
                idEditText++;
            }
        }
    }


    public void getVectorsData()throws Exception{
        String nValue = nText.getText().toString();
        int n = Integer.parseInt(nValue);
        DataUtil.interpolation.put("Y_Value",yText.getText().toString());
        DataUtil.funcFInterpolation = new String();
        DataUtil.vectorXInterpolation = new BigDecimal[n];
        EditText temp;
        int idEditText = 0;
        temp = (EditText) findViewById(idEditText);
        DataUtil.funcFInterpolation =temp.getText().toString().length()==0 ? "0": temp.getText().toString();
        idEditText++;

        for (int i = 0; i < n; i++) {
            temp = (EditText) findViewById(idEditText);
            DataUtil.vectorXInterpolation[i] =
                    temp.getText().toString().length()==0 ? BigDecimal.ZERO: new BigDecimal(temp.getText().toString());
            idEditText++;
        }
    }




    public void onHelpClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.linear_Help)
                .setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create().show();
    }

}
