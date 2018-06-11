package co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.eafit.dis.analisisnumerico.R;
import co.edu.eafit.dis.analisisnumerico.utility_class.DataUtil;

public class GraphDataActivity extends AppCompatActivity {

    private EditText funcText,maxIterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_data);

        funcText = (EditText)findViewById(R.id.funcText);
        maxIterText = (EditText)findViewById(R.id.maxIterText);
        setDataHashMap();
    }

    public void onCalculateClick(View view){
        try {
            Intent intent = new Intent(GraphDataActivity.this, GraphViewActivity.class);
            String f = funcText.getText().toString();
            double points = Double.parseDouble(maxIterText.getText().toString());
            getDataHashMap();
            intent.putExtra("function", f);
            intent.putExtra("values", points);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this,"Error, please check the data, if the problem continue " +
                    "may be the method can not operate correctly with this data " +
                    "",Toast.LENGTH_LONG).show();
        }
    }

    public void setDataHashMap(){
        funcText.setText(DataUtil.dataMap.get("Function_fx"));
        maxIterText.setText(DataUtil.dataMap.get("Iterations"));
    }

    public void getDataHashMap(){
        DataUtil.dataMap.put("Function_fx",funcText.getText().toString());
        DataUtil.dataMap.put("Iterations",maxIterText.getText().toString());
    }
}
