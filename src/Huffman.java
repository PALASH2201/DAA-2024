import java.util.*;

class Node{
    int freq ;
    char c;

    Node left;
    Node right;
}
class MyComparator implements Comparator<Node>{

    @Override
    public int compare(Node o1, Node o2) {
        return  o1.freq - o2.freq;
    }
}

public class Huffman {
    public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of distinct characters:");
        int n =sc.nextInt();
        System.out.println("Enter the distinct characters:");
        char[] c = sc.next().toCharArray();
        int[] freq = new int[n];
        String[] codedChars = new String[n];
        System.out.println("Enter the frequencies of each character:");
        for(int i=0;i<n;i++){
            freq[i] = sc.nextInt();
        }
        Node tree_root = buildHuffmanTree(freq,c,n);
        for(int i=0;i<n;i++){
            codedChars[i] = getString(tree_root,"",c[i]);
        }
        for(int i=0;i<n;i++){
            System.out.println(c[i]+" = "+ codedChars[i]);
        }
    }
    public static Node buildHuffmanTree(int[] freq , char[] c,int n){
        PriorityQueue<Node> q = new PriorityQueue<>(n,new MyComparator());
        for(int i=0;i<n;i++){
            Node new_node = new Node();
            new_node.freq = freq[i];
            new_node.c = c[i];
            new_node.left=null;
            new_node.right=null;

            q.add(new_node);
        }
        Node root = null;
        while(q.size() > 1){
            Node x =q.peek();
            q.poll();

            Node y = q.peek();
            q.poll();

            Node new_node = new Node();
            new_node.freq = x.freq + y.freq;
            new_node.c = '-';

            new_node.left = x;
            new_node.right = y;

            root = new_node;

            q.add(new_node);
        }
        return root;
    }

    public static String getString(Node root,String s,char a){
        if(root == null)
            return "";
        if (root.left == null && root.right ==null && root.c == a)
            return s;
        String leftPrefix = getString(root.left,s+"0",a);
        if(!leftPrefix.isEmpty())
            return leftPrefix;
        String rightPrefix = getString(root.right,s+"1",a);
        if(!rightPrefix.isEmpty())
            return rightPrefix;

        return "";
    }
}
