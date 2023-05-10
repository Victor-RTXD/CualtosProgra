package AVL;

/*
 * Documetnacion
 * AVL tree is a self-balancing binary search tree in which each node maintains extra information called a 
 * balance factor whose value is either -1, 0 or +1.
 * 
 * se balancea con la diferencia de altura del subarbol izquierda y del de la derecha
 * 
 * factor de balanceo = altura subarbol izquierda - altura sub arbol derecha
 * o altura de sub arbol derecha - subarbol izquierda
 * 
 * Tiene una complegidad de O(log n) en busqueda, insercion y eliminacion
 * 
 * Rotacion de subarboles en arbol AVL
 * 
 * Rotacion izquierda: el arreglo de nodos de la derecha son transofrmados en
 * arreglos del nodo derecho
 * 
 * Rotacion derecha: los arreglos de los nodos de la izquierda se transforman
 * en arreglos del nodo derecho
 * 
 * para ver los pasos: https://www.programiz.com/dsa/avl-tree
 */
class AvlTree {
    Nodo root;

    int height(Nodo N) {
      if (N == null)
        return 0;
      return N.height;
    }
  
    int max(int a, int b) {
      return (a > b) ? a : b;
    }
  
    Nodo rightRotate(Nodo y) {
      Nodo x = y.left;
      Nodo T2 = x.right;
      x.right = y;
      y.left = T2;
      y.height = max(height(y.left), height(y.right)) + 1;
      x.height = max(height(x.left), height(x.right)) + 1;
      return x;
    }
  
    Nodo leftRotate(Nodo x) {
      Nodo y = x.right;
      Nodo T2 = y.left;
      y.left = x;
      x.right = T2;
      x.height = max(height(x.left), height(x.right)) + 1;
      y.height = max(height(y.left), height(y.right)) + 1;
      return y;
    }
  
    // Get balance factor of a Nodo
    int getBalanceFactor(Nodo N) {
      if (N == null)
        return 0;
      return height(N.left) - height(N.right);
    }
  
    // Insert a Nodo
    Nodo insertNodo(Nodo Nodo, int item) {
  
      // Find the position and insert the Nodo
      if (Nodo == null)
        return (new Nodo(item));
      if (item < Nodo.item)
        Nodo.left = insertNodo(Nodo.left, item);
      else if (item > Nodo.item)
        Nodo.right = insertNodo(Nodo.right, item);
      else
        return Nodo;
  
      // Update the balance factor of each Nodo
      // And, balance the tree
      Nodo.height = 1 + max(height(Nodo.left), height(Nodo.right));
      int balanceFactor = getBalanceFactor(Nodo);
      if (balanceFactor > 1) {
        if (item < Nodo.left.item) {
          return rightRotate(Nodo);
        } else if (item > Nodo.left.item) {
          Nodo.left = leftRotate(Nodo.left);
          return rightRotate(Nodo);
        }
      }
      if (balanceFactor < -1) {
        if (item > Nodo.right.item) {
          return leftRotate(Nodo);
        } else if (item < Nodo.right.item) {
          Nodo.right = rightRotate(Nodo.right);
          return leftRotate(Nodo);
        }
      }
      return Nodo;
    }
  
    Nodo NodoWithMimumValue(Nodo Nodo) {
      Nodo current = Nodo;
      while (current.left != null)
        current = current.left;
      return current;
    }
  
    // Delete a Nodo
    Nodo deleteNodo(Nodo root, int item) {
  
      // Find the Nodo to be deleted and remove it
      if (root == null)
        return root;
      if (item < root.item)
        root.left = deleteNodo(root.left, item);
      else if (item > root.item)
        root.right = deleteNodo(root.right, item);
      else {
        if ((root.left == null) || (root.right == null)) {
          Nodo temp = null;
          if (temp == root.left)
            temp = root.right;
          else
            temp = root.left;
          if (temp == null) {
            temp = root;
            root = null;
          } else
            root = temp;
        } else {
          Nodo temp = NodoWithMimumValue(root.right);
          root.item = temp.item;
          root.right = deleteNodo(root.right, temp.item);
        }
      }
      if (root == null)
        return root;
  
      // Update the balance factor of each Nodo and balance the tree
      root.height = max(height(root.left), height(root.right)) + 1;
      int balanceFactor = getBalanceFactor(root);
      if (balanceFactor > 1) {
        if (getBalanceFactor(root.left) >= 0) {
          return rightRotate(root);
        } else {
          root.left = leftRotate(root.left);
          return rightRotate(root);
        }
      }
      if (balanceFactor < -1) {
        if (getBalanceFactor(root.right) <= 0) {
          return leftRotate(root);
        } else {
          root.right = rightRotate(root.right);
          return leftRotate(root);
        }
      }
      return root;
    }
  
    void preOrder(Nodo Nodo) {
      if (Nodo != null) {
        System.out.print(Nodo.item + " ");
        preOrder(Nodo.left);
        preOrder(Nodo.right);
      }
    }
  
    // Print the tree
    private void printTree(Nodo currPtr, String indent, boolean last) {
      if (currPtr != null) {
        System.out.print(indent);
        if (last) {
          System.out.print("R----");
          indent += "   ";
        } else {
          System.out.print("L----");
          indent += "|  ";
        }
        System.out.println(currPtr.item);
        printTree(currPtr.left, indent, false);
        printTree(currPtr.right, indent, true);
      }
    }

    public static void inorden(Nodo root){
        if (root != null) {
            inorden(root.left);
            System.out.print(root.item + " ");
            inorden(root.right);
        }
    }
  
    // Driver code
    public static void main(String[] args) {
      AvlTree tree = new AvlTree();
      tree.root = tree.insertNodo(tree.root, 33);
      tree.root = tree.insertNodo(tree.root, 13);
      tree.root = tree.insertNodo(tree.root, 53);
      tree.root = tree.insertNodo(tree.root, 9);
      tree.root = tree.insertNodo(tree.root, 21);
      tree.root = tree.insertNodo(tree.root, 61);
      tree.root = tree.insertNodo(tree.root, 8);
      tree.root = tree.insertNodo(tree.root, 11);
      tree.printTree(tree.root, "", true);
      tree.root = tree.deleteNodo(tree.root, 13);
      System.out.println("After Deletion: ");
      tree.printTree(tree.root, "", true);

      System.out.println("inorden ");
      inorden(tree.root);
    }
}