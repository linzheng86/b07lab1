public class Polynomial{
   double[] coefficients;
 
   public Polynomial(){
      coefficients = new double[]{0};
   }
 
   public Polynomial(double[] coefficients){
      this.coefficients = coefficients;
   }
 
   public Polynomial add(Polynomial x) {
      if (this.coefficients.length >= x.coefficients.length) {
         for(int i = 0; i < x.coefficients.length; i++) {
            this.coefficients[i] += x.coefficients[i];
         }
         return new Polynomial(this.coefficients);
      }
      else{
         for(int i = 0; i < this.coefficients.length; i++) {
            x.coefficients[i] += this.coefficients[i];
         }
         return new Polynomial(x.coefficients);
      }
   }
 
public double evaluate(double value) {
   double result = 0;
   for (int i = 0; i < coefficients.length; i++){
      result = result + this.coefficients[i] * Math.pow(value,i);
   }
   return result;
}

public boolean hasRoot(double x) {
   return evaluate(x) == 0;
}  
}