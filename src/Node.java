public class Node{
    int data;
    int fe;

    Node hijoIzquierdo, hijoDerecho;

    public Node(int data) {
        this.data = data;
        this.fe = 0;
        this.hijoDerecho=null;
        this.hijoIzquierdo= null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public Node getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(Node hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public Node getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(Node hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
}
