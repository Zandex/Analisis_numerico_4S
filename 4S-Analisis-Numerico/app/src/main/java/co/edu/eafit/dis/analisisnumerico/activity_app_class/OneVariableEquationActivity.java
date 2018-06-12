package co.edu.eafit.dis.analisisnumerico.activity_app_class;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.GraphDataActivity;
import co.edu.eafit.dis.analisisnumerico.R;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.BisectionActivity;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.FalseRuleActivity;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.FixedPointActivity;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.IncrementalSearchActivity;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.MultipleRootsActivity;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.NewtonActivity;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.SecantActivity;

public class OneVariableEquationActivity extends AppCompatActivity {

    private static ListView list_view;
    private static String[] METHODS = new String[] {
            "Busqueda Incremental", "Biseccion", "Regla Falsa",
            "Punto Fijo", "Newton", "Secante", "Raices Multiples",
            "Graficador"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_variable_equation);
        listView();
    }

    public void listView(){
        list_view = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.method_list,METHODS);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String value = (String)list_view.getItemAtPosition(position);
                        Intent intent;
                        switch(value){
                            case "Busqueda Incremental":
                                intent = new Intent(OneVariableEquationActivity.this,IncrementalSearchActivity.class);
                                startActivity(intent);
                                break;
                            case "Biseccion":
                                intent = new Intent(OneVariableEquationActivity.this,BisectionActivity.class);
                                startActivity(intent);
                                break;
                            case "Regla Falsa":
                                intent = new Intent(OneVariableEquationActivity.this,FalseRuleActivity.class);
                                startActivity(intent);
                                break;
                            case "Punto Fijo":
                                intent = new Intent(OneVariableEquationActivity.this,FixedPointActivity.class);
                                startActivity(intent);
                                break;
                            case "Newton":
                                intent = new Intent(OneVariableEquationActivity.this,NewtonActivity.class);
                                startActivity(intent);
                                break;
                            case "Secante":
                                intent = new Intent(OneVariableEquationActivity.this,SecantActivity.class);
                                startActivity(intent);
                                break;
                            case "Raices Multiples":
                                intent = new Intent(OneVariableEquationActivity.this,MultipleRootsActivity.class);
                                startActivity(intent);
                                break;
                            case "Graficador":
                                intent = new Intent(OneVariableEquationActivity.this,GraphDataActivity.class);
                                startActivity(intent);
                                break;
                        }

                    }
                }
        );
    }
}

