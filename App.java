import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Repertoire repertoire = new Repertoire(3);
        repertoire.ajouter("Aboubakr", 612685418);
        repertoire.ajouter("Aboubaks", 644885601);
        repertoire.ajouter("Aboubaks", 18188);
        repertoire.ajouter("Younes", 15748252);
        repertoire.ajouter("badr", 515115151);
        repertoire.ajouter("khadija", 656555);
        repertoire.afficher();
        for (Repertoire.Rinfo element : repertoire.toList()) {
            System.out.println("--------------------");
            System.out.println(element.nom + " " + element.numeros);
        }

    }

}

class Repertoire implements Cloneable {
    public compartiment[] compartiments;
    int remplis;

    public Repertoire(int M) {
        compartiments = new compartiment[M];
        remplis = 0;
        for (int i = 0; i < compartiments.length; i++) {
            compartiments[i] = new compartiment();
        }
    }

    class compartiment {
        public LinkedList<Rinfo> Rinfos;

        public compartiment() {
            Rinfos = new LinkedList<>();
        }

    }

    class Rinfo implements Comparable<Rinfo> {
        public String nom;
        public ArrayList<Integer> numeros;

        public Rinfo(String nom, int num) {
            this.nom = nom;
            numeros = new ArrayList<>();
            numeros.add(num);
        }

        @Override
        public int compareTo(Rinfo autre) {
            return this.nom.compareTo(autre.nom);
        }
    }

    public int h(String s) {
        return s.hashCode() / compartiments.length;
    }

    public int[] rechercher(String nom) {
        int[] resultat = new int[2];
        for (int i = 0; i < compartiments.length; i++) {
            for (int j = 0; j < compartiments[i].Rinfos.size(); j++) {
                if (compartiments[i].Rinfos.get(j).nom.equals(nom)) {
                    resultat[0] = i;
                    resultat[1] = j;
                    return resultat;
                }
            }
        }
        resultat[0] = -1;
        resultat[1] = -1;
        return resultat;
    }

    public int[] ishashCode(String nom) {
        int[] resultat = new int[2];
        for (int i = 0; i < compartiments.length; i++) {
            for (int j = 0; j < compartiments[i].Rinfos.size(); j++) {
                if (h(compartiments[i].Rinfos.get(j).nom) == h(nom)) {
                    resultat[0] = i;
                    resultat[1] = j;
                    return resultat;
                }
            }
        }
        resultat[0] = -1;
        resultat[1] = -1;
        return resultat;
    }

    public void trierCompartiments() {
        for (int i = 0; i < compartiments.length - 1; i++) {
            for (int j = 0; j < compartiments.length - 1 - i; j++) {
                if (!compartiments[j].Rinfos.isEmpty() && !compartiments[j + 1].Rinfos.isEmpty()
                        && h(compartiments[j].Rinfos.get(0).nom) < h(compartiments[j + 1].Rinfos.get(0).nom)) {
                    compartiment temp = compartiments[j];
                    compartiments[j] = compartiments[j + 1];
                    compartiments[j + 1] = temp;
                }
            }
        }
    }

    public int compartimentVide() {
        for (int i = 0; i < compartiments.length; i++) {
            if (compartiments[i].Rinfos.isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    public void ajouter(String nom, int num) {
        int[] recherche = rechercher(nom);
        int[] hshage = ishashCode(nom);
        if (recherche[0] != -1 && recherche[1] != -1) {
            compartiments[recherche[0]].Rinfos.get(recherche[1]).numeros.add(num);
            trierCompartiments();
            return;
        } else if (hshage[0] != -1 && hshage[1] != -1) {
            compartiments[hshage[0]].Rinfos.add(new Rinfo(nom, num));
            trierCompartiments();
            return;
        } else {
            if (remplis < compartiments.length) {
                int indice = compartimentVide();
                if (indice != -1) {
                    compartiments[indice].Rinfos.add(new Rinfo(nom, num));
                    remplis++;
                    trierCompartiments();
                    return;
                }
            }
        }
    }

    public void supprimer(String nom, int num) {
        int[] recherche = rechercher(nom);
        if (recherche[0] != -1 && recherche[1] != -1) {
            Rinfo contactActuel = compartiments[recherche[0]].Rinfos.get(recherche[1]);
            if (contactActuel.numeros.contains(num)) {
                contactActuel.numeros.remove((Integer) num);
                if (contactActuel.numeros.isEmpty()) {
                    compartiments[recherche[0]].Rinfos.remove((Rinfo) contactActuel);
                    if (compartiments[recherche[0]].Rinfos.isEmpty()) {
                        remplis--;
                    }
                }
            }
        }
        trierCompartiments();
    }

    public void afficher() {
        String resultat = "Nombre de compartiments : " + compartiments.length + "\n";
        for (int i = 0; i < compartiments.length; i++) {
            resultat += "----------------------------------------\n";
            resultat += "Compartiment numero : " + (i + 1) + "\n";
            for (int j = 0; j < compartiments[i].Rinfos.size(); j++) {
                resultat += "ID : " + j + " ,Nom : " + compartiments[i].Rinfos.get(j).nom + " ,Numeros : "
                        + compartiments[i].Rinfos.get(j).numeros + "\n";
                resultat += "----------------------------------------\n";
            }
        }
        System.out.println(resultat);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Repertoire copie = (Repertoire) super.clone();
        copie.compartiments = new compartiment[this.compartiments.length];
        for (int i = 0; i < copie.compartiments.length; i++) {
            copie.compartiments[i] = new compartiment();
            for (Rinfo infoOriginal : this.compartiments[i].Rinfos) {
                Rinfo infoClone = new Rinfo(infoOriginal.nom, 0);
                infoClone.numeros.clear();
                for (int num : infoOriginal.numeros) {
                    infoClone.numeros.add(num);
                }
                copie.compartiments[i].Rinfos.add(infoClone);
            }
        }
        return copie;
    }

    public List<Rinfo> toList() throws CloneNotSupportedException {
        Repertoire copie = (Repertoire) this.clone();
        List<Rinfo> rinfosList = new ArrayList<>();
        for (int i = 0; i < copie.compartiments.length; i++) {
            for (int j = 0; j < copie.compartiments[i].Rinfos.size(); j++) {
                Rinfo infoActuel = copie.compartiments[i].Rinfos.get(j);
                rinfosList.add(infoActuel);
            }
        }
        Collections.sort(rinfosList);
        return rinfosList;
    }
}
