public class AdjacentVertex {
    private Vertex vertex;
    private int cost;
    
    public AdjacentVertex(Vertex vertex, int cost){
        this.vertex = vertex;
        this.cost = cost;
    }
    public Vertex getVertex(){
        return this.vertex;
    }
    public void setVertex(Vertex vertex){
        this.vertex = vertex;
    }
    public int getCost(){
        return this.cost;
    }
    public void setCost(int cost){
        this.cost = cost;
    }
}
