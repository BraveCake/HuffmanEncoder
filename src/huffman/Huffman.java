/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author Ali
 */
public class Huffman {
    public static class Node implements Comparable<Node>
    {
        public int f;
        public Node l,r;
        public char c;
        public Node(int f,char c)
        {
            this.f = f;
            this.c= c;
            l=r=null;
        }
        @Override
        public int compareTo(Node a)
        {
            return this.f- a.f; 
        }
        static ArrayList <Node> stringToNodes(String str)
        {
            HashMap <Character,Integer> freq = new HashMap();
            for(int i=0; i<str.length(); ++i)
            {
                char c = str.charAt(i);
                if(freq.containsKey(c)){
                    int f = freq.get(c);
                    freq.put(c,++f);
                }
                else{
                freq.put(c,1);
                }
                   
            }
            ArrayList<Node> result = new ArrayList<Node> (freq.size());
            int i=0;
            
            for(Map.Entry<Character,Integer> entry: freq.entrySet())
            {
               result.add(i,new Node(entry.getValue(),entry.getKey()));
               ++i;
            }
            return result;
        }
        
        void printRepresentation(Node n,String code)
        {
            if(n==null)
                return;
            if(n.c!='\0'){
                System.out.println("Character:"+n.c +" || Code:"+code);
                return;
            }
            printRepresentation(n.l,code+"0");
            printRepresentation(n.r,code+"1");
        }

        
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Please Enter the value you want to encode");
        Scanner scanner =  new Scanner(System.in);
        String str = scanner.nextLine();
        ArrayList<Node> list = Node.stringToNodes(str);
        PriorityQueue<Node> q = new PriorityQueue<>(list);
        while(q.size()>1)
        {
          Node n1 = q.poll();
          Node n2 = q.poll();    
          Node internal = new Node(n1.f+n2.f, '\0');
          internal.r = n1;
          internal.l= n2;
          q.add(internal);
        }
        Node huffmanTree= q.poll();
        huffmanTree.printRepresentation(huffmanTree, "");
    }
    
}
