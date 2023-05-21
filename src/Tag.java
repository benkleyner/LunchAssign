public class Tag {
    private String name;
    private int weight;
    private int ID;

    public Tag(){
        this.name = "";
        this.weight = 0;
        this.ID = 0;
    }

    public Tag(String name, int weight, int ID){
        this.name = name;
        this.weight = weight;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", ID=" + ID +
                '}';
    }
}

