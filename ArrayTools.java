public class ArrayTools{
    public static double get_average(double[] array){
        int dim = array.length;
        double sum = 0;
        for(int i=1; i<array.length; i++) {
            sum +=array[i];
        }
        double ave = sum/dim;
        return ave;
    }
}
