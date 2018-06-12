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

    }

    public void button_Ecuaciones(View view) {
        Intent intent;
        intent = new Intent(InitActivity.this,OneVariableEquationActivity.class);
        startActivity(intent);
    }

    public void button_sistemasde(View view) {
        Intent intent;
        intent = new Intent(InitActivity.this,EquationSystemActivity.class);
        startActivity(intent);
    }

    public void button_Interpolacion(View view) {
        Intent intent;
        intent = new Intent(InitActivity.this,InterpolationActivity.class);
        startActivity(intent);
    }
}
