import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Personaje goku = new Personaje("Goku", 600, 150, Raza.SAIYAJIN);
        Personaje vegeta = new Personaje("Vegeta", 500, 140, Raza.SAIYAJIN);
        Personaje freezer = new Personaje("Freezer", 800, 120, Raza.TERRICOLA);

        ArrayList<Personaje> personajes = new ArrayList<>();
        personajes.add(goku);
        personajes.add(vegeta);
        personajes.add(freezer);

        System.out.println("===== Combate: Goku vs Vegeta =====");

        Personaje atacante = goku;
        Personaje objetivo = vegeta;

        for (int turno = 1; turno <= 10 && goku.estaVivo() && vegeta.estaVivo(); turno++) {
            System.out.println("\n--- Turno " + turno + " ---");

            if (turno % 3 == 0) {
                atacante.lanzarKamehameha(objetivo);
            } else {
                atacante.atacar(objetivo);
            }

            if (!objetivo.estaVivo()) {
                System.out.println(objetivo.getNombre() + " ha sido derrotado");
                break;
            }

            if (turno == 2 && goku.estaVivo()) {
                System.out.println("Goku come una semilla del ermitaño y se regenera.");
                goku.curar(100);
                goku.mostrarEstado();
            }

            Personaje temp = atacante;
            atacante = objetivo;
            objetivo = temp;
        }

        System.out.println("\nSupervivientes tras el combate: " + contarVivos(personajes));

        System.out.println("\n===== Pelea final: Freezer vs superviviente =====");
        Personaje superviviente = goku.estaVivo() ? goku : vegeta;
        if (superviviente.getRaza() == Raza.SAIYAJIN) {
            superviviente.superSaiyajin();
        }
        pelear(freezer, superviviente);

        System.out.println("\nSupervivientes al final: " + contarVivos(personajes));
    }

    public static void pelear(Personaje a, Personaje b) {
        System.out.println(a.getNombre() + " vs " + b.getNombre() + " - ¡comienza la pelea!");

        int turno = 1;
        while (a.estaVivo() && b.estaVivo()) {
            Personaje atacante = (turno % 2 == 1) ? a : b;
            Personaje objetivo = (atacante == a) ? b : a;

            System.out.println("\n--- Turno " + turno + " ---");

            if (turno % 3 == 0) {
                if (atacante.puedeLanzarKamehameha()) {
                    atacante.lanzarKamehameha(objetivo);
                } else {
                    atacante.concentrarse();
                }
            } else {
                atacante.atacar(objetivo);
            }

            if (!objetivo.estaVivo()) {
                System.out.println(objetivo.getNombre() + " ha sido derrotado");
            }
            turno++;
        }

        Personaje ganador = a.estaVivo() ? a : b;
        System.out.println("\n¡" + ganador.getNombre() + " gana la pelea!");
    }

    public static int contarVivos(ArrayList<Personaje> lista) {
        int vivos = 0;
        for (Personaje p : lista) {
            if (p.estaVivo()) {
                vivos++;
            }
        }
        return vivos;
    }
}