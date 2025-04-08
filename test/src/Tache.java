public class Tache {
    protected String code;
    protected int nombre;

    public Tache(String c, int n) {
        this.code = c;
        this.nombre = n;
    }

    public Tache() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Tache tache = (Tache) obj;

        if (this.code == null) {
            return tache.code == null;
        }

        return this.code.equals(tache.code);
    }

    public Tache(Tache tache) {
        this.code = tache.code;
        this.nombre = tache.nombre;
    }
}
