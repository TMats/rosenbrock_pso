public class Rosenbrock{
    // set maxmum & minimum of x
    private static double max = 2.048;
    private static double min = -2.048;
    // generate initial x
    public static double[] init_x(int dim) {
        double[] x = new double[dim];
        // generate initial x
        for (int i = 0; i < dim; i++) {
            x[i] = Math.random() * (max - min) + min;
        }

        return x;
    }

    // calcuate function value
    public static double get_value(double[] x){
        int dim = x.length;
        double val = 0;
        for(int i=0; i<dim-1; i++){
            val += (100*Math.pow((x[i+1]-Math.pow(x[i],2)),2) + Math.pow(1 - x[i],2));
        }
        return val;
    }
}
