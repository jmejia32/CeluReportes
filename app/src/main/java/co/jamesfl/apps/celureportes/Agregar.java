package co.jamesfl.apps.celureportes;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;

public class Agregar extends AppCompatActivity {
    private Spinner spMarca, spSO, spColor;
    private EditText etNombre, etPrecio, etRAM;
    private Resources res;
    private LinkedList<String> marcas, sistOp, colores, soiOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        spMarca = (Spinner) findViewById(R.id.spMarca);
        spSO = (Spinner) findViewById(R.id.spSO);
        spColor = (Spinner) findViewById(R.id.spColor);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        etRAM = (EditText) findViewById(R.id.etRam);
        res = getResources();
        String spHint = res.getString(R.string.seleccione);
        marcas = new LinkedList<>(Arrays.asList(res.getStringArray(R.array.marcas)));
        marcas.addFirst(spHint);

        sistOp = new LinkedList<>(Arrays.asList(res.getStringArray(R.array.sistemasOp)));
        sistOp.addFirst(spHint);

        colores = new LinkedList<>(Arrays.asList(res.getStringArray(R.array.colores)));
        colores.addFirst(spHint);

        soiOS = new LinkedList<>();
        soiOS.addFirst(spHint);
        soiOS.add(res.getString(R.string.SOiOS));

        ArrayAdapter<String> adpMarca = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, marcas);
        spMarca.setAdapter(adpMarca);

        ArrayAdapter<String> adpSO = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sistOp);
        spSO.setAdapter(adpSO);

        spMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LinkedList<String> list;
                if (i == 2) { //Apple
                    list = soiOS;
                } else {
                    list = sistOp;
                }
                ArrayAdapter<String> adp = new ArrayAdapter<>(Agregar.this, android.R.layout.simple_spinner_item, list);
                spSO.setAdapter(adp);
                if (i == 2) spSO.setSelection(1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ArrayAdapter<String> adpColor = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colores);
        spColor.setAdapter(adpColor);
    }

    private boolean valido() {
        if (etNombre.getText().toString().isEmpty()) {
            etNombre.setError(res.getString(R.string.errNombre));
            etNombre.requestFocus();
            return false;
        }
        if (spMarca.getSelectedItemPosition() == 0) {
            setErrorSpinner(spMarca, res.getString(R.string.errMarca));
            return false;
        }
        if (spSO.getSelectedItemPosition() == 0) {
            setErrorSpinner(spSO, res.getString(R.string.errSO));
            return false;
        }
        if (spColor.getSelectedItemPosition() == 0) {
            setErrorSpinner(spColor, res.getString(R.string.errColor));
            return false;
        }
        if (etRAM.getText().toString().isEmpty()) {
            etRAM.setError(res.getString(R.string.errRAM));
            etRAM.requestFocus();
            return false;
        }
        if (etPrecio.getText().toString().isEmpty()) {
            etPrecio.setError(res.getString(R.string.errPrecio));
            etPrecio.requestFocus();
            return false;
        }
        return true;
    }

    private void setErrorSpinner(Spinner s, String err) {
        View v = s.getSelectedView();
        if (v != null) {
            TextView txt = (TextView) v;
            txt.setError("1");
            spMarca.requestFocus();
        }
        Toast.makeText(this, err, Toast.LENGTH_LONG).show();
    }

    public void agregar(View v) {
        if (valido()) {
            String nomb, marca, so;
            int color, precio, ram;
            nomb = etNombre.getText().toString();
            marca = marcas.get(spMarca.getSelectedItemPosition());
            so = spSO.getSelectedItem().toString();
            color = spColor.getSelectedItemPosition() - 1;
            precio = Integer.parseInt(etPrecio.getText().toString());
            ram = Integer.parseInt(etRAM.getText().toString());
            Celular c = new Celular(nomb, marca, so, color, precio, ram);
            c.guardar();
            Toast.makeText(this, res.getString(R.string.msjAgregar), Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, Principal.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
