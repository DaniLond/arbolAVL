import javax.swing.*;
import java.util.*;
import java.io.*;
public class ArbolAVL {

    private Node raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public Node getRaiz() {
        return raiz;
    }

    public Node obtenerRaiz(){
        return raiz;
    }
    //buscar
    public Node buscar(int d, Node r){
        if(raiz==null){
            return null;
        }else if(r.data==d){
            return r;
        }else if(r.data<d){
            return buscar(d, r.hijoDerecho);
        }else{
            return buscar(d, r.hijoIzquierdo);
        }
    }

    //obtener factor de equilibrio
    public int obtenerFE(Node x){
        if(x==null){
            return -1;
        }else{
            return x.fe;
        }
    }

    //rotacion simple a la izquierda
    public Node rotacionIzquierda(Node c){
        Node auxiliar=c.hijoIzquierdo;
        c.hijoIzquierdo=auxiliar.hijoDerecho;
        auxiliar.hijoDerecho=c;
        c.fe=Math.max(obtenerFE(c.hijoIzquierdo), obtenerFE(c.hijoDerecho))+1;  //obtiene el maximo
        auxiliar.fe=Math.max(obtenerFE(auxiliar.hijoIzquierdo), obtenerFE(auxiliar.hijoDerecho))+1;
        return auxiliar;
    }

    //rotacion simple derecha
    public Node rotacionDerecha(Node c){
        Node auxiliar=c.hijoDerecho;
        c.hijoDerecho=auxiliar.hijoIzquierdo;
        auxiliar.hijoIzquierdo=c;
        c.fe=Math.max(obtenerFE(c.hijoIzquierdo), obtenerFE(c.hijoDerecho))+1;  //obtiene el maximo
        auxiliar.fe=Math.max(obtenerFE(auxiliar.hijoIzquierdo), obtenerFE(auxiliar.hijoDerecho))+1;
        return auxiliar;
    }


    //rotacion doble a la der
    public Node rotacionDobleIzquierda(Node c){
        Node temporal;
        c.hijoIzquierdo= rotacionDerecha(c.hijoIzquierdo);
        temporal=rotacionIzquierda(c);
        return temporal;

    }
    //rotacion doble a la izq
    public Node rotacionDobleDerecha(Node c){
        Node temporal;
        c.hijoDerecho= rotacionIzquierda(c.hijoDerecho);
        temporal=rotacionDerecha(c);
        return temporal;
    }

    //insertar avl
    public Node insertarAVL(Node nuevo, Node subAr){
        Node nuevoPadre=subAr;
        if(nuevo.data<subAr.data){
            if(subAr.hijoIzquierdo==null){
                subAr.hijoIzquierdo=nuevo;
            }else{
                subAr.hijoIzquierdo=insertarAVL(nuevo, subAr.hijoIzquierdo);
                if((obtenerFE(subAr.hijoIzquierdo)-obtenerFE(subAr.hijoDerecho)==2)){
                    if(nuevo.data<subAr.hijoIzquierdo.data){
                        nuevoPadre=rotacionIzquierda(subAr);
                    }else{
                        nuevoPadre=rotacionDobleIzquierda(subAr);
                    }
                }
            }
        }else if(nuevo.data>subAr.data){
            if(subAr.hijoDerecho==null){
                subAr.hijoDerecho=nuevo;
            }else{
                subAr.hijoDerecho=insertarAVL(nuevo, subAr.hijoDerecho);
                if((obtenerFE(subAr.hijoDerecho)-obtenerFE(subAr.hijoIzquierdo)==2)){
                    if(nuevo.data>subAr.hijoDerecho.data){
                        nuevoPadre=rotacionDerecha(subAr);
                    }else{
                        nuevoPadre=rotacionDobleDerecha(subAr);
                    }
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Nodo duplicado");
        }

        //actualizar altura
        if((subAr.hijoIzquierdo==null)&&(subAr.hijoDerecho!=null)){
            subAr.fe=subAr.hijoDerecho.fe+1;
        }else if((subAr.hijoDerecho==null)&&(subAr.hijoIzquierdo!=null)){
            subAr.fe=subAr.hijoIzquierdo.fe+1;
        }else{
            subAr.fe=Math.max(obtenerFE(subAr.hijoIzquierdo), obtenerFE(subAr.hijoDerecho))+1;
        }
        return nuevoPadre;
    }


    //insertar normal
    public void insertar(int d){
        Node nuevo= new Node(d);
        if(raiz==null){
            raiz=nuevo;
        }else{
            raiz=insertarAVL(nuevo, raiz);
        }
    }

    public void inOrden(Node r){
        if(r!=null){
            inOrden(r.hijoIzquierdo);
            System.out.println(r.data);
            inOrden(r.hijoDerecho);
        }
    }


    public void levelOrder(){
        //Write your code here
        if (raiz == null) return;

        Queue<Node> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            Node node = cola.poll();
            System.out.print(node.data + " ");

            if (node.hijoIzquierdo != null) {
                cola.add(node.hijoIzquierdo);
            }

            if (node.hijoDerecho != null) {
                cola.add(node.hijoDerecho);
            }
        }
    }

}
