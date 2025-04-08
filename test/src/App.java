public class App {
    public static void main(String[] args) throws Exception {
        Realisation r1 = new Realisation("01", "X7", 12);
        Realisation r2 = new Realisation("02", "X5", 15);
        Realisation r3 = new Realisation("03", "X4", 35);
        Realisation r4 = new Realisation("03", "X4", 15);
        Realisation r5 = new Realisation("02", "X1", 15);
        Realisation r6 = new Realisation("09", "X1", 15);
        Realisation r7 = new Realisation("09", "X4", 12);

        RRealisation liste = new RRealisation();
        liste.ajouter(r1);
        liste.ajouter(r2);
        liste.ajouter(r3);
        liste.ajouter(r4);
        liste.ajouter(r5);
        liste.ajouter(r6);
        liste.ajouter(r7);
        liste.afficher();
        liste.nbPieceParOuvrier();
    }
}
