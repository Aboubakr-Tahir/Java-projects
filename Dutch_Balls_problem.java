

public class Dutch_Balls_problem {
    public static void main (String args[]){
        System.out.println(Dutch_solution(new char[]{'W' ,'R' ,'B' ,'B' ,'W','B','R'})) ;
    }
    public static char[] Dutch_solution(char[] tab){
        int mid = 0 ;
        int high = tab.length - 1 ;
        int low = 0 ; 
        char inter ;
        while (mid <= high){
            if (tab[mid] == 'B'){
                inter = tab[mid] ; 
                tab[mid]=tab[low] ;
                tab[low]=inter ; 
                low ++ ;
                mid ++ ;
            }
            else if(tab[mid]=='W'){
                mid ++ ;
            }
            else if(tab[mid]=='R'){
                inter = tab[mid] ;
                tab[mid] = tab[high] ;
                tab[high] = inter ; 
                high -- ;
            }
        }
        return tab ;
    }
    } 

