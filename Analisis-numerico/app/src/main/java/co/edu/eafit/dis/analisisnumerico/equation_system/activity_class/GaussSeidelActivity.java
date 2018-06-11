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
import co.edu.eafit.dis.analisisnumerico.equation_system.method_class.GaussSeidel;
import co.edu.eafit.dis.analisisnumerico.equation_system.method_class.PartialPivoting;
import co.edu.eafit.dis.analisisnumerico.equation_system.method_class.ScaledPivoting;
import co.edu.eafit.dis.analisisnumerico.equation_system.method_class.TotalPivoting;
import co.edu.eafit.dis.analisisnumerico.utility_class.DataUtil;

public class GaussSeidelActivity extends AppCompatActivity {

    private EditText nText,tolText,iterText;
    private TableLayout tableVectorB,tableMatrixA,tableVectorX,table;
    private TableRow tableRow,tableRow1;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauss_seidel);
        nText = (EditText)findViewById(R.id.nText);
        tolText = (EditText)findViewById(R.id.tolText);
        iterText = (EditText)findViewById(R.id.iterText);
        tableVectorB = (TableLayout) findViewById(R.id.tableVectorB);
        tableMatrixA = (TableLayout) findViewById(R.id.tableMatrixA);
        tableVectorX = (TableLayout) findViewById(R.id.tableVectorX);
        table = (TableLayout) findViewById(R.id.table);
        tableRow1 = (TableRow) findViewById(R.id.tableRow1);

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
            Toast.makeText(GaussSeidelActivity.this,"Error, please check " +
                    "the data",Toast.LENGTH_LONG).show();
        }
    }


    public void onCalculateClick(View view){
        try {
            getMatrixData();
            GaussSeidel.gaussSeidel(DataUtil.matrixA,DataUtil.vectorX
                    ,DataUtil.vectorB,
                    Integer.parseInt(DataUtil.equationSystemDataMap.get("Iterations")),
                    new BigDecimal(DataUtil.equationSystemDataMap.get("Tolerance")),
                    DataUtil.matrixA.length);
            createViewResult();
        }catch (Exception e){
            Toast.makeText(GaussSeidelActivity.this,"Error, please check " +
                    "the data",Toast.LENGTH_LONG).show();
        }
    }

    public void createViewResult() {
        table.removeAllViews();
        for(int j=0;j<DataUtil.matrixA.length;j++){
            textView= new TextView(this);
            textView.setText("x"+(j+1));
            textView.setPadding(5,3,5,5);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(15);
            tableRow1.addView(textView);
        }

        textView= new TextView(this);
        textView.setText("Mayor ...");
        textView.setPadding(5,3,5,5);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(15);
        tableRow1.addView(textView);
        table.addView(tableRow1);

        for(int i=0;i<GaussSeidel.tableArray.size()-1;i++){
            tableRow = new TableRow(this);
            for(int j=0;j<GaussSeidel.tableArray.get(j).size();j++){
                textView = new TextView(this);
                textView.setPadding(5,5,8,5);
                textView.setGravity(Gravity.START);
                textView.setTextSize(15);
                textView.setText(GaussSeidel.tableArray.get(i).get(j));
                tableRow.addView(textView);
            }
            table.addView(tableRow);
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
        tableVectorX.removeAllViews();

        tableRow = new TableRow(this);
        textView= new TextView(this);
        textView.setText("x");
        textView.setPadding(5,3,5,5);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(15);
        tableRow.addView(textView);
        tableVectorX.addView(tableRow);

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


    public void setMatrix_VectorData()throws Exception{
        tolText.setText(DataUtil.equationSystemDataMap.get("Tolerance"));
        iterText.setText(DataUtil.equationSystemDataMap.get("Iterations"));

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
            for (int i = 0; i < n; i++) {
                temp = (EditText) findViewById(idEditText);
                temp.setText(DataUtil.vectorX[i].toString());
                idEditText++;
            }
        }
    }


    public void getMatrixData()throws Exception{
        DataUtil.equationSystemDataMap.put("Tolerance",tolText.getText().toString());
        DataUtil.equationSystemDataMap.put("Iterations",iterText.getText().toString());

        String nValue = nText.getText().toString();
        int n = Integer.parseInt(nValue);
        DataUtil.matrixA = new BigDecimal[n][n];
        DataUtil.vectorB = new BigDecimal[n];
        DataUtil.vectorX = new BigDecimal[n];
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
        for (int i = 0; i < n; i++) {
            temp = (EditText) findViewById(idEditText);
            DataUtil.vectorX[i] = temp.getText().toString().length()==0 ? BigDecimal.ZERO: new BigDecimal(temp.getText().toString());
            idEditText++;
        }
    }


    public void onHelpClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.gaussSeidelHelp)
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
                            Toast.makeText(GaussSeidelActivity.this,
                                    R.string.ExceptionE,Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton(R.string.scaled, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            scaledPivoting();
                        } catch (Exception e) {
                            Toast.makeText(GaussSeidelActivity.this,
                                    R.string.ExceptionE,Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setPositiveButton(R.string.total, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            totalPivoting();
                        } catch (Exception e) {
                            Toast.makeText(GaussSeidelActivity.this,
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
            Toast.makeText(GaussSeidelActivity.this,
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
            Toast.makeText(GaussSeidelActivity.this,
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
            Toast.makeText(GaussSeidelActivity.this,
                    R.string.ExceptionE,Toast.LENGTH_LONG).show();
        }
    }

}
