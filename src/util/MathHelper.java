package util;

public class MathHelper {
	public static double[] resolve(double a, double b, double c) {
		if (a == 0) {
			double result[] = { -c / b };
			return result;
		}
			
		double aux = b * b - 4 * a * c;

		if (aux < 0) {
			double result[] = {};
			return result;
		}

		// REFERENCIAS:
		// https://people.csail.mit.edu/bkph/Quadratics.pdf
		// e aulas de metodos numericos
		aux = Math.sqrt(aux);

		if (b < 0)
			aux -= b;
		else
			aux = - (aux + b);

		double result[] = { aux / 2*a, 2*c / aux };

		return result;
	}
}
