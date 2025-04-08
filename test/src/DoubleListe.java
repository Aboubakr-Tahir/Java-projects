public class DoubleListe {
    class Noeud {
        protected Object element;
        protected Noeud suivant;
        protected Noeud precedent;

        public Noeud() {
            this.suivant = null;
            this.precedent = null;
        }

        public Noeud(Object element) {
            this();
            this.element = element;
        }

        public void setElement(Object nouveau) {
            this.element = nouveau;
        }
    }

    protected Noeud tete;
    protected int taille;

    public DoubleListe() {
        this.tete = new Noeud();
        this.taille = 0;
    }

    public boolean ajouterFin(Object element) {
        Noeud nouveau = new Noeud(element);
        if (taille <= 0) {
            this.tete.suivant = nouveau;
            nouveau.precedent = this.tete;
            taille++;
            return true;
        }
        Noeud courant = this.tete.suivant;
        Noeud precedent = this.tete;
        while (courant != null) {
            precedent = courant;
            courant = courant.suivant;
        }
        precedent.suivant = nouveau;
        nouveau.precedent = precedent;
        taille++;
        return true;
    }

    public void afficher() {
        if (taille > 0) {
            Noeud courant = this.tete.suivant;
            String result = "";
            while (courant != null) {
                result += courant.element + " ";
                if (courant.suivant != null) {
                    result += "-> ";
                }
                courant = courant.suivant;
            }
            System.out.println(result);
        }

    }
}
