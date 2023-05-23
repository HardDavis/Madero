public class Item {
    String name, ing, info;
    double value;
    boolean availability;

    public Item(String nm, double val, String ing, String inf, boolean avai){
        this.name = nm;
        this.value = val;
        this.ing = ing;
        this.info = inf;
        this.availability = avai;
    }

    public Item(){
        
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