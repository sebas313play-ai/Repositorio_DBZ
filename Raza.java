public enum Raza {
    SAIYAJIN(1.2),
    TERRICOLA(1.0),
    NAMEKIANO(1.1),
    ANDROIDE(1.15);

    private final double bonoDanio;

    Raza(double bonoDanio) {
        this.bonoDanio = bonoDanio;
    }

    public double getBonoDanio() {
        return bonoDanio;
    }
}