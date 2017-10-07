package co.jamesfl.apps.celureportes;

import android.content.Context;
import android.content.res.Resources;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by javie on 30/09/2017.
 */

public class AppModel {

    private static LinkedList<Celular> celulares = new LinkedList<>();

    public static LinkedList<Celular> getDefaultCelulares(Context ctx) {
        Resources res = ctx.getResources();
        LinkedList<String> colores = new LinkedList<>(Arrays.asList(res.getStringArray(R.array.colores)));
        celulares.add(new Celular("Galaxy Grand Prime", res.getString(R.string.marcaSamsung), res.getString(R.string.SOAndroid), colores.indexOf(res.getString(R.string.colorNegro)), 300000, 1));
        celulares.add(new Celular("Galaxy J5", res.getString(R.string.marcaSamsung), res.getString(R.string.SOAndroid), colores.indexOf(res.getString(R.string.colorNegro)), 540000, 2));
        celulares.add(new Celular("Galaxy Galaxy Ace 4", res.getString(R.string.marcaSamsung), res.getString(R.string.SOWinPhone), colores.indexOf(res.getString(R.string.colorBlanco)), 230000, 1));
        celulares.add(new Celular("Nokia 3", res.getString(R.string.marcaNokia), res.getString(R.string.SOAndroid), colores.indexOf(res.getString(R.string.colorBlanco)), 431000, 2));
        celulares.add(new Celular("Nokia 5", res.getString(R.string.marcaNokia), res.getString(R.string.SOAndroid), colores.indexOf(res.getString(R.string.colorNegro)), 621000, 3));
        celulares.add(new Celular("Nova", res.getString(R.string.marcaHuawei), res.getString(R.string.SOAndroid), colores.indexOf(res.getString(R.string.colorBlanco)), 440000, 2));
        celulares.add(new Celular("P9", res.getString(R.string.marcaHuawei), res.getString(R.string.SOAndroid), colores.indexOf(res.getString(R.string.colorNegro)), 980000, 3));
        celulares.add(new Celular("P8 Lite", res.getString(R.string.marcaHuawei), res.getString(R.string.SOAndroid), colores.indexOf(res.getString(R.string.colorBlanco)), 420000, 3));
        celulares.add(new Celular("Mate 9", res.getString(R.string.marcaHuawei), res.getString(R.string.SOAndroid), colores.indexOf(res.getString(R.string.colorBlanco)), 680000, 3));
        return getCelulares();
    }

    public static LinkedList<Celular> getCelulares() {
        return celulares;
    }

    public static void guardarCelular(Celular c) {
        celulares.add(c);
    }

    public static boolean huaweiBlancoAndroid (Celular c, Context ctx) {
        Resources r = ctx.getResources();
        LinkedList<String> colores = new LinkedList<>(Arrays.asList(r.getStringArray(R.array.colores)));
        return c.getMarca().equals(r.getString(R.string.marcaHuawei)) &&
                c.getColor() == colores.indexOf(r.getString(R.string.colorBlanco)) &&
                c.getSistOp().equals(r.getString(R.string.SOAndroid));
    }

    public static boolean samsungNegroAndroid (Celular c, Context ctx) {
        Resources r = ctx.getResources();
        LinkedList<String> colores = new LinkedList<>(Arrays.asList(r.getStringArray(R.array.colores)));
        return c.getMarca().equals(r.getString(R.string.marcaSamsung)) &&
                c.getColor() == colores.indexOf(r.getString(R.string.colorNegro)) &&
                c.getSistOp().equals(r.getString(R.string.SOAndroid));
    }

    public static boolean marcaNokia(Celular c, Context ctx) {
        Resources r = ctx.getResources();
        return c.getMarca().equals(r.getString(R.string.marcaNokia));
    }

    public static double promedioNokia(LinkedList<Celular> celulares, Context ctx) {
        if (celulares.isEmpty()) return 0;
        int suma = 0;
        int total = 0;
        for (Celular c : celulares) {
            if (marcaNokia(c, ctx)) {
                suma += c.getPrecio();
                total++;
            }
        }
        double prom = (double) suma / (double) total;
        return prom;
    }

    public static Celular huaweiMasBarato(LinkedList<Celular> celulares, Context ctx) {
        LinkedList<Celular> huawei = new LinkedList<>();
        for (Celular c : celulares) {
            if (huaweiBlancoAndroid(c, ctx)) {
                huawei.add(c);
            }
        }
        Celular barato = huawei.getFirst();
        for (Celular h : huawei.subList(1, huawei.size() - 1)) {
            if (h.getPrecio() < barato.getPrecio()) {
                barato = h;
            }
        }
        return barato;
    }

    public static LinkedList<Celular> samsungNegrosAndroid(LinkedList<Celular> celulares, Context ctx) {
        LinkedList<Celular> samsung = new LinkedList<>();
        for (Celular c : celulares) {
            if (samsungNegroAndroid(c, ctx)) {
                samsung.add(c);
            }
        }
        return samsung;
    }


}
