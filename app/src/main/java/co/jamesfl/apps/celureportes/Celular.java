package co.jamesfl.apps.celureportes;

/**
 * Created by javie on 30/09/2017.
 */

public class Celular {
    private String nombre, marca, sistOp;
    private int color, precio, ram;

    public Celular(String nombre, String marca, String sistOp, int color, int precio, int ram) {
        this.nombre = nombre;
        this.marca = marca;
        this.sistOp = sistOp;
        this.color = color;
        this.precio = precio;
        this.ram = ram;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSistOp() {
        return sistOp;
    }

    public void setSistOp(String sistOp) {
        this.sistOp = sistOp;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Celular celular = (Celular) o;

        if (color != celular.color) return false;
        if (precio != celular.precio) return false;
        if (ram != celular.ram) return false;
        if (!nombre.equals(celular.nombre)) return false;
        if (!marca.equals(celular.marca)) return false;
        return sistOp.equals(celular.sistOp);

    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + marca.hashCode();
        result = 31 * result + sistOp.hashCode();
        result = 31 * result + color;
        result = 31 * result + precio;
        result = 31 * result + ram;
        return result;
    }

    public void guardar() {
        AppModel.guardarCelular(this);
    }
}
