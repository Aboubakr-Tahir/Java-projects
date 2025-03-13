

public class carnetAdresse{
    public static void main(String[] args){
        Carnet carnet = new Carnet(4);  
        Adresse a1 = new Adresse("Casablanca", 205);
        Personne p1 = new Personne("Aboubakr", "Tahir", a1);
        Element e = new Element(p1, a1);
        carnet.ajouterElement(e); 
        Adresse a2 = new Adresse("Rabat", 205);
        Personne p2 = new Personne("Zohra", "Tahir", a2);
        Element e2 = new Element(p2, a2);
        carnet.ajouterElement(e2); 
        Adresse a3 = new Adresse("Berrechid", 205);
        Personne p3 = new Personne("Zineb", "m3rtch", a3);
        Element e3 = new Element(p3, a3);
        carnet.ajouterElement(e3);  
        Adresse a4 = new Adresse("Settat", 205);
        Personne p4 = new Personne("Boubker", "Naki", a4);
        Element e4 = new Element(p4, a4);
        carnet.ajouterElement(e4);  
        carnet.trier();
        carnet.afficheElements();
    }
}

class Adresse{
    private String ville;
    private int zip;
    
    public Adresse(String ville , int zip){
        this.ville = ville ;
        this.zip=zip ;
    }

    public String getVille(){
        return this.ville;
    }

    public String setVille(String ville){
        this.ville=ville;
        return this.ville;
    }

    public int getZip(){
        return this.zip;
    }

    public int setZip(int zip){
        this.zip=zip;
        return this.zip;
    }
}

class Personne {
    private String nom ; 
    private String prenom;
    private Adresse adresse;

    public Personne (String nom , String prenom , Adresse adresse){
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
    }

    public String getNom(){return this.nom;}
    public String setNom(String nom){this.nom=nom; return this.nom;}
    public String getPrenom(){return this.prenom;}
    public String setPrenom(String prenom){this.prenom=prenom;return this.prenom;}
    public Adresse getAdresse(){return this.adresse;}
    public Adresse setAdresse(Adresse adresse){this.adresse=adresse;return this.adresse;}
}

class Element{
    private Personne personne ; 

    public Element(Personne personne , Adresse adresse){
        this.personne = personne ; 
        this.personne.setAdresse(adresse);
    } 

    public Personne getPersonne(){
        return this.personne;
    }
    public Personne setPersonne(Personne personne){this.personne=personne; return this.personne;} 

    public Adresse getAdresse(){return this.personne.getAdresse();}
    
    public Adresse setAdresse(Adresse adresse){
        this.personne.setAdresse(adresse);
        return this.personne.getAdresse(); 
    }
}

class Carnet{ 
    private Element[] carnet ;
    private int maxDimension;
    private int taille = 0;

    public Carnet(int maxDimension){
        this.maxDimension=maxDimension; 
        this.carnet= new Element[maxDimension];
    } 

    public int getLenght(){
        return carnet.length; 
    } 

    public int getNumberElements(){
        return this.taille;
    }

    public boolean ajouterElement(Element element){
        if(this.taille < this.maxDimension){
            carnet[taille]=element;
            taille++; 
            return true;
        }
        else{
            return false;
        }
    } 

    public boolean supprimerElement(String nom){
        for(int j=0 ; j<taille ; j++){
            if(carnet[j].getPersonne().getNom().equalsIgnoreCase(nom)){ 
                for (int i=j ; i<taille-1;i++){
                    carnet[i]=carnet[i+1];
                }
                carnet[taille-1]=null;
                taille--; 
                return true; 
            }
        }
        return false;
    }  

    public Element rechercher(String nom){
        for(int i=0 ; i<taille ; i++){
            if(carnet[i].getPersonne().getNom().equalsIgnoreCase(nom)){
                return carnet[i];
            }
        }
        return null; 
    }

    public void trier(){ 
        Element temp;
        for(int i=0 ; i<taille-1 ; i++){
            for(int j=0 ; j<taille-1-i ; j++){
                if(carnet[j].getPersonne().getNom().compareToIgnoreCase(carnet[j+1].getPersonne().getNom())>0){
                    temp=carnet[j];
                    carnet[j]=carnet[j+1];
                    carnet[j+1]=temp;
                }
            }
        }
    }

    public void afficheElements(){
        for(int i=0 ; i<taille;i++){
                Personne p = carnet[i].getPersonne();
                System.out.println("Nom: "+p.getNom()+", Prenom: "+p.getPrenom()); 
                System.out.println("-------------------");
        } 
    }
}