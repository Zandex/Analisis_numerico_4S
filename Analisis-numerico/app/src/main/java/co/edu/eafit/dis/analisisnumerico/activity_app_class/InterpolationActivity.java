package co.edu.eafit.dis.analisisnumerico.activity_app_class;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import co.edu.eafit.dis.analisisnumerico.R;
import co.edu.eafit.dis.analisisnumerico.interpolation.activity_class.CubicSplineActivity;
import co.edu.eafit.dis.analisisnumerico.interpolation.activity_class.LagrangeActivity;
import co.edu.eafit.dis.analisisnumerico.interpolation.activity_class.LinearSplineActivity;
import co.edu.eafit.dis.analisisnumerico.interpolation.activity_class.NevilleActivity;
import co.edu.eafit.dis.analisisnumerico.interpolation.activity_class.NewtonDividedDifferenceActivity;
import co.edu.eafit.dis.analisisnumerico.interpolation.activity_class.QuadraticSplineActivity;

public class InterpolationActivity extends AppCompatActivity {

    private static ListView list_view;
    private static String[] METHODS = new String[] {
            "Newton Divided Differences", "Lagrange", "Neville",
            "Spline Lineal", "Spline Cuadratico", "Spline Cubico"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolation);
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
                            case "Newton Divided Differences":
                                intent = new Intent(InterpolationActivity.this,NewtonDividedDifferenceActivity.class);
                                startActivity(intent);
                                break;
                            case "Lagrange":
                                intent = new Intent(InterpolationActivity.this,LagrangeActivity.class);
                                startActivity(intent);
                                break;
                            case "Neville":
                                intent = new Intent(InterpolationActivity.this,NevilleActivity.class);
                                startActivity(intent);
                                break;
                            case "Spline Lineal":
                                intent = new Intent(InterpolationActivity.this,LinearSplineActivity.class);
                                startActivity(intent);
                                break;
                            case "Spline Cuadratico":
                                intent = new Intent(InterpolationActivity.this,QuadraticSplineActivity.class);
                                startActivity(intent);
                                break;
                            case "Spline Cubico":
                                intent = new Intent(InterpolationActivity.this,CubicSplineActivity.class);
                                startActivity(intent);
                                break;
                        }

                    }
                }
        );
    }
}

