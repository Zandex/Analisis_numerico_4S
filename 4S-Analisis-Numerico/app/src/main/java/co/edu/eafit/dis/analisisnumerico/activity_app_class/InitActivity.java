package co.edu.eafit.dis.analisisnumerico.activity_app_class;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;

import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.GraphViewActivity;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.MultipleRootsActivity;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.FalseRuleActivity;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.FixedPointActivity;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.IncrementalSearchActivity;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.NewtonActivity;
import co.edu.eafit.dis.analisisnumerico.R;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.SecantActivity;
import co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class.BisectionActivity;

public class InitActivity extends AppCompatActivity {

    private static ListView list_view;
    private static String[] METHODS = new String[] {
            "Ecuaciones de una Variable", "Sistema de Ecuaciones", "Interpolacion"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
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
                            case "Ecuaciones de una Variable":
                                intent = new Intent(InitActivity.this,OneVariableEquationActivity.class);
                                startActivity(intent);
                                break;
                            case "Sistema de Ecuaciones":
                                intent = new Intent(InitActivity.this,EquationSystemActivity.class);
                                startActivity(intent);
                                break;
                            case "Interpolacion":
                                intent = new Intent(InitActivity.this,InterpolationActivity.class);
                                startActivity(intent);
                                break;
                        }

                    }
                }
        );
    }
}
