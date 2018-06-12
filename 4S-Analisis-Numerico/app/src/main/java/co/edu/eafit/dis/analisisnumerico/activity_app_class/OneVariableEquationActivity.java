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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_variable_equation);

    }

    public void button_Bincremental(View view) {
        Intent intent;
        intent = new Intent(OneVariableEquationActivity.this,IncrementalSearchActivity.class);
        startActivity(intent);
    }
    public void button_biseccion(View view) {
        Intent intent;
        intent = new Intent(OneVariableEquationActivity.this,BisectionActivity.class);
        startActivity(intent);
    }

    public void button_Reglafalse(View view) {
        Intent intent;
        intent = new Intent(OneVariableEquationActivity.this,FalseRuleActivity.class);
        startActivity(intent);
    }

    public void button_Fixed(View view) {
        Intent intent;
        intent = new Intent(OneVariableEquationActivity.this,FixedPointActivity.class);
        startActivity(intent);
    }

    public void button_newton(View view) {
        Intent intent;
        intent = new Intent(OneVariableEquationActivity.this, NewtonActivity.class);
        startActivity(intent);

    }
    public void button_secante(View view) {
        Intent intent;
        intent = new Intent(OneVariableEquationActivity.this,SecantActivity.class);
        startActivity(intent);
    }

    public void button_multiples(View view) {
        Intent intent;
        intent = new Intent(OneVariableEquationActivity.this,MultipleRootsActivity.class);
        startActivity(intent);
    }

    public void button_grafi(View view) {
        Intent intent;
        intent = new Intent(OneVariableEquationActivity.this,GraphDataActivity.class);
        startActivity(intent);
    }


}

