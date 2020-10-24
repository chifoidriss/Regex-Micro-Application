package com.automate;

import java.util.ArrayList;
import java.util.Scanner;

public class Automate {
        static Scanner scanner = new Scanner(System.in);

	static ArrayList<Character> extract_symbols(String expr){	        
	        ArrayList<Character> symbols = new ArrayList<>();
	        for (int i = 0; i < expr.length(); i++) {
	            char c = expr.charAt(i);
	            if (c != '*' && c != '|' && c != '.' && c != '(' && c != ')') {
	                if (!in_array(symbols, c))
	                    symbols.add(c);
	            }
	        }
	        symbols.add('$');

	        return symbols;
	  }
	 
	 static boolean in_array(ArrayList<Character> arr,char c){

	        for (int i=0;i< arr.size();i++){
	            if (arr.get(i) == c ){
	                return true;
	            }
	        }
	        return false;
	    }
  
}
