package co.jamesfl.apps.celureportes;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("co.jamesfl.apps.celureportes", appContext.getPackageName());
    }

    @Test
    public void samsungNegros() {
        Context ctx = InstrumentationRegistry.getTargetContext();
        Resources res = ctx.getResources();
        LinkedList<String> colores = new LinkedList<>(Arrays.asList(res.getStringArray(R.array.colores)));
        LinkedList<Celular> lista = new LinkedList<>();
        lista.add(new Celular("Galaxy Grand Prime", res.getString(R.string.marcaSamsung), res.getString(R.string.SOAndroid), colores.indexOf(res.getString(R.string.colorNegro)), 300000, 1));
        lista.add(new Celular("Galaxy J5", res.getString(R.string.marcaSamsung), res.getString(R.string.SOAndroid), colores.indexOf(res.getString(R.string.colorNegro)), 540000, 2));
        LinkedList<Celular> filtro = AppModel.samsungNegrosAndroid(AppModel.getDefaultCelulares(ctx), ctx);
        assertArrayEquals(lista.toArray(new Celular[lista.size()]), filtro.toArray(new Celular[filtro.size()]));
    }

    @Test
    public void huaweiMasBaratoIncorrecto() {
        Context ctx = InstrumentationRegistry.getTargetContext();
        Resources res = ctx.getResources();
        LinkedList<String> colores = new LinkedList<>(Arrays.asList(res.getStringArray(R.array.colores)));
        Celular inesperado = new Celular("Nova", res.getString(R.string.marcaHuawei), res.getString(R.string.SOAndroid), colores.indexOf(res.getString(R.string.colorBlanco)), 440000, 2);
        Celular filtro = AppModel.huaweiMasBarato(AppModel.getDefaultCelulares(ctx), ctx);
        assertNotEquals(inesperado, filtro);
    }

    @Test
    public void promedioNokia() {
        Context ctx = InstrumentationRegistry.getTargetContext();
        double test = 526000;
        double prom = AppModel.promedioNokia(AppModel.getDefaultCelulares(ctx), ctx);
        assertEquals(test, prom, 0);
    }
}
