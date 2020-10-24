package com.automate;

import java.util.ArrayList;

public class Reconnaissance {
	   
	private int [][] Dtran; // this is a transition table of AFN
	private ArrayList<Character> symbols;
	public  int table_of_symbols_occ[];// occurence of symbols
	private ArrayList<Integer> finalState;
	   
	  
	   
   /**
	* CONSTRUCTOR
	*/
	   
    public Reconnaissance(int [][] Dtran,ArrayList<Character> symbols,ArrayList<Integer> finalState) {
		
    	  this.Dtran = Dtran;
    	  this.symbols = symbols;
    	  this.finalState = finalState;
    	  this.table_of_symbols_occ = new int[symbols.size()-1];
    }

    
    /**
     * 
     * FUNCTIONS
     */
    
    public boolean reconnaissance(String mot){

        if(mot.length() != 0) {
            int etat = 0, i = 0;
            char c = mot.charAt(0);
            System.out.println("(" + etat + "," + mot + ")");
            while (i <= mot.length() - 1) {

                if (symbols.indexOf(c) != -1)
                    etat = Dtran[etat][symbols.indexOf(c)];
                else
                    return false;
                i++;
                if (i != mot.length()) {//if we are no to the end of word
                    c = mot.charAt(i);//we take the next char
                    System.out.println("(" + etat + "," + mot.substring(i) + ")");
                }
            }

            if (in_array(finalState, etat))
                return true;
        }
        return false;
    }
    
    
   public boolean reconnaissance_Avc_execution(String mot){

        if(mot.length() != 0) {

           int[][] actions = new int[this.Dtran.length][this.symbols.size() - 1];

           for (int k = 0; k < symbols.size() - 1; k++) {
               for (int x = 0; x < actions.length; x++) {
                   actions[x][k] = k + 1;
               }
           }

           int etat = 0, i = 0;
           executer(0);
           char c = mot.charAt(0);

           System.out.println("(" + etat + "," + mot + ")");

           while (i <= mot.length() - 1) {

               //System.out.println("c = "+c);

               try {

                   if (symbols.indexOf(c) != -1) {
                       executer(actions[etat][symbols.indexOf(c)]);
                       etat = Dtran[etat][symbols.indexOf(c)];
                   }
               } catch (ArrayIndexOutOfBoundsException e) {
                   return false;
               }


               i++;

               if (i != mot.length()) {//if we are no to the end of word
                   c = mot.charAt(i);//we take the next char
                   System.out.println("(" + etat + "," + mot.substring(i) + ")");
               }

           }


           if (in_array(finalState, etat))
               return true;
       }
        return false;
    }
    
    
    void executer(int a) {
    	
    	if(a==0) 
    	{
    		for (int i = 0; i < table_of_symbols_occ.length; i++) 
    		{
				table_of_symbols_occ[i]=0;
			}
    	}
    	else if(a>0 && a<=symbols.size()-1) 
    	{
    		 //System.out.println("alt 2");
    		 this.table_of_symbols_occ[a-1]=this.table_of_symbols_occ[a-1]+1;
    	}
    	else 
    	{
    		 System.out.println("\nBro, ta merd� !!!");
    	}
    	
    }
    

    boolean in_array(ArrayList<Character> arr,char c){

        for (int i=0;i< arr.size();i++){
            if (arr.get(i) == c ){
                return true;
            }
        }
        return false;
    }
    
    boolean in_array(ArrayList<Integer> arr,int c){

        for (int i=0;i< arr.size();i++){
            if (arr.get(i) == c ){
                return true;
            }
        }
        return false; 
    }
    
    
    /**
     * Getters and setters
     * @return
     */



	public int[][] getDtran() {
		return Dtran;
	}



	public void setDtran(int[][] dtran) {
		Dtran = dtran;
	}


	public ArrayList<Integer> getFinalState() {
		return finalState;
	}


	public void setFinalState(ArrayList<Integer> finalState) {
		this.finalState = finalState;
	}


	public ArrayList<Character> getSymbols() {
		return symbols;
	}


	public void setSymbols(ArrayList<Character> symbols) {
		this.symbols = symbols;
	}

}
