/************************************************************
Coder: Wonho Lim
Class: Advanced Computer Science
Date: 2020.11.30
Description: This code implements binary tree using Node and
recursion.
************************************************************/
class BinaryTree{
   public Node root;
   public BinaryTree(int height){
      
      if(height <= 0){
         System.out.println("EXIT");
      }
      
      else if(height == 1){
         Node nw = new Node((int)(Math.random()*100) + 1);
         nw.left = null;
         nw.right = null;
         root = nw;
      }
      
      else{
         Node curr = new Node((int)(Math.random()*100) + 1);
         root = curr;
         BinaryTree BT = new BinaryTree(height - 1);
         curr.left = BT.root;
         curr.right = BT.root;
      }
   }
}