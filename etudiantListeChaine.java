import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class etudiantListeChaine {
    public static void main(String[] args) {
        Matrice matrice = new Matrice();
        Matrice matrice2 = new Matrice();
        matrice.ajouterFin(2, 7, 2.9);
        matrice2.ajouterFin(2, 5, 3.5);
        matrice2.ajouterFin(3, 5, 4.5);
        matrice2.ajouterFin(3, 9, 7.2);
        matrice.ajouterFin(3, 9, 5);
        matrice.ajouterFin(2, 5, 7.5);
        Matrice result = matrice.calculerSomme(matrice2);
        result.afficher();
    }
}

class Noeud {
    private Object element;
    private Noeud suivant;

    public Noeud(Object element) {
        this.element = element;
        this.suivant = null;
    }

    public void setSuivant(Noeud next) {
        this.suivant = next;
    }

    public Noeud getSuivant() {
        return this.suivant;
    }

    public Object getElement() {
        return this.element;
    }
}

class Liste {
    protected Noeud head;
    protected int taille;

    public Liste() {
        this.head = null;
        this.taille = 0;
    }

    public int getTaille() {
        return this.taille;
    }

    public void ajouterTete(Object element) {
        Noeud nouveau = new Noeud(element);
        if (this.head == null) {
            this.head = nouveau;
        } else {
            nouveau.setSuivant(this.head);
            this.head = nouveau;
        }
        taille++;
    }

    public void ajouterPosition(Object element, int Pos) {
        if (Pos > taille + 1) {
            System.out.println("Position entered out of index");
            return;
        }

        if (this.head == null) {
            System.out.println("La liste est vide");
            return;
        }

        if (Pos == 1) {
            ajouterTete(element);
            return;
        } else {
            int i = 1;

            Noeud current = this.head;
            while (current != null && i < Pos - 1) {
                current = current.getSuivant();
                i++;
            }
            Noeud nouveau = new Noeud(element);
            nouveau.setSuivant(current.getSuivant());
            current.setSuivant(nouveau);
        }
        taille++;
    }

    public void supprimerPos(int Pos) {
        if (this.head == null) {
            System.out.println("La liste est vide");
            return;
        }
        if (Pos > taille) {
            System.out.println("Position entered out of indexe");
            return;
        }

        Noeud precedent = null;
        Noeud current = this.head;
        int i = 1;
        while (current != null && i < Pos) {
            precedent = current;
            current = current.getSuivant();
            i++;
        }

        if (Pos == 1) {
            this.head = current.getSuivant();
        } else {
            precedent.setSuivant(current.getSuivant());
        }
        current = null;
        taille--;
    }

    public Noeud getHead() {
        return this.head;
    }

    public void supprimerElement(Object element) {
        Noeud current = this.head;
        Noeud precedent = null;

        if (this.head == null) {
            System.out.println("La liste est vide");
            return;
        }

        while (current != null && !current.getElement().equals(element)) {
            precedent = current;
            current = current.getSuivant();
        }

        if (current == null) {
            System.out.println("l element n existe pas dans la liste pour etre supprimer");
            return;
        }

        if (current == this.head) {
            this.head = current.getSuivant();
        } else {
            precedent.setSuivant(current.getSuivant());
        }
        current = null;
        taille--;
    }

    public int chercherElement(Object element) {
        int i = 1;
        Noeud current = this.head;
        while (current != null && !current.getElement().equals(element)) {
            current = current.getSuivant();
            i++;
        }
        if (current == null) {
            System.out.println("L element n existe pas dans la liste");
            return -1;
        }
        return i;
    }

    public void afficher() {
        Noeud current = this.head;
        String results = "";
        int i = 1;
        while (current != null) {
            results += i + ". " + current.getElement().toString();
            if (current.getSuivant() != null) {
                results += " -> ";
            }
            current = current.getSuivant();
            i++;
        }
        System.out.println(results);
    }
}

class Ensemble extends Liste {
    public Ensemble() {
        super();
    }

    public boolean isExist(Object element) {
        if (this.getHead() == null) {
            return false;
        }

        Noeud current = this.getHead();
        while (current != null && !current.getElement().equals(element)) {
            current = current.getSuivant();
        }

        return current != null;

    }

    @Override
    public void ajouterTete(Object element) {
        if (this.isExist(element)) {
            System.out.println("Element deja existe , impossible de l ajouter");
            return;
        }
        super.ajouterTete(element);
    }

    @Override
    public void ajouterPosition(Object element, int Pos) {
        if (this.isExist(element)) {
            System.out.println("Element deja existe , impossible de l ajouter");
            return;
        }
        super.ajouterPosition(element, Pos);
    }

    public boolean appartient(Object element) {
        return this.isExist(element);
    }

    public boolean estInclut(Ensemble ens) {
        Noeud currentThis = this.getHead();
        while (currentThis != null) {
            Noeud currentOther = ens.getHead();
            while (currentOther != null && !currentThis.getElement().equals(currentOther.getElement())) {
                currentOther = currentOther.getSuivant();
            }
            if (currentOther == null) {
                return false;
            }
            currentThis = currentThis.getSuivant();
        }
        return true;
    }

    public Ensemble intersection(Ensemble ens) {
        Noeud currentThis = this.getHead();
        Ensemble result = new Ensemble();
        while (currentThis != null) {
            if (ens.isExist(currentThis.getElement())) {
                result.ajouterTete(currentThis.getElement());
            }
            currentThis = currentThis.getSuivant();
        }
        return result;
    }

    public Ensemble reunion(Ensemble ens) {
        Ensemble result = new Ensemble();
        Noeud currentThis = this.getHead();
        Noeud currentOther = ens.getHead();
        while (currentThis != null) {
            result.ajouterTete(currentThis.getElement());
            currentThis = currentThis.getSuivant();
        }
        while (currentOther != null) {
            result.ajouterTete(currentOther.getElement());
            currentOther = currentOther.getSuivant();
        }
        return result;
    }

    public Ensemble difference(Ensemble ens) {
        Ensemble result = new Ensemble();
        Noeud currentThis = this.getHead();
        while (currentThis != null) {
            if (!ens.isExist(currentThis.getElement())) {
                result.ajouterTete(currentThis.getElement());
            }
            currentThis = currentThis.getSuivant();
        }
        return result;
    }

    public Ensemble diffSymetric(Ensemble ens) {
        Ensemble result = new Ensemble();
        Ensemble temp = new Ensemble();
        temp = this.difference(ens);
        Noeud currentTemp = temp.getHead();
        while (currentTemp != null) {
            result.ajouterTete(currentTemp.getElement());
            currentTemp = currentTemp.getSuivant();
        }
        temp = ens.difference(this);
        currentTemp = temp.getHead();
        while (currentTemp != null) {
            result.ajouterTete(currentTemp.getElement());
            currentTemp = currentTemp.getSuivant();
        }
        return result;
    }

}

class Etudiant {
    private int mat;
    private double[] notes;
    private int taille = 0;

    public Etudiant(int mat) {
        this.mat = mat;
        this.notes = new double[3];
    }

    public int getMat() {
        return this.mat;
    }

    public double[] getNotes() {
        return this.notes;
    }

    public void setMat(int mat) {
        this.mat = mat;
    }

    public void ajouterNote(double note) {
        if (taille >= notes.length) {
            System.out.println("Le tableau de notes est plein");
            return;
        }
        notes[taille] = note;
        taille++;
    }

    public int getTaille() {
        return this.taille;
    }

    public void initialiserNotes(double note1, double note2, double note3) {
        if (this.taille > 0) {
            System.out.println(
                    "le tableau deja a une note , impossible de l initialiser , utiliser la fonction ajouterNote ou Remplacer/SupprimerNote");
            return;
        }
        this.ajouterNote(note1);
        this.ajouterNote(note2);
        this.ajouterNote(note3);

    }

    // Poisition ici 1-based (comme tout le programme , je veux comme un utilisatuer
    // l'utiliser pas un programmeur , il ne comprends pas 0-based indexes)
    public double supprimerNote(int Pos) {
        if (this.taille == 0) {
            System.out.println("Le tableau deja vide , rien a supprimer");
            return -1;
        }
        double temp = notes[Pos - 1];
        notes[Pos - 1] = 0.0;
        taille--;
        for (int i = Pos - 1; i < notes.length - 1; i++) {
            notes[i] = notes[i + 1];
        }
        notes[notes.length - 1] = 0.0;
        return temp;
    }

    public void modifierNote(int Pos, double note) {
        if (Pos < 1 || Pos > notes.length) {
            System.out.println("Position out of index");
            return;
        }
        if (Pos > taille) {
            System.out.println("cette position est null maintenant , utiliser ajouterNote");
            return;
        }
        notes[Pos - 1] = note;
    }

    public double getMoyenne() {
        return (notes[0] + notes[1] + notes[2]) / 3;
    }

}

class Etudiants extends Liste {
    public Etudiants() {
        super();
    }

    public boolean isExist(Object element) {
        if (this.getHead() == null) {
            return false;
        }
        Etudiant etudiant = (Etudiant) element;
        return this.rechercheEtudiant(etudiant.getMat()) != null;
    }

    @Override
    public void ajouterTete(Object element) {
        if (isExist(element)) {
            System.out.println("L etudiant deja existe");
            return;
        }
        super.ajouterTete(element);
    }

    @Override
    public void ajouterPosition(Object element, int Pos) {
        if (isExist(element)) {
            System.out.println("L etudiant deja existe");
            return;
        }
        super.ajouterPosition(element, Pos);
    }

    public Etudiant rechercheEtudiant(int matricule) {
        if (this.getHead() == null) {
            return null;
        }
        Noeud current = this.getHead();
        while (current != null) {
            Etudiant currentEtudiant = (Etudiant) current.getElement();
            if (currentEtudiant.getMat() == matricule) {
                return currentEtudiant;
            }
            current = current.getSuivant();
        }
        return null;
    }

    public void chargerDepuisFichier(String nomFichier) {
        try {
            File fichier = new File(nomFichier);
            Scanner scanner = new Scanner(fichier);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String[] elements = ligne.split(" ");

                if (elements.length >= 4) {
                    int matricule = Integer.parseInt(elements[0]);
                    double note1 = Double.parseDouble(elements[1]);
                    double note2 = Double.parseDouble(elements[2]);
                    double note3 = Double.parseDouble(elements[3]);

                    Etudiant etudiant = new Etudiant(matricule);
                    etudiant.initialiserNotes(note1, note2, note3);

                    this.ajouterTete(etudiant);
                }
            }

            scanner.close();
            System.out.println("Donnees chargees depuis " + nomFichier);
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé: " + e.getMessage());
        }
    }

    public void sauvergarderVersFichier(String nomFichier) {
        try {
            File fichier = new File(nomFichier);
            FileWriter writer = new FileWriter(fichier);
            Noeud current = this.getHead();
            while (current != null) {
                Etudiant etudiant = (Etudiant) current.getElement();
                writer.write(Integer.toString(etudiant.getMat()));
                for (int i = 0; i < etudiant.getNotes().length; i++) {
                    writer.write(" " + etudiant.getNotes()[i]);
                }
                writer.write("\n");
                current = current.getSuivant();
            }
            writer.close();
            System.out.println("Donnes sauvegardees dans " + nomFichier);
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouver");
        } catch (IOException e) {
            System.out.println("Erreur d'écriture dans le fichier: " + e.getMessage());
        }
    }

    public int nombreEtudiant() {
        Noeud current = this.getHead();
        int i = 0;
        while (current != null) {
            i++;
            current = current.getSuivant();
        }
        return i;
    }

    public double[] afficherOrdreMoyenne() {
        if (this.getHead() == null) {
            System.out.println("La liste est empty");
        }
        double[] moyennes = new double[this.nombreEtudiant()];
        int i = 0;
        Noeud current = this.getHead();
        while (current != null) {
            Etudiant etudiant = (Etudiant) current.getElement();
            moyennes[i] = etudiant.getMoyenne();
            i++;
            current = current.getSuivant();
        }
        for (int z = 0; z < moyennes.length - 1; z++) {
            for (int k = 0; k < moyennes.length - z - 1; k++) {
                if (moyennes[k] > moyennes[k + 1]) {
                    double temp = moyennes[k];
                    moyennes[k] = moyennes[k + 1];
                    moyennes[k + 1] = temp;
                }
            }
        }
        return moyennes;
    }

    @Override
    public void afficher() {
        Noeud current = this.getHead();
        String results = "";
        while (current != null) {
            Etudiant e = (Etudiant) current.getElement();
            results += e.getMat() + " a les notes : ";
            for (int i = 0; i < e.getTaille(); i++) {
                results += e.getNotes()[i] + " ";

            }
            if (current.getSuivant() != null) {
                results += " -> ";
            }
            current = current.getSuivant();
        }
        System.out.println(results);
    }

}

class Element {
    private int ligne;
    private int colone;
    private double valeur;
    Element suivant;

    public Element(int ligne, int colone, double valeur) {
        this.ligne = ligne;
        this.colone = colone;
        this.valeur = valeur;
    }

    public int getLigne() {
        return this.ligne;
    }

    public int getColone() {
        return this.colone;
    }

    public double getValeur() {
        return this.valeur;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setColone(int colone) {
        this.colone = colone;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

}

class Matrice extends Liste {
    public Matrice() {
        super();
    }

    public boolean ajouterFin(int ligne, int colone, double valeur) {
        Element element = new Element(ligne, colone, valeur);
        Noeud nouveau = new Noeud(element);
        if (this.getHead() == null) {
            this.head = nouveau;
            this.taille++;
            return true;
        }
        Noeud current = this.getHead();
        Noeud precedent = this.getHead();
        while (current != null) {
            precedent = current;
            Element currElement = (Element) current.getElement();
            if (currElement.getColone() == colone && currElement.getLigne() == ligne) {
                currElement.setValeur(valeur);
                return true;
            }
            current = current.getSuivant();
        }
        precedent.setSuivant(nouveau);
        this.taille++;
        return true;
    }

    public boolean supprimerIndice(int ligne, int colone) {
        if (taille == 0) {
            return false;
        }
        Noeud current = this.getHead();
        Element tete = (Element) current.getElement();
        if (tete.getColone() == colone && tete.getLigne() == ligne) {
            this.head = this.head.getSuivant();
            taille--;
            return true;
        }
        Noeud precedent = this.getHead();
        while (current != null) {
            Element currElement = (Element) current.getElement();
            if (currElement.getColone() == colone && currElement.getLigne() == ligne) {
                precedent.setSuivant(current.getSuivant());
                current.setSuivant(null);
                taille--;
                return true;
            }
            precedent = current;
            current = current.getSuivant();
        }
        return false;
    }

    public Element getElement(int ligne, int colone) {
        if (taille == 0) {
            return null;
        }
        Noeud current = this.head;
        while (current != null) {
            Element currElement = (Element) current.getElement();
            if (currElement.getColone() == colone && currElement.getLigne() == ligne) {
                return currElement;
            }
            current = current.getSuivant();
        }
        return null;
    }

    public Matrice calculerSomme(Matrice matrice) {
        Matrice result = new Matrice();
        if (this.taille == 0 || matrice.taille == 0) {
            return null;
        }
        Noeud currentThis = this.head;
        while (currentThis != null) {
            Element currElementThis = (Element) currentThis.getElement();
            result.ajouterFin(currElementThis.getLigne(), currElementThis.getColone(), currElementThis.getValeur());
            currentThis = currentThis.getSuivant();
        }
        Noeud currentOther = matrice.head;
        while (currentOther != null) {
            Element currentElementOther = (Element) currentOther.getElement();
            result.ajouterFin(currentElementOther.getLigne(), currentElementOther.getColone(),
                    currentElementOther.getValeur());
            currentOther = currentOther.getSuivant();
        }
        currentThis = this.head;
        while (currentThis != null) {
            Element currElementThis = (Element) currentThis.getElement();
            if (matrice.getElement(currElementThis.getLigne(), currElementThis.getColone()) != null) {
                result.ajouterFin(currElementThis.getLigne(), currElementThis.getColone(), currElementThis.getValeur()
                        + matrice.getElement(currElementThis.getLigne(), currElementThis.getColone()).getValeur());
            }
            currentThis = currentThis.getSuivant();
        }
        return result;
    }

    public void afficher() {
        if (this.taille != 0) {
            Noeud current = this.getHead();
            String result = "";
            while (current != null) {
                Element currElement = (Element) current.getElement();
                result += "(" + currElement.getLigne() + "," + currElement.getColone() + "," + currElement.getValeur()
                        + ") ";
                if (current.getSuivant() != null) {
                    result += " -> ";
                }
                current = current.getSuivant();
            }
            System.out.println(result);
        }
    }
}
