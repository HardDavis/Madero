public class Item {
    private String name, ing, info;
    private double value;
    private boolean availability;

    public Item(String nm, double val, String ing, String inf, boolean avai){
        this.name = nm;
        this.value = val;
        this.ing = ing;
        this.info = inf;
        this.availability = avai;
    }


    //Name
    public String getName() {
        return name;
    }
    public void setName(String nm) {
        this.name = nm;
    }

    //Value
    public double getValue() {
        return value;
    }
    public void setValue(double val) {
        this.value = val;
    }
    
    //Ingredients
    public String getIng() {
        return ing;
    }
    public void setIng(String ing) {
        this.ing = ing;
    }
    

    //Informations
    public String getInfo() {
        return info;
    }
    public void setInfo(String inf) {
        this.info = inf;
    }


    //Availability
    public boolean getAvailability() {
        return availability;
    }
    public void setAvailability(boolean avai) {
        this.availability = avai;
    }


    @java.lang.Override
    public java.lang.String toString() {
        return  "--------------------------------------------------------------------------------\n" +
                "Nome: " + name + '\n' +
                "Ingredientes: " + ing + '\n' +
                "Informações: " + info + '\n' +
                "Preço: R$" + value + '\n' +
                "Disponibilidade: " + availability +
                "\n--------------------------------------------------------------------------------";
    }
    
}