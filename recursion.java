/************************************************************
Coder: Wonho Lim
Class: Advanced Computer Science
Date: 2020.11.30
Description: Recursive method practice.
************************************************************/
public class recursion {
   public static void main(String[] args) {
     int so =  chalupa("parangaricutirimicuaro",'a');
     System.out.println(so);
}

public static int chalupa( String n, char c ) { 
   if ( n.length() == 1  && n.charAt(0) == c ) return 1; 
   if ( n.length() == 1  && n.charAt(0) != c ) return 0;         
   else { 
      if( n.charAt(0) == c ) {
         return 1 + chalupa( n.substring(1), c ); 
      } 
      else return chalupa( n.substring(1), c ); 
      } 
   }
}