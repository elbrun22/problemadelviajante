
package problemabdelviajante;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class ProblemaDelViajante {
    
    
     

    static List<String> soluciones = new ArrayList<>();

    private static int devolucion(String i) {

        switch (i) {
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            default:
                return 0;
        }
    }

    private static void Perm2(String[] elem, String act, int n, int r) {

        if (n == 0) {
            soluciones.add(act + "" + act.charAt(0));
        } else {
            for (int i = 0; i < r; i++) {
                if (!act.contains(elem[i])) {
                    Perm2(elem, act + elem[i], n - 1, r);
                }
            }
        }

    }

    public static void main(String[] args) {

       
        int cantidad;

      
        

        cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de nodos: "));
       

        // Guarda el valor de cada ruta
        int matriz[][] = new int[cantidad + 1][cantidad + 1];

        // Me ayuda a partir la matriz para no repetir valores
        int aux = 0;

       
      
        for (int i = 1; i <= cantidad - 1; i++) {
            aux = i;
            for (int j = 2; j <= cantidad; j++) {

                // COMPARA QUE i, j NO SEAN IGUALES
                if (i != j) {
                 
                    if (j > aux) {
                       
                        matriz[i][j] =  Integer.parseInt(JOptionPane.showInputDialog("Ingrese la distancia de: " + i + "" + " a " + j + " "));
                        matriz[j][i] = matriz[i][j];
                    }
                } else {
                    matriz[i][j] = 0;
                    matriz[j][i] = 0;
                }

            }

            aux++;

        }

        // Separa los nodos para hacer las combinaciones
        String[] nodos = new String[cantidad];

        int opcion;

        int TotalSoluciones = 1;

       
        for (int i = 0; i < cantidad; i++) {
            TotalSoluciones = TotalSoluciones * (i + 1);
            nodos[i] = String.valueOf(i + 1);
           
        }
       //guarda la opcion elejida
        opcion = Integer.parseInt(JOptionPane.showInputDialog("Elija un nodo: "));
        
        //comprueba que no elija mas de la cantidad de nodos que eligio al comienzo
        while (opcion < 1 || opcion > cantidad) {
            System.out.println("");
            JOptionPane.showMessageDialog(null, "Elija un nodo valido!");
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Elija un nodo: "));
            
        }

       
        int r = nodos.length;

        // TODAS LAS COMBINACIONES POSIBLES
        Perm2(nodos, "", cantidad, r);

       

        int referencia = opcion * (TotalSoluciones / cantidad);

       
        
        List<Integer> km = new ArrayList<>();
        int indice = referencia - (TotalSoluciones / cantidad);

       
        for (int i = indice; i < referencia; i++) {
           JOptionPane.showMessageDialog(null, "combinaciones posibles: " + (i + 1) + ") " + soluciones.get(i));
           
           

            int sumatoria = 0;
            for (int j = 1; j < cantidad + 1; j++) {

                
                
                int x = devolucion(String.valueOf(soluciones.get(i).charAt(j - 1)));
                int y = devolucion(String.valueOf(soluciones.get(i).charAt(j)));
               

                sumatoria += matriz[x][y];

            }
            
            km.add(sumatoria);
            JOptionPane.showMessageDialog(null, " Total " + sumatoria);
            System.out.println("");
        }

       

        int min = km.get(0);

        List<Integer> mejorcamino = new ArrayList<>();

        //Guarda el camino más corto
        for (int i = 0; i < km.size(); i++) {
            if (min > km.get(i)) {
                min = km.get(i);
            }
        }

        //Verifica que no hayan mas de un resultado optimo
        for (int i = 0; i < km.size(); i++) {
            if (min == km.get(i)) {
                mejorcamino.add(i);
            }
        }

        String mensajeInicial;
        String mensajeFinal;

        if (mejorcamino.size() == 1) {
            mensajeInicial = "El siguiente recorrido:";
            mensajeFinal = "Tiene una distancia de " + min + "  y es el camino mas corto.";
        } else {
            mensajeInicial = "Los siguientes recorridos:";
            mensajeFinal = "Tienen una distancia de " + min + " y son los caminos mas cortos.";
        }

        JOptionPane.showMessageDialog(null, mensajeInicial);
        for (int i = 0; i < mejorcamino.size(); i++) {
            JOptionPane.showMessageDialog(null, mejorcamino.get(i) + indice + 1 + ") " + soluciones.get(mejorcamino.get(i) + indice + 1));
        }
       JOptionPane.showMessageDialog(null, mensajeFinal);

    }
}
