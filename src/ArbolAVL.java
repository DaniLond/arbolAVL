import javax.swing.*;
import java.util.*;
import java.io.*;

public class ArbolAVL<T extends Comparable<T>> {
    private Node<T> raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public Node<T> getRaiz() {
        return raiz;
    }

    public Node<T> obtenerRaiz() {
        return raiz;
    }

    public Node<T> buscar(T d, Node<T> r) {
        if (raiz == null) {
            return null;
        } else if (r.data.compareTo(d) == 0) {
            return r;
        } else if (r.data.compareTo(d) < 0) {
            return buscar(d, r.hijoDerecho);
        } else {
            return buscar(d, r.hijoIzquierdo);
        }
    }

    public int obtenerFE(Node<T> x) {
        if (x == null) {
            return -1;
        } else {
            return x.fe;
        }
    }

    public Node<T> rotacionIzquierda(Node<T> c) {
        Node<T> auxiliar = c.hijoIzquierdo;
        c.hijoIzquierdo = auxiliar.hijoDerecho;
        auxiliar.hijoDerecho = c;
        c.fe = Math.max(obtenerFE(c.hijoIzquierdo), obtenerFE(c.hijoDerecho)) + 1;
        auxiliar.fe = Math.max(obtenerFE(auxiliar.hijoIzquierdo), obtenerFE(auxiliar.hijoDerecho)) + 1;
        return auxiliar;
    }

    public Node<T> rotacionDerecha(Node<T> c) {
        Node<T> auxiliar = c.hijoDerecho;
        c.hijoDerecho = auxiliar.hijoIzquierdo;
        auxiliar.hijoIzquierdo = c;
        c.fe = Math.max(obtenerFE(c.hijoIzquierdo), obtenerFE(c.hijoDerecho)) + 1;
        auxiliar.fe = Math.max(obtenerFE(auxiliar.hijoIzquierdo), obtenerFE(auxiliar.hijoDerecho)) + 1;
        return auxiliar;
    }

    public Node<T> rotacionDobleIzquierda(Node<T> c) {
        Node<T> temporal;
        c.hijoIzquierdo = rotacionDerecha(c.hijoIzquierdo);
        temporal = rotacionIzquierda(c);
        return temporal;
    }

    public Node<T> rotacionDobleDerecha(Node<T> c) {
        Node<T> temporal;
        c.hijoDerecho = rotacionIzquierda(c.hijoDerecho);
        temporal = rotacionDerecha(c);
        return temporal;
    }


    public Node<T> insertarAVL(Node<T> nuevo, Node<T> subAr){
        Node<T> nuevoPadre = subAr;
        if(nuevo.data.compareTo(subAr.data) < 0){
            if(subAr.hijoIzquierdo == null){
                subAr.hijoIzquierdo = nuevo;
            }else{
                subAr.hijoIzquierdo = insertarAVL(nuevo, subAr.hijoIzquierdo);
                if((obtenerFE(subAr.hijoIzquierdo) - obtenerFE(subAr.hijoDerecho) == 2)){
                    if(nuevo.data.compareTo(subAr.hijoIzquierdo.data) < 0){
                        nuevoPadre = rotacionIzquierda(subAr);
                    }else{
                        nuevoPadre = rotacionDobleIzquierda(subAr);
                    }
                }
            }
        }else if(nuevo.data.compareTo(subAr.data) > 0){
            if(subAr.hijoDerecho == null){
                subAr.hijoDerecho = nuevo;
            }else{
                subAr.hijoDerecho = insertarAVL(nuevo, subAr.hijoDerecho);
                if((obtenerFE(subAr.hijoDerecho) - obtenerFE(subAr.hijoIzquierdo) == 2)){
                    if(nuevo.data.compareTo(subAr.hijoDerecho.data) > 0){
                        nuevoPadre = rotacionDerecha(subAr);
                    }else{
                        nuevoPadre = rotacionDobleDerecha(subAr);
                    }
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Nodo duplicado");
        }

        //actualizar altura
        if((subAr.hijoIzquierdo == null) && (subAr.hijoDerecho != null)){
            subAr.fe = subAr.hijoDerecho.fe + 1;
        }else if((subAr.hijoDerecho == null) && (subAr.hijoIzquierdo != null)){
            subAr.fe = subAr.hijoIzquierdo.fe + 1;
        }else{
            subAr.fe = Math.max(obtenerFE(subAr.hijoIzquierdo), obtenerFE(subAr.hijoDerecho)) + 1;
        }
        return nuevoPadre;
    }

    public void insertar(T d){
        Node<T> nuevo = new Node<>(d);
        if(raiz == null){
            raiz = nuevo;
        }else{
            raiz = insertarAVL(nuevo, raiz);
        }
    }

    public void inOrden(Node<T> r){
        if(r != null){
            inOrden(r.hijoIzquierdo);
            System.out.println(r.data);
            inOrden(r.hijoDerecho);
        }
    }

    public void levelOrder(){
        //Write your code here
        if (raiz == null) return;

        Queue<Node<T>> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            Node<T> node = cola.poll();
            System.out.print(node.data + " ");

            if (node.hijoIzquierdo != null) {
                cola.add(node.hijoIzquierdo);
            }

            if (node.hijoDerecho != null) {
                cola.add(node.hijoDerecho);
            }
        }
    }

    public void eliminar(T d){
        raiz= eliminar(d, raiz);
    }


    public Node<T> eliminar(T d, Node<T> r) {
        if (r == null) {
            return null;
        } else {
            if (d.compareTo(r.data) < 0) {
                r.hijoIzquierdo = eliminar(d, r.hijoIzquierdo);
            } else if (d.compareTo(r.data) > 0) {
                r.hijoDerecho = eliminar(d, r.hijoDerecho);
            } else {
                if (r.hijoIzquierdo == null && r.hijoDerecho == null) {
                    r = null;
                } else if (r.hijoIzquierdo == null) {
                    r = r.hijoDerecho;
                } else if (r.hijoDerecho == null) {
                    r = r.hijoIzquierdo;
                } else {
                    Node<T> sucesor = buscarSucesor(r.hijoDerecho);
                    r.data = sucesor.data;
                    r.hijoDerecho = eliminar(sucesor.data, r.hijoDerecho);
                }
            }
            if (r != null) {
                r.fe = Math.max(obtenerFE(r.hijoIzquierdo), obtenerFE(r.hijoDerecho)) + 1;
                int balance = obtenerFE(r.hijoIzquierdo) - obtenerFE(r.hijoDerecho);
                if (balance > 1) {
                    if (obtenerFE(r.hijoIzquierdo.hijoIzquierdo) >= obtenerFE(r.hijoIzquierdo.hijoDerecho)) {
                        r = rotacionIzquierda(r);
                    } else {
                        r = rotacionDobleIzquierda(r);
                    }
                } else if (balance < -1) {
                    if (obtenerFE(r.hijoDerecho.hijoDerecho) >= obtenerFE(r.hijoDerecho.hijoIzquierdo)) {
                        r = rotacionDerecha(r);
                    } else {
                        r = rotacionDobleDerecha(r);
                    }
                }
            }
            return r;
        }
    }

    private Node<T> buscarSucesor(Node<T> r) {
        while (r.hijoIzquierdo != null) {
            r = r.hijoIzquierdo;
        }
        return r;
    }

}
