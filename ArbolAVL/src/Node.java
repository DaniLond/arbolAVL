public class Node {
    int data;
    int fe;

    Node hijoIzquierdo, hijoDerecho;

    public Node(int data) {
        this.data = data;
        this.fe = 0;
        this.hijoDerecho=null;
        this.hijoIzquierdo= null;
    }
}
