public class RTaches extends DoubleListe {
    public boolean ajouter(Tache e) {
        Noeud nouveau = new Noeud(e);
        if (this.taille <= 0) {
            this.tete.suivant = nouveau;
            nouveau.precedent = this.tete;
            taille++;
            return true;
        }
        Noeud courant = this.tete.suivant;
        Noeud precedent = this.tete;
        while (courant != null) {
            Tache tache = (Tache) courant.element;
            if (tache.code.equals(e.code)) {
                tache.nombre += e.nombre;
                return true;
            }
            precedent = courant;
            courant = courant.suivant;
        }
        precedent.suivant = nouveau;
        nouveau.precedent = precedent;
        taille++;
        return true;
    }

    public String piecePlusCommandee() {
        if (taille <= 0) {
            return "";
        }
        Noeud courant = this.tete.suivant;
        Tache max = (Tache) courant.element;
        while (courant != null) {
            Tache tache = (Tache) courant.element;
            if (tache.nombre > max.nombre) {
                max = tache;
            }
            courant = courant.suivant;
        }
        return max.code;
    }

    public void afficher() {
        if (taille > 0) {
            Noeud courant = this.tete.suivant;
            String result = "";
            while (courant != null) {
                Tache tache = (Tache) courant.element;
                result += "(" + tache.code + " , " + tache.nombre + ") ";
                if (courant.suivant != null) {
                    result += "-> ";
                }
                courant = courant.suivant;
            }
            System.out.println(result);
        }

    }
}
