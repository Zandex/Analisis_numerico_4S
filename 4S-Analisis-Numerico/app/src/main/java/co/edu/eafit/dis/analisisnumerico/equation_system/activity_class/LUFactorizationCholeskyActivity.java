package co.edu.eafit.dis.analisisnumerico.equation_system.activity_class;

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
import co.edu.eafit.dis.analisisnumerico.equation_system.method_class.LUFactorizationCholesky;
import co.edu.eafit.dis.analisisnumerico.equation_system.method_class.PartialPivoting;
import co.edu.eafit.dis.analisisnumerico.equation_system.method_class.ScaledPivoting;
import co.edu.eafit.dis.analisisnumerico.equation_system.method_class.TotalPivoting;
import co.edu.eafit.dis.analisisnumerico.utility_class.DataUtil;

public class LUFactorizationCholeskyActivity extends AppCompatActivity {

    private EditText nText;
    private TableLayout tableVectorB,tableMatrixA,tableL,tableU;
    private TableRow tableRow,tableRow1L,tableRow1U;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lufactorization_cholesky);
        nText = (EditText)findViewById(R.id.nText);
        tableVectorB = (TableLayout) findViewById(R.id.tableVectorB);
        tableMatrixA = (TableLayout) findViewById(R.id.tableMatrixA);
        tableRow1L = (TableRow) findViewById(R.id.tableRow1L);
        tableRow1U = (TableRow) findViewById(R.id.tableRow1U);
        tableL = (TableLayout) findViewById(R.id.tableL);
        tableU = (TableLayout) findViewById(R.id.tableU);
        try {
            if (DataUtil.matrixA != null && DataUtil.matrixA.length != 0) {
                int n = DataUtil.matrixA.length;
                createMatrix_Vector(n);
                setMatrix_VectorData();
            }
        }catch (Exception e){

        }
    }


    public void onCreateClick(View view){
        try {
            String nValue = nText.getText().toString();
            int n= Integer.parseInt(nValue);
            createMatrix_Vector(n);
        }catch (Exception e){
            Toast.makeText(LUFactorizationCholeskyActivity.this,"Error, please check " +
                    "the data",Toast.LENGTH_LONG).show();
        }
    }


    public void onCalculateClick(View view){
        try {
            getMatrixData();
            LUFactorizationCholesky.cholesky(DataUtil.matrixA,DataUtil.matrixA.length);
            createViewResult();
        }catch (Exception e){
            Toast.makeText(LUFactorizationCholeskyActivity.this,"Error, please check " +
                    "the data",Toast.LENGTH_LONG).show();
        }
    }

    public void createViewResult() {
        tableL.removeAllViews();
        tableU.removeAllViews();
        tableL.addView(tableRow1L);
        tableU.addView(tableRow1U);
        for(int i=0;i<LUFactorizationCholesky.tableArrayL.size()-1;i++){
            tableRow = new TableRow(this);
            for(int j=0;j<LUFactorizationCholesky.tableArrayL.get(j).size();j++){
                textView = new TextView(this);
                textView.setPadding(5,5,8,5);
                textView.setGravity(Gravity.START);
                textView.setTextSize(15);
                textView.setText(LUFactorizationCholesky.tableArrayL.get(i).get(j));
                tableRow.addView(textView);
            }
            tableL.addView(tableRow);
        }
        for(int i=0;i<LUFactorizationCholesky.tableArrayU.size()-1;i++){
            tableRow = new TableRow(this);
            for(int j=0;j<LUFactorizationCholesky.tableArrayU.get(j).size();j++){
                textView = new TextView(this);
                textView.setPadding(5,5,8,5);
                textView.setGravity(Gravity.START);
                textView.setTextSize(15);
                textView.setText(LUFactorizationCholesky.tableArrayU.get(i).get(j));
                tableRow.addView(textView);
            }
            tableU.addView(tableRow);
        }
    }



    public void createMatrix_Vector(int n)throws Exception{
        tableMatrixA.removeAllViews();

        tableRow = new TableRow(this);
        for(int j=0;j<n;j++){
            textView= new TextView(this);
            textView.setText("x"+(j+1));
            textView.setPadding(5,3,5,5);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(15);
            tableRow.addView(textView);
        }
        tableMatrixA.addView(tableRow);
        tableVectorB.removeAllViews();

        tableRow = new TableRow(this);
        textView= new TextView(this);
        textView.setText("d");
        textView.setPadding(5,3,5,5);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(15);
        tableRow.addView(textView);
        tableVectorB.addView(tableRow);

        TableRow.LayoutParams editTParams=new TableRow.LayoutParams
                (100,140);
        editTParams.setMargins(20, 20, 20, 20);
        int idEditText =0;
        for(int i=0;i<n;i++){
            tableRow = new TableRow(this);
            for(int j=0;j<n;j++){
                editText= new EditText(this);
                editText.setId(idEditText);
                editText.setTextSize(15);
                editText.setLayoutParams(editTParams);
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                editText.setHint("0");
                tableRow.addView(editText);
                idEditText++;
            }
            tableMatrixA.addView(tableRow);
        }
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
            tableVectorB.addView(tableRow);
        }
    }


    public void setMatrix_VectorData()throws Exception{
        if(DataUtil.matrixA.length!=0){
            int n= DataUtil.matrixA.length;
            nText.setText(""+n);
            EditText temp;
            int idEditText = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    temp = (EditText) findViewById(idEditText);
                    temp.setText(DataUtil.matrixA[i][j].toString());
                    idEditText++;
                }
            }
            for (int i = 0; i < n; i++) {
                temp = (EditText) findViewById(idEditText);
                temp.setText(DataUtil.vectorB[i].toString());
                idEditText++;
            }
        }
    }


    public void getMatrixData()throws Exception{
        String nValue = nText.getText().toString();
        int n = Integer.parseInt(nValue);
        DataUtil.matrixA = new BigDecimal[n][n];
        DataUtil.vectorB = new BigDecimal[n];
        EditText temp;
        int idEditText = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp = (EditText) findViewById(idEditText);
                DataUtil.matrixA[i][j] = temp.getText().toString().length()==0 ? BigDecimal.ZERO: new BigDecimal(temp.getText().toString());
                idEditText++;
            }
        }
        for (int i = 0; i < n; i++) {
            temp = (EditText) findViewById(idEditText);
            DataUtil.vectorB[i] = temp.getText().toString().length()==0 ? BigDecimal.ZERO: new BigDecimal(temp.getText().toString());
            idEditText++;
        }
    }



    public void onHelpClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.LUFactorizationGeneralHelp)
                .setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create().show();
    }

    public void onPivotingClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.pivotingMethods)
                .setMessage(R.string.pivotHelp)
                .setNeutralButton(R.string.partial, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            partialPivoting();
                        } catch (Exception e) {
                            Toast.makeText(LUFactorizationCholeskyActivity.this,
                                    R.string.ExceptionE,Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton(R.string.scaled, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            scaledPivoting();
                        } catch (Exception e) {
                            Toast.makeText(LUFactorizationCholeskyActivity.this,
                                    R.string.ExceptionE,Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setPositiveButton(R.string.total, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            totalPivoting();
                        } catch (Exception e) {
                            Toast.makeText(LUFactorizationCholeskyActivity.this,
                                    R.string.ExceptionE,Toast.LENGTH_LONG).show();
                        }
                    }
                });

        builder.create().show();
    }

    public void partialPivoting() throws Exception{
        try {
            getMatrixData();
            BigDecimal [][]result = PartialPivoting.partialPivoting(DataUtil.matrixA,DataUtil.matrixA.length);
            DataUtil.matrixA = result;
            setMatrix_VectorData();
        }catch (Exception e){
            Toast.makeText(LUFactorizationCholeskyActivity.this,
                    R.string.ExceptionE,Toast.LENGTH_LONG).show();
        }
    }

    public void scaledPivoting() throws Exception{
        try {
            getMatrixData();
            BigDecimal [][]result = ScaledPivoting.scaledPivoting(DataUtil.matrixA,
                    DataUtil.matrixA.length,DataUtil.matrixA.length-1);
            DataUtil.matrixA = result;
            setMatrix_VectorData();
        }catch (Exception e){
            Toast.makeText(LUFactorizationCholeskyActivity.this,
                    R.string.ExceptionE,Toast.LENGTH_LONG).show();
        }
    }

    public void totalPivoting() throws Exception{
        try {
            getMatrixData();
            BigDecimal[][] result= TotalPivoting.totalPivoting(DataUtil.matrixA,
                    DataUtil.matrixA.length);
            DataUtil.matrixA = result;
            setMatrix_VectorData();
        }catch (Exception e){
            Toast.makeText(LUFactorizationCholeskyActivity.this,
                    R.string.ExceptionE,Toast.LENGTH_LONG).show();
        }
    }

}

