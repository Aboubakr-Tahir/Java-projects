import java.util.Arrays;
import java.util.Scanner;
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class scanningFile {
    public static void main(String[] args){ 

        String[] words;
        int index = 0 ; 
        int nombreDeMots = 0 ;
        File fichier = new File("c:\\Users\\hp\\Desktop\\javaScrapingExercice.txt");


        try(Scanner input = new Scanner(fichier)){
            while(input.hasNextLine()){
                String ligne = input.nextLine(); 
                String[] mots = ligne.split("\\s+");
                nombreDeMots += mots.length ;
            }
            System.out.println("Le nombre de mots dans cette fichier est : "+nombreDeMots); 
            input.close();
        }catch(FileNotFoundException e){
            System.out.println(" File could not be processed no1");
        }

        words = new String[nombreDeMots]; 
        
        
        try(Scanner input = new Scanner(fichier)){
            while(input.hasNext()){
                String mot = input.next();
                words[index]=mot;
                index++;
            }
            Arrays.sort(words); 
            input.close();
        }catch(FileNotFoundException e){
            System.out.println(" File could not be processed no2");
        }

        try(PrintWriter output = new PrintWriter("c:\\Users\\hp\\Desktop\\AGD-PSR32.txt")){
            output.println(Arrays.toString(words)); 
            System.out.println("Les mots ont été triées et enregistrer dans le fichier.");
        }catch(FileNotFoundException e){
            System.out.println(" File could not be processed no3"); 
        }

    }
}

