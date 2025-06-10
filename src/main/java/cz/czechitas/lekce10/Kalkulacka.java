package cz.czechitas.lekce10;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.czechitas.lekce10.operace.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

/**
 * Hlavní třída aplikace Kalkulačka.
 */
public class Kalkulacka implements Runnable {

    private final Scanner console = new Scanner(System.in);
    private final Map<String, Operace> mapaOperaci;
    private Operace zvolenaOperace;

    public static void main(String[] args) {
        new Kalkulacka().run();
    }

    /**
     * Vytvoří kalkulačku a přidá známé operace.
     */
    public Kalkulacka() {
        Konstanty konstanty = nacistKonstanty();
        mapaOperaci = Map.of(
                "+", new Scitani(),
                "-", new Odcitani(),
                "*", new Nasobeni(),
                "/", new Deleni(),
                "odmocnit", new DruhaOdmocnina(),
                "pi", new CisloPi(konstanty.getPi()),
                "e", new CisloE(konstanty.getE())
        );
    }

    /**
     * Spustí kalkulačku a vyzve uživatele k zadání údajů.
     */
    @Override
    public void run() {
        nacistOperaciOpakovane();
        if (zvolenaOperace.getPocetOperandu() >= 1) {
            nacistPrvniCisloOpakovane();
        }
        if (zvolenaOperace.getPocetOperandu() >= 2) {
            nacistDruheCisloCisloOpakovane();
        }
        vypsatVysledek();
    }

    /**
     * Vypíše prompt a načte následný vstup od uživatele.
     *
     * @param format Formátovací řetězec pro {@link String#format(String, Object...)}.
     * @param args   Argumenty pro dosazení do formátovacího řetězce.
     * @return Vstup od uživatele.
     */
    private String nacistUdaj(String format, Object... args) {
        return nacistUdaj(String.format(format, args));
    }

    /**
     * Vypíše prompt a načte následný vstup od uživatele.
     *
     * @param prompt Výzva uživateli, co má zadat.
     * @return Vstup od uživatele.
     */
    private String nacistUdaj(String prompt) {
        System.out.println(prompt);
        System.out.print("> ");
        return console.nextLine();
    }

    /**
     * Načte od uživatele operaci, kterou chce provést.
     */
    private void nacistOperaci() {
        String seznamOperaci = String.join(", ", mapaOperaci.keySet());
        String vstup = nacistUdaj("Zadejte operaci (%s) a stiskněte <Enter>:", seznamOperaci);
        zvolenaOperace = mapaOperaci.get(vstup);
    }

    /**
     * Načte od uživatele operaci, kterou chce provést. Pokud uživatel zadá neexistující operaci, vyzve ho k opětovnému zadání.
     */
    private void nacistOperaciOpakovane() {
        for (int i = 0; i < 3; i++) {
            try {
                nacistOperaci();
                return;
            } catch (IllegalArgumentException e) {
                System.err.println("Zadána neplatná operace.");
            }
        }
        ukoncitAplikaci();
    }

    /**
     * Načte od uživatele první číslo (první operand).
     */
    private void nacistPrvniCislo() {
        String vstup = nacistUdaj("Zadejte první (celé) číslo a stiskněte <Enter>:");
        int cislo = Integer.parseInt(vstup);
        zvolenaOperace.setA(cislo);
    }

    /**
     * Načte od uživatele první číslo (první operand). Pokud uživatel zadá chybný vstup, vyzve ho k opětovnému zadání.
     */
    private void nacistPrvniCisloOpakovane() {
        for (int i = 0; i < 3; i++) {
            try {
                nacistPrvniCislo();
                return;
            } catch (IllegalArgumentException e) {
                System.err.println("Zadáno neplatné číslo.");
            }
        }
        ukoncitAplikaci();
    }

    /**
     * Načte od uživatele druhé číslo (druhý operand).
     */
    private void nacistDruheCisloCislo() {
        String vstup = nacistUdaj("Zadejte druhé (celé) číslo a stiskněte <Enter>:");
        int cislo = Integer.parseInt(vstup);
        zvolenaOperace.setB(cislo);
    }

    /**
     * Načte od uživatele druhé číslo (druhý operand). Pokud uživatel zadá chybný vstup, vyzve ho k opětovnému zadání.
     */
    private void nacistDruheCisloCisloOpakovane() {
        for (int i = 0; i < 3; i++) {
            try {
                nacistDruheCisloCislo();
                return;
            } catch (IllegalArgumentException e) {
                System.err.println("Zadáno neplatné číslo.");
            }
        }
        ukoncitAplikaci();
    }

    /**
     * Provede výpočet a vypíše výsledek operace včetně celého výpočtu.
     */
    private void vypsatVysledek() {
        System.out.printf("Výpočet: %s", zvolenaOperace.vypocet()).println();
    }

    private void ukoncitAplikaci() {
        System.err.println("Příliš mnoho neplatných pokusů o zadání vstupu, končím aplikaci.");
        System.exit(1);
    }

    /**
     * Načte konstanty pro kalkulačku z JSON souboru.
     *
     * @return Objekt s načtenými konstantami.
     */
    private static Konstanty nacistKonstanty() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = Kalkulacka.class.getResourceAsStream("konstanty.json")) {
            return objectMapper.readValue(inputStream, Konstanty.class);
        } catch (IOException e) {
            throw new RuntimeException("Nepodařilo se načíst konstanty.", e);
        }
    }

    /**
     * Načte konstanty pro kalkulačku z JSON souboru.
     * <p>
     * Použit je alternativní zápis pomocí try-finally.
     *
     * @return Objekt s načtenými konstantami.
     */
    private static Konstanty nacistKonstantyStarsi() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Alternativní zápis pomocí try-finally
        try {
            InputStream inputStream = Kalkulacka.class.getResourceAsStream("konstanty.json");
            try {
                return objectMapper.readValue(inputStream, Konstanty.class);
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Nepodařilo se načíst konstanty.", e);
        }
    }

}
