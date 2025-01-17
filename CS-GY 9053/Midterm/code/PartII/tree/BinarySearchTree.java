package tree;
import java.util.Comparator;
import pair.Pair;

public class BinarySearchTree<T> {
	
    private TreeNode<T> root;
    private Comparator<T> comparator;

    private int compare(T x, T y) {
    	if (comparator != null) {
    		return comparator.compare(x, y);
    	}
    	else {
    		return ((Comparable<T>) x).compareTo(y);
    	}
    }
    
    public BinarySearchTree() {
        this.root = null;
        this.comparator = null;
    }

    public BinarySearchTree(Comparator<T> comparator) {
    	this.root = null;
    	this.comparator = comparator;
    }
    
    // Insert value into the BST
    public void insert(T value) {
        root = insertRec(root, value);
    }

    private TreeNode<T> insertRec(TreeNode<T> root, T value) {
        if (root == null) {
            root = new TreeNode<>(value);
            return root;
        }

        if (compare(value, root.value) < 0) {
            root.left = insertRec(root.left, value);
        } else if (compare(value, root.value) > 0) {
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    // Search for a value in the BST
    public boolean search(T value) {
        return searchRec(root, value);
    }

    private boolean searchRec(TreeNode<T> root, T value) {
        if (root == null) {
            return false;
        }

        int cmp = compare(value, root.value);
        if (cmp == 0) {
            return true;
        }

        if (cmp < 0) {
        	return searchRec(root.left, value);
        } else {
        	return searchRec(root.right, value);
        }
    }

    // Method to call inorder traversal of the BST
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(TreeNode<T> root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.value + " ");
            inorderRec(root.right);
        }
    }
    
    public static void main(String[] args) {
    	
    	TreeNode<Integer> tn = new TreeNode<>(5);
    	if (tn instanceof Comparable<?>) {
    		Comparable<?> c = (Comparable<?>)tn;
    	}
    	/* this is how it works now: */
//    	BinarySearchTree bst0 = new BinarySearchTree();
//    	for (int i=0;i<10;i++) {
//    		int val = (int)(Math.random()*100);
//    		System.out.println("inserting " + val);
//    		bst0.insert(val);
//    	}
//    	bst0.inorder();
    	
    	/* this is how it should be able to work :*/
    	System.out.println("bst:");
    	BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    	for (int i=0;i<10;i++) {
    		int val = (int)(Math.random()*100);
    		System.out.println("inserting " + val);
    		bst.insert(val);
    	}
    	bst.inorder();
    	
    	/* this will fail during the insert or constructor call
    	 * because Objects are not Comparable,
    	 * unless I pass in a Comparator into the constructor
    	 */
    	System.out.println();
    	System.out.println("bst2:");
    	Comparator<Object> objectComparator = (o1, o2) -> (o1.hashCode()-o2.hashCode());
    	BinarySearchTree<Object> bst2 = new BinarySearchTree<>(objectComparator);
    	for (int i=0;i<10;i++) {
    		Object val = new Object();
    		bst2.insert(val);
    	}
    	bst2.inorder();
    	
    	/* Assume I have a BST named bst3 that takes Pair objects 
    	 * where the Pair objects have Key,Value pairs of 
    	 * Integer and String,  and the ordering I have chosen 
    	 * for the binary search tree is to be order by the keys 
    	 * in ascending order (that is to say, Integers). 
    	 * In this case, after creating the BST 
    	 * (code not included here), we this should work:
    	 * 
    	 * Pair<Integer, String> p1 = new Pair(5, "John");
    	 * bst3.insert(p1);
    	 * Pair<Integer, String> p2 = new Pair(3, "Bob");
    	 * bst3.insert(p2);
    	 * Pair<Integer, String> p3 = new Pair(9, "Alice");
    	 * bst3.insert(p3);
    	 * Pair<Integer, String> p4 = new Pair(13, "Mallory");
    	 * bst3.insert(p4);
    	 * Pair<Integer, String> p5 = new Pair(7, "Larry");
    	 * bst3.insert(p5);
    	 * 
    	 * And the output of:
    	 * bst3.inorder();
    	 * 
    	 * will be:
    	 * 
    	 * Pair[key=3, value=Bob] Pair[key=5, value=John] \
    	 *  Pair[key=7, value=Larry] Pair[key=9, value=Alice] \
    	 *  Pair[key=13, value=Mallory]
    	 * 
    	 */
    	
    	System.out.println();
    	System.out.println("bst3:");
    	Comparator<Pair<Integer, String>> pairComparator = new Comparator<Pair<Integer, String>>() {
            @Override
            public int compare(Pair<Integer, String> p1, Pair<Integer, String> p2) {
                return p1.getKey().compareTo(p2.getKey());
            }
        };
    	BinarySearchTree<Pair<Integer, String>> bst3 = new BinarySearchTree<>(pairComparator);

    	Pair<Integer, String> p1 = new Pair<>(5, "John");
   	 	bst3.insert(p1);
   	 	Pair<Integer, String> p2 = new Pair<>(3, "Bob");
   	 	bst3.insert(p2);
   	 	Pair<Integer, String> p3 = new Pair<>(9, "Alice");
   	 	bst3.insert(p3);
   	 	Pair<Integer, String> p4 = new Pair<>(13, "Mallory");
   	 	bst3.insert(p4);
   	 	Pair<Integer, String> p5 = new Pair<>(7, "Larry");
   	 	bst3.insert(p5);
   	 	
   	 	bst3.inorder();
    	
    	
    }
}