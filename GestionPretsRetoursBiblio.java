
public class GestionPretsRetoursBiblio {
    public static void main(String[] args){
        Bibliotheque bibliotheque = new Bibliotheque(5, 10);

        Livre livre1 = new Livre("L001", "Le Petit Prince");
        Livre livre2 = new Livre("L002", "1984");

        bibliotheque.ajouterLivre(livre1);
        bibliotheque.ajouterLivre(livre2); 

        Client client1 = new Client("C001", "Alice");
        Client client2 = new Client("C002", "Bob");

        bibliotheque.ajouterClient(client1);
        bibliotheque.ajouterClient(client2);

        bibliotheque.traiterEmprunt("L001", "C001");

        bibliotheque.traiterEmprunt("L002", "C001");
        bibliotheque.traiterEmprunt("L002", "C001");

        bibliotheque.traiterRetour("L001", "C001");
        client1.getListBooks();
    }
}

class Livre {
    private String codeLivre ; 
    private String name;
    private boolean disponible = true; 

    public Livre(String codeLivre , String name){
        this.codeLivre = codeLivre;
        this.name=name;
    } 

    public boolean estDisponible(){
        return disponible;
    }

    public String getCodeLivre(){
        return this.codeLivre;
    }

    public boolean setDisponible(boolean value){
        this.disponible = value ;
        return this.disponible;
    }

    public String getName(){
        return this.name  ;
    }

    public String toString() {
        return "Livre [codeLivre=" + codeLivre + ", titre=" + name + ", disponible=" + disponible + "]";
    }
} 

class Client{
    private String codeClient ;
    private String nom;
    private boolean LATE=false;
    private int maxLivres = 3 ;
    private int nombreEmpruntLivre = 0 ;
    private Livre[] listeLivresEmprunt = new Livre[maxLivres];

    public Client(String codeClient , String nom){
        this.codeClient = codeClient;
        this.nom = nom ; 
    }

    public String getClientCode(){
        return this.codeClient;
    }

    public String getClientName(){
        return this.nom ;
    }

    public int getNumbersBooks(){
        return nombreEmpruntLivre;
    }

    public Boolean estValid(){
        if ((LATE == false) && (nombreEmpruntLivre<maxLivres)){
            return true;
        }
        else {
            return false;
        }
    }

    public void getListBooks(){
        System.out.println("Livres empruntés par " + nom + ":");
        for (Livre element : listeLivresEmprunt){
            if(element != null){
                System.out.println(element);
            }
        }
    }

    public Boolean isLate(){
        return this.LATE ;
    } 

    public Boolean checkBook (Livre Book){
        for (Livre element : listeLivresEmprunt){
            if ((element!=null) && (element.getCodeLivre().equals(Book.getCodeLivre())) ){
                return true;
            }
        } 
        return false;
        }
      

    public Livre retournerLivre(Livre Book){ 
        if (checkBook(Book)== false){
            System.out.println("le livre n existe pas");
            return null;
        }
        else {
            Livre temp;
            for (int i =0 ; i<maxLivres ; i++){
                if (listeLivresEmprunt[i]!=null && listeLivresEmprunt[i].getCodeLivre().equals(Book.getCodeLivre())){
                    Book.setDisponible(true);
                    temp = listeLivresEmprunt[i];
                    listeLivresEmprunt[i]=null; 
                    nombreEmpruntLivre--;
                    return temp;
                }
            }
        }
        return null;
    }
    public Livre emprunter(Livre Book){
        if(estValid() && Book.estDisponible()){
            for(int i=0 ; i<maxLivres ; i++){
                if(listeLivresEmprunt[i]==null){
                    listeLivresEmprunt[i]=Book ; 
                    nombreEmpruntLivre++;
                    Book.setDisponible(false); 
                    return listeLivresEmprunt[i];
                }
            }
        }
        else {
            return null ;
        }
        return null;
    } 
}
    
class Bibliotheque {
    private int nombreClients=0 ;
    private int nombreLivres=0 ; 
    private Client[] listClients ; 
    private Livre[] listLivres ;

    public Bibliotheque(int maxClients , int maxLivres){
        listClients = new Client[maxClients];
        listLivres = new Livre[maxLivres];  
    }

    public void ajouterLivre (Livre Book){
        if (nombreLivres < listLivres.length){
            for(int i=0 ; i<listLivres.length;i++){
                if(listLivres[i]==null){
                    listLivres[i]=Book ; 
                    nombreLivres++; 
                    return;
                }
            }
        }
        else{
            System.out.println("taille du catalogue de livres plein");
        }
    }

    public void ajouterClient (Client customer){
        if (nombreClients<listClients.length){
            for(int i=0;i<listClients.length;i++){
                if(listClients[i]==null){
                    listClients[i]=customer;
                    nombreClients++;
                    return;
                }
            }
        }
        else{
            System.out.println("taille du catalogue de clients plein");
        }
    }

    public Livre trouverLivre(String codeLivre){
        for(Livre element : listLivres){
                if(element!=null && element.getCodeLivre().equals(codeLivre)){
                    return element;
                }
            }
            return null ; 
    }

    public Client trouverClient(String codeClient){
        for (Client element : listClients){
            if(element != null){
                if (element.getClientCode().equals(codeClient)){
                    return element ;
                }
                }
            }
            return null ;
    }

    public void traiterEmprunt(String codeLivre , String codeClient){

        Livre Book = trouverLivre(codeLivre); 
        if(Book == null || Book.estDisponible() == false){
            System.out.println("Le livre n'existe pas ou n'est pas disponible !");
        }
        Client customer = trouverClient(codeClient);
        if(customer.estValid() == false || customer==null){
            System.out.println("Le client n'existe pas ou n'est pas valide !");
        }
        customer.emprunter(Book);  
        Book.setDisponible(false);
        System.out.println("Emprunt a ete efectue avec succes");

    }

    public void traiterRetour(String codeLivre , String codeClient){

        Livre Book = trouverLivre(codeLivre); 
        if(Book == null || Book.estDisponible() == false){
            System.out.println("Le livre n'existe pas ou n'est pas disponible !");
        }
        Client customer = trouverClient(codeClient);
        if(customer==null){
            System.out.println("Le client n'existe pas ou n'est pas valide !");
        }

        customer.retournerLivre(Book);
        Book.setDisponible(true);
        System.out.println("Retour effectué avec succès !");
    }
}    
