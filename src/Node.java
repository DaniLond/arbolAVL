public class Node<T> {
    T data;
    int fe;
    Node<T> hijoIzquierdo, hijoDerecho;

    public Node(T data) {
        this.data = data;
        this.fe = 0;
        this.hijoDerecho = null;
        this.hijoIzquierdo = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public Node<T> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(Node<T> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public Node<T> getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(Node<T> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
}
