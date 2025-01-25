public class GestionDesStatusDeCommande {
    public static void main (String[] args){
        Commande macommande = new Commande(1, StatutCommande.EN_ATTENTE);
        macommande.afficherStatut();
        macommande.changerStatut(StatutCommande.EXPEDIEE) ;
        macommande.afficherStatut();
        macommande.changerStatut(StatutCommande.ANNULEE);
        macommande.afficherStatut();
    }
}

enum StatutCommande {
    EN_ATTENTE("votre commande est en attente"),
    TRAITEMENT("votre commande est en cours de preparation"),
    EXPEDIEE("votre commande est expediee"),
    LIVREE("votre commande a ete livre"),
    ANNULEE("votre commande a ete annulee");

    private String descritpion ;

    private StatutCommande(String description){
        this.descritpion = description ;
    }

    void afficherMessage(){
        System.out.println("statut : "+descritpion); 
    }
}

class Commande {
    int id ; 
    StatutCommande statut ;

    public Commande (int id , StatutCommande statut){
        this.id = id ;
        this.statut = statut ;
    } 

    StatutCommande changerStatut(StatutCommande nouveauStatut){
        statut = nouveauStatut ;
        return statut ;
    }

    void afficherStatut(){
        System.out.println("Commande ID : "+this.id+" Statut : ") ;
        statut.afficherMessage();
    }
}
