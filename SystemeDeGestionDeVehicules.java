public class SystemeDeGestionDeVehicules {
    public static void main(String[] args){
        voiture voiture1 = new voiture("toyota", "Corolla", 2020, 4) ;
        Moto Moto1 = new Moto("Yamaha","R1",2018,"Sport");
        voiture1.afficherDetails(); 
        Moto1.afficherDetails();
    }
}

class Vehicules {
    String marque ;
    String modele  ;
    int annee ;

    public Vehicules (String marque , String modele , int annee){
        this.marque = marque ;
        this.modele = modele ;
        this.annee = annee ;
    }

    void afficherDetails (){
        System.out.println("Vehicule : Marque - "+this.marque+", Modele - "+this.modele+", Annee -"+this.annee);
    }
}

class voiture extends Vehicules {
    int nombreDePortes ;
    
    public voiture (String marque , String modele , int annee ,int nombreDePortes){
        super(marque, modele ,annee) ; 
        this.nombreDePortes = nombreDePortes ;
    }


    @Override 
    void afficherDetails() {
        System.out.println("Voiture : Marque - "+this.marque+", Modele - "+this.modele+", Annee -"+this.annee+", Portes -"+this.nombreDePortes);
    }
}

class Moto extends Vehicules {
    String typeDeMoto ;

    public Moto (String marque , String modele , int annee,String typeDeMoto){
        super(marque , modele , annee) ;
        this.typeDeMoto = typeDeMoto ;
    }

    @Override
    void afficherDetails(){
        System.out.println("Moto : Marque - "+this.marque+", Modele - "+this.modele+", Annee -"+this.annee+", Type -"+this.typeDeMoto);
    }
}