package co.edu.eafit.dis.analisisnumerico.one_variable_equation.activity_class;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.math.BigDecimal;

import co.edu.eafit.dis.analisisnumerico.R;
import co.edu.eafit.dis.analisisnumerico.utility_class.ExpressionEvalUtil;

public class GraphViewActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_graph_view);
            String function = getIntent().getStringExtra("function");
            double values = getIntent().getDoubleExtra("values",1000);
            createGraph(function, values);
        }catch (Exception e){
            Toast.makeText(this,"Error, please check the data, if the problem continue " +
                    "may be the method can not operate correctly with this data " +
                    " "+e,Toast.LENGTH_LONG).show();
        }
    }

    public void createGraph(String f,double value) throws Exception{
        GraphView graph = (GraphView) findViewById(R.id.graph1);
        DataPoint[] points = new DataPoint[(int)value];
        for (int i = 0; i < points.length; i++) {
            points[i] = new DataPoint(i, ExpressionEvalUtil.functionEval(f,new BigDecimal(i)).doubleValue());
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);

        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(100);

// set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100);
        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true); // enables vertical scrolling
        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling

        graph.addSeries(series);
    }
}
