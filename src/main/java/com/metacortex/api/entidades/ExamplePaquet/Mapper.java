package com.metacortex.api.entidades.ExamplePaquet;

import com.metacortex.api.entidades.TechnicalRegistry;

public final class Mapper {

    public static SMARegistry[][] toSMAExample(TechnicalRegistry[][] matriz){
        SMARegistry[][] arrayRes=new SMARegistry[matriz.length][matriz[0].length];
        for (int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                SMARegistry nuevo=new SMARegistry(matriz[i][j]);
                arrayRes[i][j]=nuevo;
            }
        }
        return arrayRes;
    }

    public static EMARegistry[][] toEMAExample(TechnicalRegistry[][] matriz){
        EMARegistry[][] arrayRes=new EMARegistry[matriz.length][matriz[0].length];
        for (int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                EMARegistry nuevo=new EMARegistry(matriz[i][j]);
                arrayRes[i][j]=nuevo;
            }
        }
        return arrayRes;
    }

    public static DEMARegistry[][] toDEMAExample(TechnicalRegistry[][] matriz){
        DEMARegistry[][] arrayRes=new DEMARegistry[matriz.length][matriz[0].length];
        for (int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                DEMARegistry nuevo=new DEMARegistry(matriz[i][j]);
                arrayRes[i][j]=nuevo;
            }
        }
        return arrayRes;
    }

    public static KAMARegistry[][] toKAMAExample(TechnicalRegistry[][] matriz){
        KAMARegistry[][] arrayRes=new KAMARegistry[matriz.length][matriz[0].length];
        for (int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                KAMARegistry nuevo=new KAMARegistry(matriz[i][j]);
                arrayRes[i][j]=nuevo;
            }
        }
        return arrayRes;
    }

    public static MAMARegistry[][] toMAMAExample(TechnicalRegistry[][] matriz){
        MAMARegistry[][] arrayRes=new MAMARegistry[matriz.length][matriz[0].length];
        for (int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                MAMARegistry nuevo=new MAMARegistry(matriz[i][j]);
                arrayRes[i][j]=nuevo;
            }
        }
        return arrayRes;
    }

    public static TEMARegistry[][] toTEMARegistry(TechnicalRegistry[][] matriz){
        TEMARegistry[][] arrayRes=new TEMARegistry[matriz.length][matriz[0].length];
        for (int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                TEMARegistry nuevo=new TEMARegistry(matriz[i][j]);
                arrayRes[i][j]=nuevo;
            }
        }
        return arrayRes;
    }

    public static TMARegistry[][] toTMARegistry(TechnicalRegistry[][] matriz){
        TMARegistry[][] arrayRes=new TMARegistry[matriz.length][matriz[0].length];
        for (int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                TMARegistry nuevo=new TMARegistry(matriz[i][j]);
                arrayRes[i][j]=nuevo;
            }
        }
        return arrayRes;
    }

    public static WMARegistry[][] toWMARegistry(TechnicalRegistry[][] matriz){
        WMARegistry[][] arrayRes=new WMARegistry[matriz.length][matriz[0].length];
        for (int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                WMARegistry nuevo=new WMARegistry(matriz[i][j]);
                arrayRes[i][j]=nuevo;
            }
        }
        return arrayRes;
    }

    public static T3Registry[][] toT3Registry(TechnicalRegistry[][] matriz){
        T3Registry[][] arrayRes=new T3Registry[matriz.length][matriz[0].length];
        for (int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                T3Registry nuevo=new T3Registry(matriz[i][j]);
                arrayRes[i][j]=nuevo;
            }
        }
        return arrayRes;
    }


}
