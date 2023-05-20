import java.util.ArrayList;

public class Mesa{
    boolean status;

    public Mesa(){
        this.status = true;
    }

    public boolean getStatus(){
        return status;
    }

    public void exibir_mesas(ArrayList<Mesa> mesaList){
        for (int i = 0; i < mesaList.size(); i++) {
            if(mesaList.get(i).status){
                System.out.print("Mesa " + (i+1) + " - LIVRE\n");
            }else {
                System.out.print("Mesa " + (i+1) + " - OCUPADA\n");
            }
        }
    }

    public void cria_mesas(ArrayList<Mesa> mesaList){
        mesaList.add(new Mesa());
        mesaList.add(new Mesa());
        mesaList.add(new Mesa());
        mesaList.add(new Mesa());
    }
}
