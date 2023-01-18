package act1;

public class Clase {
    int codigo;
    String materia;
    int calificacion;

    public Clase(int calificacion, int codgio, String materia) {
        this.calificacion = calificacion;
        this.materia = materia;
        this.codigo = codgio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
