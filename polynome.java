public class polynome {
    public static void main(String[] args){
        Monome m1 = new Monome(2, 5);
        Element element1 = new Element(m1);
        Monome m2 = new Monome(1, 2);
        Element element2 = new Element(m2);
        Monome m3 = new Monome(0, 1);
        Element element3 = new Element(m3); 
        Polynomelist p1 = new Polynomelist();

        Monome n1 = new Monome(2, 3);
        Element e1 = new Element(n1);
        Monome n2 = new Monome(1, 1);
        Element e2 = new Element(n2);
        Monome n3 = new Monome(0, 5);
        Element e3 = new Element(n3); 
        Polynomelist p2 = new Polynomelist();

        p1.addMonome(m1);
        p1.addMonome(m2);
        p1.addMonome(m3);
        p1.afficher(); 
        
        p2.addMonome(n1);
        p2.addMonome(n2);
        p2.addMonome(n3);
        p2.afficher(); 

        
        System.out.println("After multiplying : ");

        p1=p1.produit(p2);
        p1.afficher();

    }
}

class Monome{
    int deg ; 
    double coeff ;

    public Monome(int deg , double coeff){
        this.deg=deg;
        this.coeff=coeff;
    }

    public int getDeg(){
        return this.deg ;
    }

    public double getCoeff(){
        return this.coeff ;
    }

    public void setDeg(int deg){
        this.deg=deg ;
    }
    
    public void setCoeff(double coeff){
        this.coeff = coeff ; 
    } 
}

class Element{
    Monome monome ; 
    Element next ; 

    public Element(Monome monome){
        this.monome=monome ; 
        this.next=null ; 
    }

    public int getMonomeDeg(){
        return this.monome.getDeg();
    }

    public double getMonomeCoeff(){
        return this.monome.getCoeff();
    }

    public void setMonomeDeg(int deg){
        this.monome.setDeg(deg);
    }

    public void setMonomeCoeff(double coeff){
        this.monome.setCoeff(coeff);
    }
} 

class Polynomelist{
    private Element head ; 

    public Polynomelist(){
        this.head=null;
    }

    public Element getHead(){
        return this.head;
    }

    public void addMonome(Monome M){
        Element element = new Element(M);
        if(this.head==null){
            this.head=element;
        }
        else{
            Element currentMonome = this.head;
            while(currentMonome.next != null){
                currentMonome=currentMonome.next;
            }
            currentMonome.next=element ; 
        }
    }

    public void deleteElement(Element element){
        if(this.head==null){
            return;
        }
        Element currentElement = this.head ; 
        Element beforeElement = null;
        while(currentElement!=null && currentElement!=element){
            beforeElement=currentElement;
            currentElement=currentElement.next;
        }
        if(currentElement==null){
            currentElement=null;
            return;
        }
        if(currentElement==this.head){
            this.head=this.head.next;
            return;
        }
        beforeElement.next=currentElement.next; 
    }

    public void afficher(){
        if(head==null){
            System.out.println("Polynome est vide");
        }
        else{
            String result="";
            Element currentMonome=this.head;
            while(currentMonome!=null){
                result+=currentMonome.getMonomeCoeff()+"x^"+currentMonome.getMonomeDeg();
                if(currentMonome.next!=null){
                    result+=" -> ";
                }
                currentMonome=currentMonome.next; 
            }
            System.out.println(result);
        }
    }

    public boolean degExists(int deg){
        if(head==null){
            return false;
        }
        else{
            Element currentElement = head ; 
            while(currentElement!=null){
                if(currentElement.getMonomeDeg()==deg){
                    return true;
                }
                currentElement=currentElement.next;
            }
            return false;
        }
    }

    public void somme(Polynomelist polynome){
        Element currElement1 = this.head ; 
        while(currElement1!=null){
            Element currentElement2 = polynome.getHead();
            while(currentElement2!=null){
                if(currElement1.getMonomeDeg()==currentElement2.getMonomeDeg()){
                    currElement1.setMonomeCoeff(currElement1.getMonomeCoeff()+currentElement2.getMonomeCoeff());
                }
                currentElement2=currentElement2.next;
            }
            currElement1=currElement1.next;
        }
    }

    public Polynomelist produit(Polynomelist polynome){
        Polynomelist result = new Polynomelist(); 
        Element currentElement1=this.head ; 
        while(currentElement1!=null){
            Element currentElement2 = polynome.getHead();
            while(currentElement2!=null){
                Monome monome = new Monome(currentElement1.getMonomeDeg()+currentElement2.getMonomeDeg(), currentElement1.getMonomeCoeff()*currentElement2.getMonomeCoeff());
                result.addMonome(monome);
                currentElement2=currentElement2.next;
            }
            currentElement1=currentElement1.next;
        }
        currentElement1 = result.getHead();
        while(currentElement1!=null){
        Element currentElement2 = currentElement1.next;
            while(currentElement2!=null){
                if(currentElement2.getMonomeDeg()==currentElement1.getMonomeDeg()){
                    Element temp = currentElement2.next;
                    currentElement1.setMonomeCoeff(currentElement1.getMonomeCoeff()+currentElement2.getMonomeCoeff());
                    result.deleteElement(currentElement2);
                    currentElement2=temp;
                }
                else{
                    currentElement2=currentElement2.next;
                }
            }
            currentElement1=currentElement1.next;
        }
        return result;
    }
}