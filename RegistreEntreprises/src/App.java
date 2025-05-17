import java.io.File;
import java.util.List;

import javax.sql.rowset.Predicate;

public class App {
    public static void main(String[] args) throws Exception {
        File f = new File("fichier.txt");
        Adresse adresse = new Adresse(2017, "7aye l9assam", "Casablanca");
        Employe aboubakr = new Employe("Aboubakr", "Tahir", adresse, "2647", 8000);
        Registre registre = new Registre();
        registre.ajouter(aboubakr);
        Adresse adresse1 = new Adresse(2018, "7aye sa5ra lk7la", "Casablanca");
        Employe younes = new Employe("youness", "lyazidi", adresse1, "2847", 6000);
        registre.ajouter(younes);
        Adresse adresse2 = new Adresse(405, "Avenue Hassan II", "Rabat");
        Employe sarah = new Employe("Benmoussa", "Sarah", adresse2, "3012", 9500);
        registre.ajouter(sarah);
        Adresse adresse3 = new Adresse(78, "Rue Ibn Sina", "Marrakech");
        Employe karim = new Employe("Alami", "Karim", adresse3, "1590", 7200);
        registre.ajouter(karim);
        Adresse adresse4 = new Adresse(1245, "Boulevard Mohammed V", "Tanger");
        Employe nadia = new Employe("Chaoui", "Nadia", adresse4, "2105", 8200);
        registre.ajouter(nadia);
        Adresse adresse5 = new Adresse(67, "Rue Oujda", "Fès");
        Employe jamal = new Employe("Bakkali", "Jamal", adresse5, "4230", 6500);
        registre.ajouter(jamal);
        // registre.trierSalaire();
        // registre.trierNom();
        // List<Employe> habitants = registre.selectionnerVille("Casablanca");
        // for (Employe habitant : habitants) {
        // System.out.println(
        // habitant.nom + " " + habitant.prenom + " a le code : " + habitant.code + " et
        // le salaire : "
        // + habitant.salaire);
        // }
        // registre.stockerFichier(f);
        // registre.afficher();
        Registre nouvuea = registre.filtrer(e1 -> e1.salaire > 7000);
        nouvuea.afficher();
    }
}
