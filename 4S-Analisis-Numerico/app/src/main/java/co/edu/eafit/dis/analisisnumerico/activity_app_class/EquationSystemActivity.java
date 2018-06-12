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
    private static ListView list_view;
    private static String[] METHODS = new String[] {
            "Eliminacion Gaussiana", "LU Factorizacion Cholesky", "LU Factorizacion Doolittle",
            "LU Factorizacion Crout", "Jacobi", "Jacobi Relajado", "Gauss Seidel",
            "Gauss Seidel Relajado"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_system);
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
                            case "Eliminacion Gaussiana":
                                intent = new Intent(EquationSystemActivity.this,GaussianEliminationActivity.class);
                                startActivity(intent);
                                break;
                            case "LU Factorizacion Cholesky":
                                intent = new Intent(EquationSystemActivity.this,LUFactorizationCholeskyActivity.class);
                                startActivity(intent);
                                break;
                            case "LU Factorizacion Doolittle":
                                intent = new Intent(EquationSystemActivity.this,LUFactorizationDoolittleActivity.class);
                                startActivity(intent);
                                break;
                            case "LU Factorizacion Crout":
                                intent = new Intent(EquationSystemActivity.this,LUFactorizationCroutActivity.class);
                                startActivity(intent);
                                break;
                            case "Jacobi":
                                intent = new Intent(EquationSystemActivity.this,JacobiActivity.class);
                                startActivity(intent);
                                break;
                            case "Jacobi Relajado":
                                intent = new Intent(EquationSystemActivity.this,JacobiRelaxedActivity.class);
                                startActivity(intent);
                                break;
                            case "Gauss Seidel":
                                intent = new Intent(EquationSystemActivity.this,GaussSeidelActivity.class);
                                startActivity(intent);
                                break;
                            case "Gauss Seidel Relajado":
                                intent = new Intent(EquationSystemActivity.this,GaussSeidelRelaxedActivity.class);
                                startActivity(intent);
                                break;
                        }
                    }
                }
        );
    }
}
