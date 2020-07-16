public class MyFFT {
    public static void main (String[] args) {
        //амплитуда сигнала
        double A = 2;
        //частота сигнала
        double f = 32;
        double fd;
        //частота дискретизации
        int Fs = 500;
        //число отсчетов
        int Nf = 10;
        double[] var = new double[Nf];
        Complex[] var_fft = new Complex[Nf];

        for (int i = 0; i < Nf; i++) {
            var[i] = 1.0;
            //var[i]=A*Math.cos(2*Math.PI*f*i/(double)Nf);
        }
        //System.out.println(var[1]);

        for (int i = 0; i < Nf; i++) {
            Complex fft = new Complex(0,0);
            for (int j = 0; j < Nf; j++) {
                //fft.add(new Complex (1, -1));
                fft.add(new Complex (var[j]*Math.cos(2*Math.PI*j*i/(double)Nf), -var[j]*Math.sin(2*Math.PI*j*i/(double)Nf)));
            }
            var_fft[i] = fft;
            //System.out.println(var_fft[i].toString());
        }

        for (int i = 0; i < Nf; i++) {
            fd = (i*Fs)/(double) Nf;
            //System.out.println(fd);
            System.out.println((double) fd + " Гц: " + var_fft[i].toString());
        }

        //Complex a = new Complex(1,1);
        //Complex b = new Complex(1,-1);
        //System.out.println(a.toString());
        //System.out.println(b.toString());
        //Complex c = a;
        //a.add(b);
        //System.out.println(a.toString());
        //System.out.println(a.getRe() + " + " + a.getIm() + "i");

    }
}
