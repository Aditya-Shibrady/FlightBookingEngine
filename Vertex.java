
import java.util.LinkedList;

public class Vertex implements Comparable<Vertex>{
    private String name;
    private LinkedList<AdjacentVertex> listadjvertxs;
    private int cost;
    private boolean known;
    private boolean isInfinity;
    private Vertex prevVertex;
    public Vertex(String name){
        this.name = name;
        this.cost= 0;
        this.known = false;
        this.isInfinity = true;
        this.prevVertex = null;
    }
    public LinkedList<AdjacentVertex> getlstadjvertx(){
        return this.listadjvertxs;
    }
    public void setadj(LinkedList<AdjacentVertex> listofadjvertx){
        this.listadjvertxs = listofadjvertx;
    }
    
    public String getName(){
        return this.name;
    }
    public int getCost(){
        return this.cost;
    }
    public void setCost(int cost){
        this.cost= cost;
    }
    
    public boolean getKnown(){
        return this.known;
    }
    
    public void setKnown(boolean known){
        this.known=known;
    }
    
    public boolean getIsInfinity(){
        return this.isInfinity;
    }
    
    public void setIsInfinity(boolean isInfinity){
        this.isInfinity=isInfinity;
    }
    
    public Vertex getPrevVertex(){
        return this.prevVertex;
    }
    
    public void setPrevVertex(Vertex prevVertex){
        this.prevVertex = prevVertex;
    }
    
    public int compareTo(Vertex vertex){
        return this.cost - vertex.getCost();
    }
    
    public String toString(){
        return this.name;
    }
}
