package co.jamesfl.apps.celureportes;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class Principal extends AppCompatActivity {
    private TableLayout tbl;
    private Spinner spFiltro;
    private Resources res;
    private LinkedList<Celular> cels;
    private String[] colores, filtros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        spFiltro = (Spinner) findViewById(R.id.spFiltro);
        tbl = (TableLayout) findViewById(R.id.tblLista);
        res = getResources();
        cels = AppModel.getCelulares();
        if (cels.isEmpty()) cels = AppModel.getDefaultCelulares(this);
        colores = res.getStringArray(R.array.colores);
        filtros = res.getStringArray(R.array.filtros);

        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, filtros);
        spFiltro.setAdapter(adp);
        spFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        actualizarTbl(cels);
                        break;
                    case 1: //Huawei Barato
                        LinkedList<Celular> lista = new LinkedList<>();
                        lista.add(AppModel.huaweiMasBarato(cels, Principal.this));
                        actualizarTbl(lista);
                        break;
                    case 2: //Promedio Nokia
                        double prom = AppModel.promedioNokia(cels, Principal.this);
                        Toast.makeText(Principal.this, res.getString(R.string.msjProm) + ": " + prom, Toast.LENGTH_SHORT).show();
                        adapterView.setSelection(0);
                        break;
                    case 3: //Samsung negros
                        LinkedList<Celular> li = AppModel.samsungNegrosAndroid(cels, Principal.this);
                        actualizarTbl(li);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    private void actualizarTbl(LinkedList<Celular> celulares) {
        TableRow header = (TableRow) tbl.getChildAt(0);
        tbl.removeAllViews();
        tbl.addView(header);
        for (Celular c : celulares) {
            TableRow fila = new TableRow(this);
            TextView tvNomb = new TextView(this);
            TextView tvMarca = new TextView(this);
            TextView tvSO = new TextView(this);
            TextView tvClr = new TextView(this);
            TextView tvPrec = new TextView(this);

            tvMarca.setGravity(Gravity.CENTER);
            tvSO.setGravity(Gravity.CENTER);
            tvClr.setGravity(Gravity.CENTER);
            tvPrec.setGravity(Gravity.RIGHT);

            tvNomb.setText(c.getNombre());
            tvMarca.setText(c.getMarca());
            tvSO.setText(c.getSistOp());
            tvClr.setText(colores[c.getColor()]);
            tvPrec.setText(""+c.getPrecio());

            fila.addView(tvNomb);
            fila.addView(tvMarca);
            fila.addView(tvSO);
            fila.addView(tvClr);
            fila.addView(tvPrec);
            tbl.addView(fila);
        }
    }

    public void agregar(View v) {
        Intent i = new Intent(this, Agregar.class);
        startActivity(i);
        this.finish();
    }
}
