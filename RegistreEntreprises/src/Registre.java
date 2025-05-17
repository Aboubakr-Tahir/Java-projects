import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.Comparator;

public class Registre {
    List<Employe> employes;

    public Registre() {
        employes = new LinkedList<>();
    }

    public void ajouter(Employe employe) {
        employes.add(employe);
    }

    public void supprimer(String code) {
        for (int i = 0; i < employes.size(); i++) {
            if (employes.get(i).code.equals(code)) {
                employes.remove(i);
            }
        }
    }

    public void trierSalaire() {
        Collections.sort(employes, new Comparator<Employe>() {
            @Override
            public int compare(Employe e1, Employe e2) {
                return Double.compare(e1.salaire, e2.salaire);
            }
        });
    }

    public void trierNom() {
        Collections.sort(employes, new Comparator<Employe>() {
            @Override
            public int compare(Employe e1, Employe e2) {
                return e1.nom.compareTo(e2.nom);
            }
        });
    }

    public void stockerFichier(File f) throws Exception {
        PrintWriter writer = new PrintWriter(new FileWriter(f, false));
        for (Employe employe : employes) {
            writer.println(employe.code + "/" + employe.nom + "/" + employe.prenom + "/" + employe.adresse.numero + "_"
                    + employe.adresse.rue + "_" + employe.adresse.ville + "/" + employe.salaire);
        }
        writer.close();
    }

    public List<Employe> selectionnerVille(String ville) {
        List<Employe> habitants = new LinkedList<>();
        for (int i = 0; i < employes.size(); i++) {
            if (employes.get(i).adresse.ville.equals(ville)) {
                habitants.add(employes.get(i));
            }
        }
        return habitants;
    }

    public Registre filtrer(Predicate<Employe> p) {
        Registre nouveau = new Registre();
        for (Employe employe : employes) {
            if (p.test(employe)) {
                nouveau.ajouter(employe);
            }
        }
        return nouveau;
    }

    public void afficher() {
        for (Employe employe : employes) {
            System.out.println(employe.nom + " " + employe.prenom + " a le code : " + employe.code + " et le salaire : "
                    + employe.salaire);
        }
    }

}
