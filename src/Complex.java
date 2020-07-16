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
    public void add(Complex b) {
        Re = Re + b.getRe();
        Im = Im + b.getIm();
    }
    //вычитание
    public void sub(Complex b) {
        Re = Re - b.getRe();
        Im = Im - b.getIm();
    }
    //произведение
    public void prod(Complex b) {
        Re = Re * b.getRe() - Im * b.getIm();
        Im = Im * b.getRe() + Re * b.getIm();
    }

    //аргумент
    public double arg(){
        return Math.tan(Im/Re);
    }

}
