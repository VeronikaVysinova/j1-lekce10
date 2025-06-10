package cz.czechitas.lekce10.operace;

/**
 * Pomocné metody pro matematické operace na kalkulačce.
 *
 * @author Filip Jirsák
 */
public class OperaceHelper {
    /**
     * První kladné číslo, které na displeji nelze zobrazit (má 9 číslic).
     */
    private static final int limitVelikostiDispleje = 100_000_000;

    public static void validovatOperand(int cislo) {
        if (cislo >= limitVelikostiDispleje) {
            throw new IllegalArgumentException("Zadané číslo %d je příliš velké.".formatted(cislo));
        }
        if (cislo <= -limitVelikostiDispleje) {
            throw new IllegalArgumentException("Zadané číslo %d je příliš malé.".formatted(cislo));
        }
    }

    public static void validovatVysledek(long vysledek) {
        if (vysledek >= limitVelikostiDispleje || vysledek <= -limitVelikostiDispleje) {
            throw new ArithmeticException("Výsledek operace %d se nevejde na displej.".formatted(vysledek));
        }
    }

    public static void validovatVysledek(int vysledek) {
        if (vysledek >= limitVelikostiDispleje || vysledek <= -limitVelikostiDispleje) {
            throw new ArithmeticException("Výsledek operace %d se nevejde na displej.".formatted(vysledek));
        }
    }
}
