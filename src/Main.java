import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 3. feladat
        List<Versenyzo> versenyzoList = new ArrayList<>();
        File inputFile = new File("bukkm2019.txt");
        try (Scanner scanner = new Scanner(inputFile)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String aktualisSor = scanner.nextLine();
                versenyzoList.add(new Versenyzo(aktualisSor));
            }
        } catch (FileNotFoundException exception) {
            System.err.print("Fájl nem található!");
            return;
        }

        //4.feladat
        double szazalekertek = (1 - ((double) versenyzoList.size() / 691)) * 100;
        System.out.println("4. feladat: A versenytávot nem teljesítők: " + szazalekertek + "%");

        //5.feladat
        int noiRovidDarabszam = 0;
        for (Versenyzo versenyzo : versenyzoList) {
            if (versenyzo.isNo() && versenyzo.getVersenytav().equals("Rövid")) {
                noiRovidDarabszam++;
            }
        }
        System.out.println("5. feladat: Női versenyzők száma a rövid távú versenyen: " + noiRovidDarabszam + "fő");

        // 6. Feladat
        boolean volt = false;
        for (Versenyzo versenyzo : versenyzoList) {
            if (versenyzo.getOrakSzama() >= 6) {
                volt = true;
                break;
            }
        }
        String eredmenySzoveg = volt ? "6. feladat: Volt ilyen versenyző " : "6. feladat: Nem volt ilyen versenyző";
        System.out.println(eredmenySzoveg);

    }
}
class Versenytav {
    private String rajtSzam;

    public String getTav() {
        switch (rajtSzam.charAt(0)) {
            case 'M':
                return "Mini";
            case 'R':
                return "Rövid";
            case 'K':
                return "Közép";
            case 'H':
                return "Hosszú";
            case 'E':
                return "Pedelec";
            default:
                return "Hibás rajtszám";
        }
    }

    public Versenytav(String rajtszam) {
        rajtSzam = rajtszam;
    }
}

class Versenyzo {
    private String rajtszam;
    private String kategoria;
    private Versenytav versenytav;
    private String nev;
    private String egyesulet;
    private String ido;

    public String getVersenytav() {
        return versenytav.getTav();
    }

    public boolean isNo() {
        return kategoria.charAt(kategoria.length() - 1) == 'n';
    }

    public int getOrakSzama() {
        String oraString = ido.split(":")[0];
        return Integer.parseInt(oraString);
    }

    public int getOsszIdoMasodpercben() {
        String[] idoString = ido.split(":");
        int ora = Integer.parseInt(idoString[0]);
        int perc = Integer.parseInt(idoString[1]);
        int masodperc = Integer.parseInt(idoString[2]);

        return ora * 60 * 60 + perc * 60 + masodperc;
    }

    public String getKategoria() {
        return kategoria;
    }

    public String getRajtszam() {
        return rajtszam;
    }

    public String getNev() {
        return nev;
    }

    public String getEgyesulet() {
        return egyesulet;
    }

    public String getIdo() {
        return ido;
    }

    Versenyzo(String sor) {
        String[] darabolt = sor.split(";");
        rajtszam = darabolt[0];
        versenytav = new Versenytav(darabolt[0]);
        kategoria = darabolt[1];
        nev = darabolt[2];
        egyesulet = darabolt[3];
        ido = darabolt[4];
    }
}