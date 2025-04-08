public class Realisation extends Tache {
    protected String ouvrier;

    public Realisation(String ouvrier, String code, int nombre) {
        super(code, nombre);
        this.ouvrier = ouvrier;
    }

    public Realisation(Realisation realisation) {
        this.ouvrier = realisation.ouvrier;
        this.code = realisation.code;
        this.nombre = realisation.nombre;
    }

}
