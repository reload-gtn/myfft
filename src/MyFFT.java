public class MyFFT {
    //число точек дискретного преобразования
    static int Ndft = 512;
    //частота дискретизации
    static double Fdft = 1024;

    //расчет дискретного преобразования Фурье
    public Complex[] dft(double[] data) {
        Complex[] var_dft = new Complex[Ndft];
        for (int i = 0; i < Ndft; i++) {
            Complex dft = new Complex(0, 0);
            for (int j = 0; j < Ndft; j++) {
                dft.add(new Complex(data[j] * Math.cos(2 * Math.PI * j * i / (double) Ndft), -data[j] * Math.sin(2 * Math.PI * j * i / (double) Ndft)));
            }
            var_dft[i] = dft;
        }
        return var_dft;
    }

    //расчет быстрого преобразования Фурье
    public Complex[] fft(double[] data) {
        Complex[] var_fft = new Complex[Ndft];
        for (int i = 0; i < Ndft; i++) {
            Complex fft1 = new Complex(0, 0);
            Complex fft2 = new Complex(0, 0);
            for (int j = 0; j < Ndft/2; j++) {
                fft1.add(new Complex(data[2*j] * Math.cos(2 * Math.PI * 2*j * i / (double) Ndft), -data[2*j] * Math.sin(2 * Math.PI * (2*j) * i / (double) Ndft)));
                fft2.add(new Complex(data[2*j+1] * Math.cos(2 * Math.PI * (2*j + 1) * i / (double) Ndft), -data[2*j+1] * Math.sin(2 * Math.PI * (2*j+1) * i / (double) Ndft)));
            }
            var_fft[i] = fft1.add(fft2);
        }
        return var_fft;
    }

    //расчет быстрого преобразования Фурье 2
    public Complex[] fft2(double[] data) {
        Complex[] var_fft = new Complex[Ndft];
        for (int i = 0; i < Ndft; i++) {
            Complex even = new Complex(0, 0);
            Complex odd = new Complex(0, 0);
            for (int j = 0; j < Ndft/2; j++) {
                even.add(new Complex(data[2*j] * Math.cos(2 * Math.PI * 2*j * i / (double) Ndft), -data[2*j] * Math.sin(2 * Math.PI * (2*j) * i / (double) Ndft)));
                odd.add(new Complex(data[2*j+1] * Math.cos(2 * Math.PI * 2*j * i / (double) Ndft), -data[2*j+1] * Math.sin(2 * Math.PI * (2*j) * i / (double) Ndft)));
            }
            var_fft[i] = even.add(odd.prod(new Complex(Math.cos(2 * Math.PI * i / (double) Ndft), -Math.sin(2 * Math.PI * i / (double) Ndft))));
        }
        return var_fft;
    }
/*
    //расчет быстрого преобразования Фурье 3
    public Complex[] fft3(double data) {
        Complex[] var_fft = new Complex[Ndft];
        for (int i = 0; i < Ndft; i++) {
            Complex[] even = new Complex[Ndft/2];
            Complex[] odd = new Complex[Ndft/2];
            for (int j = 0; j < Ndft/2; j++) {
                even[i] = new Complex (data[2*j] * Math.cos(2 * Math.PI * 2*j * i / (double) Ndft), -data[2*j] * Math.sin(2 * Math.PI * (2*j) * i / (double) Ndft));
                odd[i] = new Complex(data[2*j+1] * Math.cos(2 * Math.PI * 2*j * i / (double) Ndft), -data[2*j+1] * Math.sin(2 * Math.PI * (2*j) * i / (double) Ndft));
            }
            Complex[] even2 = even.;
            Complex[] odd = new Complex[Ndft/2];
            var_fft[i] = even.add(odd.prod(new Complex(Math.cos(2 * Math.PI * i / (double) Ndft), -Math.sin(2 * Math.PI * i / (double) Ndft))));
        }
        return var_fft;
    }
*/

    public static void main(String[] args) {
        //амплитуда сигнала
        double A = 1;
        //частота сигнала
        double f = 160;
        //шаг по частоте
        double fd = Fdft / Ndft;
        //массив под данные
        double[] var = new double[Ndft];

        for (int i = 0; i < Ndft; i++) {
            var[i] = A * Math.cos(2 * Math.PI * f * i / Fdft);
        }

        MyFFT test = new MyFFT();

        System.out.println(" DFT: " );
        for (int i = 0; i <= test.dft(var).length / 2; i++) {
            System.out.println(i * fd + " Гц: " + 2 * test.dft(var)[i].abs() / Ndft);
        }
        System.out.println(" FFT: " );
        for (int i = 0; i <= test.fft2(var).length / 2; i++) {
            System.out.println(i * fd + " Гц: " + 2 * test.fft2(var)[i].abs() / Ndft);
        }
    }
}
