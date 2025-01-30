import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionListeDeTaches {
    public static void main (String[] args){
        ArrayList <String> List = new ArrayList<>() ;
        int choix , index ;
        Scanner scanner = new Scanner(System.in) ;
        String tache ;
        do {
            System.out.println("=== Menu de Gestion de Tâches ===\r\n" + //
                                "1. Ajouter une tache\r\n" + //
                                "2. Afficher les taches\r\n" + //
                                "3. Supprimer une tache\r\n" + //
                                "4. Marquer une tache comme terminee\r\n" + //
                                "5. Afficher le nombre total de taches\r\n" + //
                                "6. Quitter\r\n" + //
                                "Choisissez une option : ");
            choix = scanner.nextInt() ;
            scanner.nextLine();
            switch(choix){
                case 1 : 
                    System.out.println("Entrer la tache que vous voudrez ajouter :");
                    tache = scanner.nextLine();
                    List.add(tache) ;
                    break;
                case 2 :
                    System.out.println("l'ensemble des taches : ");   
                    afficheTache(List) ; 
                    break ;
                case 3 :
                    if(List.isEmpty()){
                        System.out.println("La liste des taches est vide");
                    }
                    System.out.println("Entrer le nombre de la tache que vous voudrez supprimer : ");
                    index = scanner.nextInt() ;
                    List.remove(index); 
                    break ;
                case 4 : 
                    System.out.println("Entrer le nombre de la tache que vous avez termine :");
                    index = scanner.nextInt() ;
                    List.set(index, List.get(index)+"-Terminee");
                    break ;
                case 5 :
                    System.out.println("Le nombre total de taches est :"+List.size());
                    break;
                case 6 :
                    System.out.println("au revoir");
                    break ; 
                default : 
                    System.out.println("le choix est invalide , entrer une option valide");
                    break ;           
            }
                                
        } while(choix != 6);
        scanner.close() ;
    }

    public static void afficheTache(ArrayList Liste){
        if (Liste.isEmpty()){
            System.out.println("Liste vide");
        }
        for(int i=0 ; i<Liste.size(); i++){
            System.out.println(i+1+"-"+Liste.get(i));
        }
    }
    
}
