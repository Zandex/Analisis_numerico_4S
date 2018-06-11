package co.edu.eafit.dis.analisisnumerico.utility_class;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import com.udojava.evalex.Expression;

import java.math.BigDecimal;

public class ExpressionEvalUtil {

    public static BigDecimal functionEval(String arg, BigDecimal number) {
        BigDecimal result = new Expression(arg).with("x", number).eval();
        return result;
    }


}