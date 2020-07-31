public class MyFFT {
    //число точек дискретного преобразования
    static int Ndft = 256;
    //частота дискретизации
    static double Fdft = 8000;

    //вывод массива в строку
    public void arraytostring(double[] array){
        System.out.print("Значения массива: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "; ");
        }
        System.out.println();
    }

    //расчет прямого дискретного преобразования Фурье
    public Complex[] dft(Complex[] data) {
        Complex[] data_dft = new Complex[Ndft];
        for (int i = 0; i < Ndft; i++) {
            Complex dft = new Complex(0, 0);
            for (int j = 0; j < Ndft; j++) {
                double arg = 2 * Math.PI * j * i / (double) Ndft;
                Complex test;
                test = new Complex (data[j].getRe()*Math.cos(arg),-data[j].getRe()*Math.sin(arg));
                dft = dft.add(test);
            }
            data_dft[i] = new Complex(dft.getRe(), dft.getIm());
        }
        return data_dft;
    }

    //расчет обратного дискретного преобразования Фурье
    public Complex[] odft(Complex[] data) {
        Complex[] data_odft = new Complex[Ndft];
        for (int i = 0; i < Ndft; i++) {
            Complex odft = new Complex(0, 0);
            for (int j = 0; j < Ndft; j++) {
                odft.add(data[j].prod(new Complex (Math.cos(2 * Math.PI * j * i / (double) Ndft),
                        Math.sin(2 * Math.PI * j * i / (double) Ndft))));
            }
            data_odft[i] = new Complex(odft.getRe()/Ndft, odft.getIm()/Ndft);
        }
        return data_odft;
    }

    //расчет быстрого преобразования Фурье
    public static Complex wn(int k, int N) {
        //if (k % N == 0) return new Complex(1, 1);
        //else {
            double arg = -2 * Math.PI * k / (double) N;
            return new Complex(Math.cos(arg), Math.sin(arg));
        //}
    }

    public static Complex[] fft(Complex[] x) {
        int n = x.length;

        // base case
        if (n == 1) return new Complex[] { x[0] };

        // radix 2 Cooley-Tukey FFT
        if (n % 2 != 0) {
            throw new IllegalArgumentException("n is not a power of 2");
        }

        // compute FFT of even terms
        Complex[] even = new Complex[n/2];
        for (int k = 0; k < n/2; k++) {
            even[k] = x[2*k];
        }
        Complex[] evenFFT = fft(even);

        // compute FFT of odd terms
        Complex[] odd  = even;  // reuse the array (to avoid n log n space)
        for (int k = 0; k < n/2; k++) {
            odd[k] = x[2*k + 1];
        }
        Complex[] oddFFT = fft(odd);

        // combine
        Complex[] y = new Complex[n];
        for (int k = 0; k < n/2; k++) {
            double kth = -2 * k * Math.PI / n;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            y[k]       = evenFFT[k].add (wk.prod(oddFFT[k]));
            y[k + n/2] = evenFFT[k].sub(wk.prod(oddFFT[k]));
        }
        return y;
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
        double A1 = 1;
        double A2 = 0.5;
        //частота сигнала
        double f1 = 1000;
        double f2 = 2000;
        //шаг по частоте
        double fd = Fdft / Ndft;
        //массив под данные
        Complex[] var1 = new Complex[Ndft];
        Complex[] var2 = new Complex[Ndft];

        for (int i = 0; i < Ndft; i++) {
            var1[i] = new Complex(A1 * Math.sin(2 * Math.PI * f1 * i / Fdft) , 0);
            //System.out.println(i+ " "+ var2[i].toString());
        }

        for (int i = 0; i < Ndft; i++) {
            var2[i] = new Complex(A1 * Math.sin(2 * Math.PI * f1 * i / Fdft) + A2 * Math.sin(2 * Math.PI * f2 * i / Fdft + 3 * Math.PI / 4), 0);
            //System.out.println(i+ " "+ var2[i].toString());
        }


        MyFFT myfft = new MyFFT();
        //myfft.arraytostring(var);

        MyFFT test = new MyFFT();

        System.out.println(" DFT: " );
        for (int i = 0; i < test.dft(var2).length; i++) {
            System.out.println(i*fd + " : " + test.dft(var2)[i].abs());
        }
        System.out.println(" FFT: " );
        for (int i = 0; i < test.fft(var2).length; i++) {
            System.out.println(i*fd + " : " + test.fft(var2)[i].abs());
        }
    }
}
