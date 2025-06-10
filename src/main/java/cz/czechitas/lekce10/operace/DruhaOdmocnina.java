package cz.czechitas.lekce10.operace;

/**
 * Operace druhé odmocniny.
 *
 * @author Filip Jirsák
 */
public class DruhaOdmocnina implements Operace {
    private int a;

    @Override
    public void setA(int a) {
        OperaceHelper.validovatOperand(a);
        if (a < 0) {
            throw new IllegalArgumentException("Druhá odmocnina není definována pro záporná čísla.");
        }
        this.a = a;
    }

    @Override
    public int getA() {
        return a;
    }

    @Override
    public void setB(int b) {
        throw new UnsupportedOperationException("Druhá odmocnina má jen jeden operand.");
    }

    @Override
    public int getB() {
        throw new UnsupportedOperationException("Druhá odmocnina má jen jeden operand.");
    }

    @Override
    public int getPocetOperandu() {
        return 1;
    }

    @Override
    public String vypocet() {
        int vysledek = (int) Math.sqrt(a);
        return "√%d = %d".formatted(a, vysledek);
    }
}
