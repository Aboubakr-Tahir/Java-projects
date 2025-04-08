public class RRealisation extends DoubleListe {
    public RRealisation() {
        super();
    }

    public RRealisation(RRealisation realisation) {
        this.tete = realisation.tete;
        this.taille = realisation.taille;
    }

    public boolean ajouter(Realisation r) {
        Noeud nouveau = new Noeud(r);
        if (taille <= 0) {
            this.tete.suivant = nouveau;
            nouveau.precedent = this.tete;
            taille++;
            return true;
        }
        Noeud courant = this.tete.suivant;
        Noeud precedent = this.tete;
        while (courant != null) {
            Realisation realisation = (Realisation) courant.element;
            if (realisation.ouvrier.equals(r.ouvrier) && realisation.code.equals(r.code)) {
                realisation.nombre += r.nombre;
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

    public String[] getOuvriers() {
        if (taille <= 0) {
            return null;
        }

        Noeud courant = this.tete.suivant;
        int maxOuvriers = 20;
        int nbOuvriers = 0;
        String[] ouvriers = new String[maxOuvriers];
        while (courant != null) {
            Realisation realisation = (Realisation) courant.element;
            boolean found = false;
            for (int i = 0; i < nbOuvriers; i++) {
                if (realisation.ouvrier.equals(ouvriers[i])) {
                    found = true;
                }
            }
            if (!found && nbOuvriers < maxOuvriers) {
                ouvriers[nbOuvriers] = realisation.ouvrier;
                nbOuvriers++;
            }
            courant = courant.suivant;
        }
        String[] final_ouvriers = new String[nbOuvriers];
        for (int i = 0; i < nbOuvriers; i++) {
            final_ouvriers[i] = ouvriers[i];
        }
        return final_ouvriers;
    }

    public int nbPieceOne(String ouvrier) {
        if (taille <= 0) {
            return 0;
        }
        Noeud courant = this.tete.suivant;
        int nbPieces = 0;
        while (courant != null) {
            Realisation realisation = (Realisation) courant.element;
            if (realisation.ouvrier.equals(ouvrier)) {
                nbPieces += realisation.nombre;
            }
            courant = courant.suivant;
        }
        return nbPieces;

    }

    public void nbPieceParOuvrier() {
        if (taille <= 0) {
            return;
        }
        String[] ouvriers = this.getOuvriers();
        for (int i = 0; i < ouvriers.length; i++) {
            System.out.println(
                    "L'ouvrier: " + ouvriers[i] + " a fourni un nombre de piece egale a: " + nbPieceOne(ouvriers[i]));
        }
    }

    public void afficher() {
        if (taille > 0) {
            Noeud courant = this.tete.suivant;
            String result = "";
            while (courant != null) {
                Realisation realisation = (Realisation) courant.element;
                result += "(" + realisation.ouvrier + " , " + realisation.code + " , " + realisation.nombre + ") ";
                if (courant.suivant != null) {
                    result += "-> ";
                }
                courant = courant.suivant;
            }
            System.out.println(result);
        }

    }
}
