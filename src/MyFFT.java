public class MyFFT {
    public static void main (String[] args) {
        //амплитуда сигнала
        double A = 1;
        //частота сигнала
        double f = 1;
        //частота дискретизации
        int Ff = 8;
        //число отсчетов
        int Nf = 8;
        double[] realarg = new double[Nf];
        double[] imgarg = new double[Nf];



        Complex a = new Complex(1,1);
        Complex b = new Complex(1,-1);
        System.out.println(a.toString());
        System.out.println(b.toString());
        //Complex c = a;
        a.add(b);
        System.out.println(a.toString());
        //System.out.println(a.getRe() + " + " + a.getIm() + "i");

    }
}
