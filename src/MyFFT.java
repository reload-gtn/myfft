public class MyFFT {
    //число точек дискретного преобразования
    static int Ndft = 100;
    //частота дискретизации
    static double Fdft = 8000;

    //расчет дискретного преобразования Фурье
    public Complex[] dft(double[] data){
        Complex[] var_dft = new Complex[Ndft];
        for (int i = 0; i < Ndft; i++) {
            Complex dft = new Complex(0,0);
            for (int j = 0; j < Ndft; j++) {
                dft.add(new Complex (data[j]*Math.cos(2*Math.PI*j*i/(double)Ndft), -data[j]*Math.sin(2*Math.PI*j*i/(double)Ndft)));
            }
            var_dft[i] = dft;
        }
        return var_dft;
    }
/*
    private static Complex w(double k, double N)
    {
        if (k % N == 0) return new Complex(1, 1);
        double arg = -2 * Math.PI * k / N;
        return new Complex(Math.cos(arg), Math.sin(arg));
    }

    public static Complex[] fft(Complex[] x)
    {
        Complex[] X;
        int N = x.length;
        if (N == 2)
        {
            X = new Complex[2];
            x[0].add(x[1]);
            X[0] =x[0];
            x[0].sub(x[0]);
            X[1] = x[0];
        }
        else
        {
            Complex[] x_even = new Complex[N / 2];
            Complex[] x_odd = new Complex[N / 2];
            for (int i = 0; i < N / 2; i++)
            {
                x_even[i] = x[2 * i];
                x_odd[i] = x[2 * i + 1];
            }
            Complex[] X_even = fft(x_even);
            Complex[] X_odd = fft(x_odd);
            X = new Complex[N];
            for (int i = 0; i < N / 2; i++)
            {
                X[i] = X_even[i] + w(i, N) * X_odd[i];
                X[i + N / 2] = X_even[i] - w(i, N) * X_odd[i];
            }
        }
        return X;
    }
*/
    public static void main (String[] args) {
        //амплитуда сигнала
        double A = 1;
        //частота сигнала
        double f = 400;
        //шаг по частоте
        double fd = Fdft/Ndft;
        //массив под данные
        double[] var = new double[Ndft];

        for (int i = 0; i < Ndft; i++) {
            var[i]=A*Math.cos(2*Math.PI*f*i/Fdft);
        }

        MyFFT test = new MyFFT();

        for (int i = 0; i <= test.dft(var).length/2; i++) {
            System.out.println(i*fd+ " Гц: " + 2*test.dft(var)[i].abs()/Ndft);
        }
    }
}
