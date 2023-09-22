public class Polynomial{
   double[] coefficients;
 
   public Polynomial(){
      coefficients = new double[]{0};
   }
 
   public Polynomial(double[] coefficients){
      this.coefficients = coefficients;
   }
 
   public Polynomial add(Polynomial x) {
      int newlength = Math.max(this.coefficients.length, x.coefficients.length);
      double[] newcoe = new double[newlength];
      if (this.coefficients.length >= x.coefficients.length) {
         for(int i = 0; i < x.coefficients.length; i++) {
            newcoe[i] = this.coefficients[i] + x.coefficients[i];
         }
         for(int j = x.coefficients.length; j < this.coefficients.length; j++)
         {
            newcoe[j] = this.coefficients[j];
         }
      }
      else{
         for(int i = 0; i < this.coefficients.length; i++) {
            newcoe[i] = x.coefficients[i] + this.coefficients[i];
         }
         for(int j = this.coefficients.length; j < x.coefficients.length; j++)
         {
            newcoe[j] = x.coefficients[j];
         }
      }
      return new Polynomial(newcoe);
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