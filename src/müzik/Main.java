package müzik;

import model.DataSource;
import model.Sarkici;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        DataSource dataSource =new DataSource();

        dataSource.veriTabaniAc();

       ArrayList<Sarkici> tumSarkicilar = dataSource.tumSarkicilariGetir(DataSource.SIRLAMA_AZALAN);
       if (tumSarkicilar == null){
           System.out.println("Şarkıcı yok");
           return;
       }for (Sarkici s : tumSarkicilar){
            System.out.println("ID: "+ s.getSarkiciID() + "ADI: "+ s.getSarkiciAdi());
        }


        dataSource.veriTabaniniKapat();
    }
}
