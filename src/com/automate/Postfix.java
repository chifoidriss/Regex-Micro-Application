package com.automate;

public class Postfix {
	private Stack theStack;
	private String input, output = "";
	
	public Postfix(String in) {
		input = in;
		
		int stackSize = input.length();
	    theStack = new Stack(stackSize);
	}
	
	public String getOutput() {
		return output;
	}
	
	public void setOutput(String output) {
		this.output = output;
	}
	
	public String doTrans() {
      for (int j = 0; j < input.length(); j++) {
         char ch = input.charAt(j);
         switch (ch) {
            case '+': 
            case '|':
            	gotOper(ch, 1); 
            	break; 
            case '*': 
            	gotOper(ch, 3); 
            	break;
            case '.':
            	gotOper(ch, 2); 
            	break; 
            case '(': 
            	theStack.push(ch);
            	break;
            case ')': 
            	gotParen(ch); 
            	break;
            default: 
	               output = output + ch; 
	            break;
         }
      }
      while (!theStack.isEmpty()) {
         output = output + theStack.pop();
      }
      return output; 
   }
   public void gotOper(char opThis, int prec1) {
	   while (!theStack.isEmpty()) {
         char opTop = theStack.pop();
	         if (opTop == '(') {
	            theStack.push(opTop);
	            break;
	         } else {
	            int prec2;
	            if (opTop == '+' || opTop == '|')
	            prec2 = 1;
	            else
	            prec2 = 2;
	            if (prec2 < prec1) { 
	               theStack.push(opTop);
	               break;
	            } 
	            else output = output + opTop;
	         }
	      }
	      theStack.push(opThis);
	   }
	   public void gotParen(char ch) { 
	      while (!theStack.isEmpty()) {
	         char chx = theStack.pop();
	         if (chx == '(') 
	         break; 
	         else output = output + chx; 
	      }
	   }
	  
   class Stack {
      private int maxSize;
      private char[] stackArray;
      private int top;
      
      public Stack(int max) {
         maxSize = max;
         
         stackArray = new char[maxSize];
         top = -1;
      }
      public void push(char j) {
         stackArray[++top] = j;
      }
      public char pop() {
         return stackArray[top--];
      }
      
      public char peek() {
         return stackArray[top];
      }
      public boolean isEmpty() {
         return (top == -1);
      }
   }
}

