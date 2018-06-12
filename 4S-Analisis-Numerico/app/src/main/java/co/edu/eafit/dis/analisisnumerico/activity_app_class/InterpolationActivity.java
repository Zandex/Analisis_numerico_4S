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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolation);

    }



    public void button_newton(View view) {
        Intent intent;
        intent = new Intent(InterpolationActivity.this,NewtonDividedDifferenceActivity.class);
        startActivity(intent);
    }

    public void button_lagran(View view) {
        Intent intent;
        intent = new Intent(InterpolationActivity.this,LagrangeActivity.class);
        startActivity(intent);
    }

    public void button_nevil(View view) {
        Intent intent;
        intent = new Intent(InterpolationActivity.this,NevilleActivity.class);
        startActivity(intent);
    }

    public void button_spliline(View view) {
        Intent intent;
        intent = new Intent(InterpolationActivity.this,LinearSplineActivity.class);
        startActivity(intent);
    }

    public void button_splicuadr(View view) {
        Intent intent;
        intent = new Intent(InterpolationActivity.this,QuadraticSplineActivity.class);
        startActivity(intent);
    }

    public void button_splicubi(View view) {
        Intent intent;
        intent = new Intent(InterpolationActivity.this,CubicSplineActivity.class);
        startActivity(intent);
    }
}

