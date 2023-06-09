public class Item {
    public int qnt = 0;
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

    public Item(){
        
    }

    public double getValue(){
        return this.value;
    }
    
    public String getName(){
        return this.name;
    }

    public String getIng(){
        return this.ing;
    }

    public String getInfo(){
        return this.info;
    }

    public Boolean isAvaiability(){
        return this.availability;
    }


    @java.lang.Override
    public java.lang.String toString() {
        return  "--------------------------------------------------------------------------------\n" +
                "Nome: " + name + '\n' +
                "Ingredientes: " + ing + '\n' +
                "Informações: " + info + '\n' +
                "Preço: R$" + value + '\n' +
                "Disponibilidade: " + availability;
    }
}