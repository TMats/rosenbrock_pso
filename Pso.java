import java.util.Arrays;
import java.util.ArrayList;

class Particle{
    // Consts
    public static double W;
    public static double C1;
    public static double C2;

    // define 'global_best's
    public static double[] global_best_x;
    public static double global_best_value;

    private int dim;
    private double[] x;
    private double[] v;
    private double alpha;
    private double value;
    private double[] best_x;
    private double best_value;

    Particle(int dim){
        this.dim = dim;
        x = Rosenbrock.init_x(dim);
        v = new double[dim];
        alpha = Math.random()*0.5+0.5;
        // alpha = 1.0;
        // alpha = 0.8;
        // alpha = 0.6;
        value = Rosenbrock.get_value(x);
        best_x = Arrays.copyOf(x,x.length);
        best_value = value;
    }

    double[] get_x(){ return x; }
    double get_value(){ return value; }

    void update() {
        double[] new_x = Arrays.copyOf(x,x.length);
        double[] new_v = Arrays.copyOf(v,v.length);
        double r1 = Math.random();
        double r2 = Math.random();
        for (int i = 0; i < dim; i++) {
            new_x[i] += v[i];
            new_v[i] = alpha*(W * v[i] + C1 * r1 * (best_x[i] - x[i]) + C2 * r2 * (global_best_x[i] - x[i]));
        }
        x = new_x;
        v = new_v;
        value = Rosenbrock.get_value(x);
    }

    void update_best(){
        if(value<best_value){
            best_value = value;
            best_x = Arrays.copyOf(x,x.length);
        }
        if(value<global_best_value){
            global_best_value = value;
            global_best_x = Arrays.copyOf(x,x.length);

        }
    }

}


public class Pso{
    public static double execute(int N, int D, int T, double[] params){
        Particle.W =params[0];
        Particle.C1=params[1];
        Particle.C2=params[2];

        Particle.global_best_x = new double[N];
        Particle.global_best_value = Double.POSITIVE_INFINITY;

        ArrayList<Particle> particle_list = new ArrayList<Particle>();
        for(int i=0;i<N;i++){
            Particle part = new Particle(D);
            particle_list.add(part);
            particle_list.get(i).update_best();
        }

        for(int t=1;t<T+1;t++){
            for(int i=0;i<N;i++){
                particle_list.get(i).update();
                particle_list.get(i).update_best();
            }
        }
        double result = Particle.global_best_value;
        return result;
    }

    public static void main(String[] args) {
        // number of particles
        int N = 4000;
        // dimension
        int D = 5;
        // number of iteration
        int T = 250;
        // parameters
        double[] params = {0.7,0.4,0.3};
        // execute
        double result = execute(N,D,T,params);
        System.out.println(result);
    }
}
