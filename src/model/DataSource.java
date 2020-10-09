package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.spi.AbstractResourceBundleProvider;

public class DataSource {

    public static final String DB_NAME="müzik.db";
    public static final String CONNECTION_STRING="jdbc:sqlite:"+ DB_NAME;

    public static final String TABLO_ALBUM="album";
    public static final String SUTUN_ALBUM_ID="albumID";
    public static final String SUTUN_ALBUM_ADI="albumaAdi";
    public static final String SUTUN_ALBUM_SARKICIID="sarkiciID";

    public static final String TABLO_SARKICI="şarkıcı";
    public static final String SUTUN_SARKICI_ID="şarkıcıID";
    public static final String SUTUN_SARKICI_ADI="şarkıcıAdi";

    public static final String TABLO_SARKI="şarkı";
    public static final String SUTUN_SARKI_ID="şarkıID";
    public static final String SUTUN_SARKI_ADI="şarkıAdi";
    public static final String SUTUN_SARKI_ALBUMID="albumID";

    public static final int SIRALA_ARTAN=1;
    public static final int SIRLAMA_AZALAN=2;


    private Connection baglanti ;

    public boolean veriTabaniAc(){
        try {
            baglanti= DriverManager.getConnection(CONNECTION_STRING);
            return  true;
        } catch (SQLException e) {
            System.out.println("Veritabanına bağlanılamadı");
            return false;
        }
    }
    public void veriTabaniniKapat(){
        try {
            if (baglanti !=null)
            {
                baglanti.close();
            }
        }catch (SQLException e){
            System.out.println("Veritabanı kapatılamadı");
        }
    }



    public ArrayList<Sarkici> tumSarkicilariGetir(int siralama){

        StringBuilder sb=new StringBuilder();
        sb.append(TABLO_SARKICI);

        if (siralama == SIRALA_ARTAN){
            sb.append(" ORDER BY ");
            sb.append(SUTUN_SARKICI_ADI);
            sb.append(" ASC");



        }else {
            sb.append(" ORDER BY ");
            sb.append(SUTUN_SARKICI_ADI);
            sb.append(" DESC");
        }

        try(Statement statement=baglanti.createStatement();
            ResultSet sonuc=statement.executeQuery(sb.toString())) {

            ArrayList<Sarkici> tumSarkicilar =new ArrayList<>();


            while (sonuc.next()){
                 Sarkici sarkici =new Sarkici();
                 sarkici.setSarkiciID(sonuc.getInt(SUTUN_SARKICI_ID));
                 sarkici.setSarkiciAdi(sonuc.getString(SUTUN_SARKICI_ADI));

                 tumSarkicilar.add(sarkici);

            }
            return tumSarkicilar;

        }catch (SQLException e){
            System.out.println("Sorgu başarısız " + e.getMessage());
            return null;
        }
    }
}
