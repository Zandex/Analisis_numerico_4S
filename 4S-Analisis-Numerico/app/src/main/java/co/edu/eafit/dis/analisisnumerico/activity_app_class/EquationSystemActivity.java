package co.edu.eafit.dis.analisisnumerico.activity_app_class;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import co.edu.eafit.dis.analisisnumerico.R;
import co.edu.eafit.dis.analisisnumerico.equation_system.activity_class.GaussSeidelActivity;
import co.edu.eafit.dis.analisisnumerico.equation_system.activity_class.GaussSeidelRelaxedActivity;
import co.edu.eafit.dis.analisisnumerico.equation_system.activity_class.GaussianEliminationActivity;
import co.edu.eafit.dis.analisisnumerico.equation_system.activity_class.JacobiActivity;
import co.edu.eafit.dis.analisisnumerico.equation_system.activity_class.JacobiRelaxedActivity;
import co.edu.eafit.dis.analisisnumerico.equation_system.activity_class.LUFactorizationCholeskyActivity;
import co.edu.eafit.dis.analisisnumerico.equation_system.activity_class.LUFactorizationCroutActivity;
import co.edu.eafit.dis.analisisnumerico.equation_system.activity_class.LUFactorizationDoolittleActivity;

public class EquationSystemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_system);
    }



    public void button_gauss(View view) {
        Intent intent;
        intent = new Intent(EquationSystemActivity.this,GaussianEliminationActivity.class);
        startActivity(intent);
    }

    public void button_chole(View view) {
        Intent intent;
        intent = new Intent(EquationSystemActivity.this,LUFactorizationCholeskyActivity.class);
        startActivity(intent);
    }

    public void button_dooli(View view) {
        Intent intent;
        intent = new Intent(EquationSystemActivity.this,LUFactorizationDoolittleActivity.class);
        startActivity(intent);
    }

    public void button_crou(View view) {
        Intent intent;
        intent = new Intent(EquationSystemActivity.this,LUFactorizationCroutActivity.class);
        startActivity(intent);
    }

    public void button_jacobi(View view) {
        Intent intent;
        intent = new Intent(EquationSystemActivity.this,JacobiActivity.class);
        startActivity(intent);
    }

    public void button_jacorel(View view) {
        Intent intent;
        intent = new Intent(EquationSystemActivity.this,JacobiRelaxedActivity.class);
        startActivity(intent);
    }

    public void button_gausse(View view) {
        Intent intent;
        intent = new Intent(EquationSystemActivity.this,GaussSeidelActivity.class);
        startActivity(intent);
    }

    public void button_gauserelx(View view) {
        Intent intent;
        intent = new Intent(EquationSystemActivity.this,GaussSeidelRelaxedActivity.class);
        startActivity(intent);
    }
}
