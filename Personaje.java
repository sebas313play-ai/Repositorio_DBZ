public class Personaje {

    private static final int KI_MAXIMO = 100;
    private static final int COSTO_KAMEHAMEHA = 40;
    private static final int COSTO_SUPER_SAIYAJIN = 50;
    private static final int KI_CONCENTRACION = 20;

    private final String nombre;
    private int vida;
    private final int vidaMaxima;
    private int poderDePelea;
    private int ki;
    private final Raza raza;
    private boolean transformado = false;

    public Personaje(String nombre, int vidaMaxima, int poderDePelea) {
        this(nombre, vidaMaxima, poderDePelea, Raza.TERRICOLA);
    }

    public Personaje(String nombre, int vidaMaxima, int poderDePelea, Raza raza) {
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.vida = vidaMaxima;
        this.poderDePelea = poderDePelea;
        this.ki = KI_MAXIMO;
        this.raza = raza;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getPoderDePelea() {
        return poderDePelea;
    }

    public int getKi() {
        return ki;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setVida(int vida) {
        if (vida < 0) {
            this.vida = 0;
        } else if (vida > vidaMaxima) {
            this.vida = vidaMaxima;
        } else {
            this.vida = vida;
        }
    }

    private void setKi(int ki) {
        this.ki = Math.max(0, Math.min(KI_MAXIMO, ki));
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public boolean puedeLanzarKamehameha() {
        return ki >= COSTO_KAMEHAMEHA;
    }

    public void recibirDanio(int danio) {
        if (danio < 0) {
            return;
        }
        setVida(vida - danio);
    }

    public void curar(int cantidad) {
        if (!estaVivo() || cantidad <= 0) {
            return;
        }
        setVida(vida + cantidad);
    }

    public void atacar(Personaje objetivo) {
        if (!estaVivo()) {
            System.out.println(nombre + " está muerto y no puede atacar.");
            return;
        }
        if (objetivo == null || objetivo == this) {
            System.out.println(nombre + " no puede atacarse a sí mismo.");
            return;
        }

        int danio = calcularDanio(poderDePelea);
        System.out.println(nombre + " ataca a " + objetivo.getNombre() + " con " + danio + " de daño");
        objetivo.recibirDanio(danio);
        objetivo.mostrarEstado();
    }

    public void lanzarKamehameha(Personaje objetivo) {
        if (!estaVivo()) {
            System.out.println(nombre + " está muerto y no puede atacar.");
            return;
        }
        if (objetivo == null || objetivo == this) {
            System.out.println(nombre + " no puede atacarse a sí mismo.");
            return;
        }
        if (!puedeLanzarKamehameha()) {
            System.out.println(nombre + " no tiene ki suficiente para el Kamehameha (ki actual: " + ki + ").");
            return;
        }

        setKi(ki - COSTO_KAMEHAMEHA);
        int danio = calcularDanio(poderDePelea * 3);
        System.out.println(nombre + " lanza un Kamehameha a " + objetivo.getNombre()
                + " con " + danio + " de daño (-" + COSTO_KAMEHAMEHA + " ki)");
        objetivo.recibirDanio(danio);
        objetivo.mostrarEstado();
    }

    public void superSaiyajin() {
        if (!estaVivo()) {
            return;
        }
        if (transformado) {
            System.out.println(nombre + " ya está en su forma Super Saiyajin.");
            return;
        }
        if (ki < COSTO_SUPER_SAIYAJIN) {
            System.out.println(nombre + " no tiene ki suficiente para transformarse.");
            return;
        }
        setKi(ki - COSTO_SUPER_SAIYAJIN);
        poderDePelea *= 2;
        transformado = true;
        System.out.println("¡" + nombre + " se transforma en Super Saiyajin! Poder: " + poderDePelea);
    }

    public void concentrarse() {
        if (!estaVivo()) {
            return;
        }
        setKi(ki + KI_CONCENTRACION);
        System.out.println(nombre + " se concentra y recupera " + KI_CONCENTRACION + " de ki.");
    }

    public void mostrarEstado() {
        System.out.println(nombre + " -> Vida: " + vida + "/" + vidaMaxima + " | Ki: " + ki);
    }

    private int calcularDanio(int danioBase) {
        return (int) Math.round(danioBase * raza.getBonoDanio());
    }
}