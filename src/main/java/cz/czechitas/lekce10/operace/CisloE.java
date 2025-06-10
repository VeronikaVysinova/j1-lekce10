package cz.czechitas.lekce10.operace;

/**
 * Operace vracející konstantu e – Eulerova konstanta.
 *
 * @author Filip Jirsák
 */
public class CisloE implements Operace {
    private final String cisloE;

    public CisloE(double cisloE) {
        this.cisloE = Double.toString(cisloE);
    }

    @Override
    public void setA(int a) {
        throw new UnsupportedOperationException("Konstanta nemá žádné operandy.");
    }

    @Override
    public int getA() {
        throw new UnsupportedOperationException("Konstanta nemá žádné operandy.");
    }

    @Override
    public void setB(int b) {
        throw new UnsupportedOperationException("Konstanta nemá žádné operandy.");
    }

    @Override
    public int getB() {
        throw new UnsupportedOperationException("Konstanta nemá žádné operandy.");
    }

    @Override
    public int getPocetOperandu() {
        return 0;
    }

    @Override
    public String vypocet() {
        return "e = %s".formatted(cisloE);
    }
}
