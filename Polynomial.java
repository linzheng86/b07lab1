import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Polynomial{

    double[] coefficients;
    int[] exponents;
    
    public Polynomial(){
        this.coefficients = null;
        this.exponents = null;
    }
   
    public Polynomial(double[] coefficients, int[] exponents){
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

    public Polynomial add_zero(Polynomial x){
        int max = x.exponents[0];
        for(int i = 0; i < x.exponents.length; i++){
            if(max < x.exponents[i]){
                max = x.exponents[i];
            }
        }
        int [] new_exp = new int [max + 1];
        for(int i = 0; i < new_exp.length; i++)
        {
            new_exp[i] = i;
        }
        double [] new_coe = new double[max + 1];
        for(int i = 0; i < x.exponents.length; i++){
            int a = x.exponents[i];
            new_coe[a] = x.coefficients[i];
        }
        return new Polynomial(new_coe, new_exp);
    }
    
    public Polynomial remove_zero(Polynomial x)
    {
        int a = 0;
        for(int i = 0; i < x.coefficients.length; i++){
            if(x.coefficients[i] != 0){
                a++;
            }
        }
        int [] new_exp = new int[a];
        double [] new_coe = new double[a];
        int b = 0;
        for (int i = 0; i < x.coefficients.length; i++){
            if (x.coefficients[i] != 0){
                new_exp[b] = x.exponents[i];
                new_coe[b] = x.coefficients[i];
                b++;
            }
        }
        return new Polynomial(new_coe, new_exp);
    }

    public Polynomial add(Polynomial x){
        if(this.exponents.length == 0){
            return x;
        }
        if(x.exponents.length == 0){
            return this;
        }
        Polynomial new_x = add_zero(x);
        Polynomial new_polynomial = add_zero(this);
        int newlength = Math.max(new_polynomial.coefficients.length, new_x.coefficients.length);
        double[] new_coe = new double[newlength];
        int [] new_exp = new int [newlength];
        for (int i = 0; i < new_exp.length; i++){
            new_exp[i] = i;
        }
        if(new_polynomial.coefficients.length >= new_x.coefficients.length){
            for(int i = 0; i < new_x.coefficients.length; i++) {
                new_coe[i] = new_polynomial.coefficients[i] + new_x.coefficients[i];
            }
            for(int j = new_x.coefficients.length; j < new_polynomial.coefficients.length; j++)
            {
                new_coe[j] = new_polynomial.coefficients[j];
            }
        }
        else{
            for(int i = 0; i < new_polynomial.coefficients.length; i++){
               new_coe[i] = new_x.coefficients[i] + new_polynomial.coefficients[i];
            }
            for(int j = new_polynomial.coefficients.length; j < new_x.coefficients.length; j++)
            {
               new_coe[j] = new_x.coefficients[j];
            }
        }
        Polynomial result = new Polynomial(new_coe, new_exp);
        return remove_zero(result);
    }

    public double evaluate(double value){  
        double result = 0;
        for(int i = 0; i < coefficients.length; i++){
            result = result + this.coefficients[i] * Math.pow(value, this.exponents[i]);
        }
        return result;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }

    public Polynomial multiply(Polynomial x)
    {        
        if(this.exponents.length == 0){
            return this;
        }
        if(x.exponents.length == 0){
            return x;
        }
        int x_largest = x.exponents[0];
        for(int i = 0; i < x.exponents.length; i++){
            if(x_largest < x.exponents[i]){
                x_largest = x.exponents[i];
            }
        }
        int this_largest = this.exponents[0];
        for(int i = 0; i < this.exponents.length; i++){
            if(this_largest < this.exponents[i]){
                this_largest = this.exponents[i];
            }
        }
        int [] new_exp = new int [x_largest + this_largest + 1];
        double [] new_coe = new double [x_largest + this_largest + 1];
        for(int i = 0; i < this.coefficients.length; i++){
            for (int j = 0; j < x.coefficients.length; j++){
                int a = this.exponents[i] + x.exponents[j];
                new_exp[a - 1] = a;
                new_coe[a - 1] += this.coefficients[i] * x.coefficients[j];
            }
        }
        Polynomial result = new Polynomial(new_coe, new_exp);
        return remove_zero(result);
    }

    public Polynomial(File file) throws IOException {
        List<Double> coefficientsList = new ArrayList<>();
        List<Integer> exponentsList = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        String[] part_result = line.split("(?=\\+|-)");
        for (String part_res: part_result){
            String[] res = part_res.split("x");
            if (res.length == 1){
                coefficientsList.add(Double.parseDouble(res[0]));
                exponentsList.add(0);
            } 
            else{
                coefficientsList.add(Double.parseDouble(res[0]));
                exponentsList.add(Integer.parseInt(res[1]));
                }
            }
            this.coefficients = new double[coefficientsList.size()];
            this.exponents = new int [exponentsList.size()];
            for (int i = 0; i < coefficientsList.size(); i++) {
                this.coefficients[i] = coefficientsList.get(i);
                this.exponents[i] = exponentsList.get(i);
            }
        scanner.close();
    }    

    public String toString() {
        String result = "";
        boolean first = true;
        for (int i = 0; i < coefficients.length; i++) {
            if(first == false){
                if(coefficients[i] > 0){
                    result += "+";
                }
            }
            else{
                first = false;
            }
            result += coefficients[i];
            if(exponents[i] != 0) {
                result += "x";
                result += exponents[i];
            }
        }
        return result;
    }

    public void saveToFile(String fileName) throws IOException {
        PrintStream ps = new PrintStream(fileName);
        ps.println(this.toString());
        ps.close();
    }
}