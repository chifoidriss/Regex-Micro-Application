package com.automate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Afn_En_Afd {
     
	private ArrayList<Character> symbols; //symbols table
	private int symbols_size; //symbols table size
    private ArrayList<Integer>[][] tran; // this is a transition table of AFN
    private ArrayList<Integer> Netats_final; // this is a table of AFN acceptance state
    private ArrayList<ArrayList<Integer>>[] Dtran; // AFD transitions
    private int Dtran_simplifier[][];
    private HashMap<Integer, ArrayList<Integer>> alias; // Alias of array of states of states array
    private ArrayList<ArrayList<Integer>> etats; // AFD states
    private ArrayList<ArrayList<Integer>> Detats_final; // AFD acceptance states
    private ArrayList<Integer> etats_final_mini; // AFD acceptance states
    
    /**
     * constructor
     */

    public Afn_En_Afd(ArrayList<Integer>[][] tran, ArrayList<Integer> Netats_final, ArrayList<Character> symbols){
    	
    	  this.symbols = new ArrayList<>();
    	  this.symbols = symbols;
    	  this.symbols_size = this.symbols.size();
    	  this.tran = tran;
    	  this.Netats_final = Netats_final;
    	  this.Dtran = new ArrayList[this.symbols_size];
    	  this.alias = new HashMap<>();
    	  this.setEtats(new ArrayList<>());
    	  this.Detats_final = new ArrayList<>();
    	  this.etats_final_mini = new ArrayList<>();
    }  
    
    
	/**
     * FUNCTIONS
     */


    public void afn_En_Afd(){
        
       
        
        Queue<ArrayList<Integer>> etats_non_marques = new ConcurrentLinkedQueue<>() ; // Queue contenant l'ens des �tats non marqu�s
        
        ArrayList<Integer> etat_a_marquer = new ArrayList<Integer>();//etat � marqu�
        
        ArrayList<Integer> U = new ArrayList<Integer>();// est le r�sultat de la epsilon fermeture de trasit�
        
        char symbol;

        ArrayList<Integer> q0 = new ArrayList<Integer>();q0.add(0);//etat initial
        
        
        ArrayList<Integer> A = epsilon_fermeture(q0);// epsilon_fermeture de  �tat initial
        
        for(int i=0;i<this.Dtran.length;i++){ // initialisation de Dtran
            this.Dtran[i] = new ArrayList<>();
        }

    
        etats_non_marques.offer(A); // A est non marqu�
        this.etats.add(A); // A est le premier �tat de l'ens des �tats

        while(etats_non_marques.size() != 0){ // tantqu'il existe un �tat non marqu�
               
             etat_a_marquer = etats_non_marques.poll();// d�filellage de l' �tat en tete de fille pour le marquer
            
             this.Dtran[0].add(etat_a_marquer); //marquage de l'�tat defiller

             
             for(int i=0;i<this.symbols_size-1;i++){ // pour chaque symbol
               
                symbol = this.symbols.get(i); // r�cup�ration de symbols
               
                U = epsilon_fermeture(transiter(etat_a_marquer,symbol)); //on calcul epsilon fermeture de transiter
                
                if(!in_array(this.etats, U)){ // si U n'est pas le tableau 
                    
                	if(!U.isEmpty()) {
                       this.etats.add(U); // On ajoute U comme nouveaux etat � etats
                       etats_non_marques.offer(U); // et aussi � la file des �tats qui attendent d'etre marqu�s
                    }
                    

                }
                this.Dtran[this.symbols.indexOf(symbol)+1].add(U);// On ajoute  U � Dtran 
             }
        
        }
        
        etats_acceptation_AFD();
        make_Alias();
        simplifie_Dtran();
        
     }
    
    
    void etats_acceptation_AFD() {
    	   
    	   for(int i=0;i<this.Netats_final.size();i++) {
    		   
    		   for(ArrayList<Integer> array:this.etats) {
    			   
    				   if(in_array(array, this.Netats_final.get(i))) {
    					   
    					   if(!in_array(this.Detats_final, array)) {
    						  
    						   this.Detats_final.add(array);
    						   this.etats_final_mini.add(this.etats.indexOf(array));
    						   
    					   }
    				   }
    			   
    		   }
    	   }
    }
    
    void make_Alias() {
    	 
    	 for(int i=0;i<this.etats.size();i++) {
    		 this.alias.put(i,this.etats.get(i));
    	 }
    	
    }
    
    void simplifie_Dtran() {
    	   
    	this.Dtran_simplifier = new int[this.etats.size()][this.symbols_size-1];
    	   
    	   for (int i = 1; i < Dtran.length; i++) {
    		   int curent_size = Dtran[i].size();
			  
    		    for (int j = 0; j < curent_size; j++) {
				  
				  this.Dtran_simplifier[j][i-1]=this.etats.indexOf(Dtran[i].get(j));
			
    		    }
		   }
    }
    
    ArrayList<Integer> epsilon_fermeture(ArrayList<Integer> T){
        
        
        ArrayList<Integer> epsilon_fermeture = new ArrayList<>();
        
        if(!T.isEmpty()){
            
            Stack<Integer> pile = new Stack<>(); // pile contenant les etats � trouver la epsilon-fermeture
            ArrayList<Integer> cur_epsi_tran_set = new ArrayList<>();
            int etatDepiler=0;

             /**
              * Initialisation de epsi_fer de T � T  et copie des elements dans une pile
              */

             for(int x:T){
                pile.push(x);
                epsilon_fermeture.add(x);
             }
            
             /**
              *  parcourt de la pile et traitement des etats de la pile
              */

             while (!pile.empty()) {
                 
                 etatDepiler = pile.pop();  // on depile un etat
                 cur_epsi_tran_set = this.tran[etatDepiler][this.symbols.indexOf('$')]; // array contenant eventuellement les etats atteint par epsilon 
                 
                 if (!cur_epsi_tran_set.isEmpty()) {
                     
                    for(int x:cur_epsi_tran_set){ // On parcourt le tableau des epsilons transition courante correspondant etatDepiler
                        
                        if (!in_array(epsilon_fermeture,x)) { // si x n appartient pas � l'epsilon fermeture
                            epsilon_fermeture.add(x); //on ajoute x � l epsilon fermeture de T
                            
                            pile.push(x); //On empile le nouvelle etat pour trouver ses epsilon fermeture
                        }
                        
                     }
                 }
             }
     
        }
        return epsilon_fermeture;
    }
   
    ArrayList<Integer> transiter(ArrayList<Integer> T, char symbol){
       
       ArrayList<Integer> transiter = new ArrayList<>();
          
          if(!T.isEmpty()){
        	  
             ArrayList<Integer> etats_transiter = new ArrayList<>();
             
             for(int x:T){
                  etats_transiter = tran[x][this.symbols.indexOf(symbol)]; //etat issu de la transition du symbol X
                  
                  if (!etats_transiter.isEmpty()) { // x pos�de une transition suivant symbol
                      
                      for(int etat:etats_transiter){ // s'il n'est d�ja dans le tableau de transiter
                    	  
                    	  if(!in_array(transiter,etat))
                            transiter.add(etat);  // si oui on ajoute un � transiter
                      }
                  }
             }
          }

       return transiter;

    }
    
    

    
    boolean in_array(ArrayList<Integer> arr,int c){
      
      if(!arr.isEmpty()) {
    	  
         for (int i=0;i< arr.size();i++){
             if (arr.get(i) == c ){
                return true;
             }
         }
      }
        return false;
    }

    boolean in_array(ArrayList<ArrayList<Integer>> arr,ArrayList<Integer> a){
        
    	if(!arr.isEmpty()) {
    		
          for (int i=0;i< arr.size();i++){
              if (are_equals(arr.get(i), a)){
                  return true;
              }
          }
          
    	}
    	
        return false;
    }
    
    boolean are_equals(ArrayList<Integer> arr1,ArrayList<Integer> arr2){

        if(!(arr1.size() != arr2.size())){
            int n = arr1.size(),i = 0;
            
            while(i<n){
                     
                     if(arr1.get(i) == arr2.get(i)){
                         i++;
                     }
                     else
                         return false;
            }

            return true;
        }

        return false;
    }

    void afficheDtran() {
    	int i=0;
    	while(i<this.Dtran.length) {
            ArrayList<ArrayList<Integer>> etats=this.Dtran[i];
            System.out.println("\n----> nouvelle colone \n");
            for(ArrayList<Integer> array:etats) {
            	System.out.println(array);
            }
           i++;
        }
    }
    
    
    /**
     * GETTERS AND SETTERS
     * @return
     */
    
    
    
    public ArrayList<Character> getSymbols() {
		return symbols;
	}
    
    
	public void setSymbols(ArrayList<Character> symbols) {
		this.symbols = symbols;
	}
	
	
	public ArrayList<Integer>[][] getTran() {
		return tran;
	}
	
	
	public void setTran(ArrayList<Integer>[][] tran) {
		this.tran = tran;
	}


	public ArrayList<ArrayList<Integer>>[] getDtran() {
		return Dtran;
	}


	public void setDtran(ArrayList<ArrayList<Integer>>[] dtran) {
		Dtran = dtran;
	}



	public ArrayList<ArrayList<Integer>> getEtats() {
		return etats;
	}


	public void setEtats(ArrayList<ArrayList<Integer>> etats) {
		this.etats = etats;
	}
     
	public ArrayList<ArrayList<Integer>> getDetats_final() {
		return Detats_final;
	}


	public void setDetats_final(ArrayList<ArrayList<Integer>> Detats_final) {
		this.Detats_final = Detats_final;
	}

	public ArrayList<Integer> getNetats_final() {
		return Netats_final;
	}


	public void setNetats_final(ArrayList<Integer> netats_final) {
		Netats_final = netats_final;
	}

	public HashMap<Integer, ArrayList<Integer>> getAlias() {
		return alias;
	}


	public void setAlias(HashMap<Integer, ArrayList<Integer>> alias) {
		this.alias = alias;
	}


	public int[][] getDtran_simplifier() {
		return Dtran_simplifier;
	}


	public void setDtran_simplifier(int dtran_simplifier[][]) {
		Dtran_simplifier = dtran_simplifier;
	}


	public ArrayList<Integer> getEtats_final_mini() {
		return etats_final_mini;
	}


	public void setEtats_final_mini(ArrayList<Integer> etats_final_mini) {
		this.etats_final_mini = etats_final_mini;
	}
}
