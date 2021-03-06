package cit590;

public class Rational {

	// Declare instance variables here
	private int numerator;

	private int denominator;

	public Rational(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	/**
	 * Given a "p/q" string make the rational number
	 * 
	 * @param s
	 */
	public Rational(String pSlashQ) {
		// see if there is a slash
		if (pSlashQ.contains("/")) {
			String[] s = pSlashQ.split("/");
			int numerator = Integer.parseInt(s[0]);
			int denominator = Integer.parseInt(s[1]);
			this.numerator = numerator;
			this.denominator = denominator;
		}
		else{
			// see if this is a number
			try{
				int num = Integer.parseInt(pSlashQ);
				this.numerator = num;
				this.denominator = 1;
			}
			catch (NumberFormatException e){
				System.out.println("random bad input. try again");
			}
		}
	}

	public String toString() {
		if (this.getNumerator() == 0) {
			return "0";
		}
		if (this.getDenominator() == 1) {
			return "" + this.getNumerator();
		}
		return this.getNumerator() + "/" + this.getDenominator();
	}

	public Rational reduceToLowestTerms() {
		if (numerator == 0) {
			this.numerator = 0;
			this.denominator = 1;
		}
		// the highest possible common factor is the min of the numerator and
		// the denominator
		int maxPossibleFactor = Math.min(numerator, denominator);
		for (int i = 2; i <= maxPossibleFactor; i++) {
			while ((numerator % i == 0) && (denominator % i == 0)) {
				this.numerator = this.numerator / i;
				this.denominator = this.denominator / i;
			}
		}
		if ((this.numerator < 0) && (this.denominator < 0)) {
			this.numerator = this.numerator * (-1);
			this.denominator = this.denominator * (-1);
		}
		return this;
	}

	public Rational add(Rational f) {
		// actual solution begins here
		int denominator = f.denominator * this.denominator;
		int numerator = f.numerator * this.denominator + this.numerator
				* f.denominator;
		return new Rational(numerator, denominator).reduceToLowestTerms();
	}

	public Rational multiply(Rational f) {
		// actual solution begins here
		int denominator = f.denominator * this.denominator;
		int numerator = f.numerator * this.numerator;
		return new Rational(numerator, denominator).reduceToLowestTerms();
	}

	public boolean equals(Object arg0) {
		Rational r = (Rational) arg0;
		return (this.numerator == r.numerator)
				&& (this.denominator == r.denominator);
	}

	public double getDecimal() {
		return (1.0 * numerator) / denominator;
	}

	public int getNumerator() {
		return numerator;
	}

	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}
	
	public static void main(String[] args) {
		Rational r = new Rational("1/3");
		System.out.println(" NUM " + r.getNumerator());
		System.out.println(" den " + r.getDenominator());
		Rational r1 = new Rational("2");
		System.out.println("den " + r1.getDenominator());
	}
}
