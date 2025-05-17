public class Employe extends Personne {
    public String code;
    public double salaire;

    public Employe(String nom, String prenom, Adresse adresse, String code, double salaire) {
        super(nom, prenom, adresse);
        this.code = code;
        this.salaire = salaire;
    }

}
