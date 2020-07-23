public class Complex {
    private double Re;
    private double Im;

    public Complex(double re, double im) {
        Re = re;
        Im = im;
    }

    public double getRe() {
        return Re;
    }

    public double getIm() {
        return Im;
    }

    public void setRe(double a) {
        Re = a;
    }

    public void setIm(double a) {
        Im = a;
    }

    //вывод в тектс
    public String toString(){
        return "Re=" + Re + " Im=" + Im + "i";
    }

    //модуль
    public double abs() {
        return Math.sqrt(Re*Re + Im*Im);
    }

    //суммирование
    public Complex add(Complex b) {
        Complex a = this;
        double real = a.Re + b.getRe();
        double imag = a.Im + b.getIm();
        return new Complex(real, imag);
    }
    //вычитание
    public Complex sub(Complex b) {
        Complex a = this;
        double real = a.Re - b.getRe();
        double imag = a.Im - b.getIm();
        return new Complex(real, imag);
    }
    //произведение
    public Complex prod(Complex b) {
        Complex a = this;
        double real = a.Re * b.Re - a.Im * b.Im;
        double imag = a.Re * b.Im + a.Im * b.Re;
        return new Complex(real, imag);
    }

    //аргумент
    public double arg(){
        return Math.tan(Im/Re);
    }

}
