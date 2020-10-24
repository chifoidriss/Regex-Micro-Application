package com.automate;
import java.util.ArrayList;

public class AutomateD {
	
	ArrayList<Character>alphabet;
	ArrayList<Integer> etats;
	ArrayList<Integer> Init_etat;
	ArrayList<Integer> F_etat;
	Table<ArrayList<Integer>>transition;
	final static String operateur = "+|.()*"; 
	public AutomateD() {
		alphabet=new ArrayList<Character>();
		etats=new ArrayList<Integer>();
		Init_etat=new ArrayList<>();
		F_etat=new ArrayList<>();
		transition=new Table<>(2,1);
	}
	
	public AutomateD(String str) {
		AutomateD aut = new AutomateD();
		aut=this.transit(str);
		this.alphabet=aut.getAlphabet();
		this.etats=aut.getEtats();
		this.Init_etat=aut.getInit_etat();
		this.F_etat=aut.getF_etat();
		this.transition=aut.getTransition();
	}
	
	private Boolean isOperator(char c)  {
		return (operateur.indexOf(c)!=-1);
	}
	
	public AutomateD auto_symbole(char c) {
	    AutomateD auto = new AutomateD();
	    auto.getAlphabet().add(c);
	    ArrayList<Integer> tmp = new ArrayList<Integer>();
	    tmp.add(1);
	    auto.getTransition().init(0, 0, tmp);
	    auto.getInit_etat().add(0);
	    auto.getF_etat().add(1);
	    auto.getEtats().add(0);
	    auto.getEtats().add(1);
		return auto;
	 }
	
	private AutomateD etoile(AutomateD a) {
		AutomateD result=new AutomateD();
		result.setAlphabet(a.getAlphabet());
		//initialisation des etats de result
		for(int i=0; i<a.getEtats().size()+2; i++)
			result.getEtats().add(i);
		Table<ArrayList<Integer>> trans_res = new Table<>(a.getTransition().getNb_lignes()+2,result.getAlphabet().size());
		//initialisation de la premier ligne de la table de result
		ArrayList<Integer> tmp1 = new ArrayList<>();
		tmp1.add(1);
		tmp1.add(trans_res.getNb_lignes()-1);
		for(int j=0; j<trans_res.getNb_colones(); j++)
			trans_res.init(0, j, tmp1);
		//ajout de l'automate de a
		Table<ArrayList<Integer>> tmp2 = new Table<>(a.getTransition().getNb_lignes(),a.getTransition().getNb_colones());
		tmp2.setTable(shift(a.getTransition(), 1));
		//recopie dans trans_res
		int e=0,f=0;
		for(int i=1;i<trans_res.getNb_lignes()-1; i++) {
			for(int j=0; j<a.getTransition().getNb_colones();j++) {
				//System.out.println("\n"+tmp2.get(i,j));
				trans_res.init(i, j,tmp2.get(e,f));
				f++;
			}
			f=0;
			e++;
		}
		//remplissage de la derniere ligne
		for(int j=0; j<trans_res.getNb_colones(); j++)
			trans_res.init(a.getTransition().getNb_lignes(), j, tmp1);
		result.setInit_etat();
		result.setF_etat();
		result.getTransition().setTable(trans_res);
		return result;
	}

	public void initial(ArrayList<Integer> [][] t) {
		for(int i=0;i<t.length;i++){
			for(int j=0;j<t[i].length;j++){
				t[i][j] = new ArrayList<Integer>();
			}
		}
	}
	public ArrayList<Integer> [][] copy() {
		ArrayList<Integer>[][] tran=new ArrayList[this.getEtats().size()][this.getAlphabet().size()];
		initial(tran);
		     for(int i=0; i<this.getTransition().nb_lignes;i++)
		     {
				 for(int j=0; j<this.getTransition().nb_colones;j++)
				 {
                       if(getTransition().get(i,j)!=null)
                       {
						   for(int k=0;k<this.getTransition().get(i,j).size();k++)
						   {
							   tran[i][j].add(this.getTransition().get(i,j).get(k));
						   }
					   }
				 }
			 }
		return tran;
	}

	@SuppressWarnings("unchecked")
	private AutomateD and(AutomateD b, AutomateD a) {
		AutomateD result=new AutomateD();
		ArrayList<Object> alpha_index=new ArrayList<Object>();
		alpha_index = copie(a.getAlphabet(), b.getAlphabet());
		ArrayList<Character> alpha = new ArrayList<>();
		alpha = (ArrayList<Character>) alpha_index.get(0);
		result.setAlphabet(alpha);
		ArrayList<Integer> index=new ArrayList<>();
		index=(ArrayList<Integer>) alpha_index.get(1);
		for(int i=0; i<a.getEtats().size()+b.getEtats().size()-1;i++)
			result.getEtats().add(i);
		
		Table<ArrayList<Integer>> trans_res = new Table<>(result.getEtats().size(),result.getAlphabet().size());	
		//recopier la table de a
		int i=0,j=0;
		for(i=0;i<a.getTransition().getNb_lignes()-1;i++) {
			for(j=0;j<a.getTransition().getNb_colones();j++) 
			{
				//System.out.println("\nici1");
				trans_res.init(i, j, a.getTransition().get(i, j));
			}
		}
		//on decale b et on le recopie
		Table<ArrayList<Integer>> tmp_b = new Table<>(b.getTransition().getNb_lignes(),result.getTransition().getNb_colones());
		tmp_b.setTable(shift(b.getTransition(), a.getTransition().getNb_lignes()-1));
		if(index.isEmpty()) {
			//System.out.println(index);
			int e=0, f=0;
			for(int k=i;k<trans_res.getNb_lignes();k++) 
			{
				for(int l=j;l<trans_res.getNb_colones();l++) 
				{
					//System.out.println("\nici2");
					trans_res.init(k, l, tmp_b.get(e, f));
					f++;
				}
				f=0;
				e++;
			}
		}
		else {
			//System.out.println(index);
			int e=0, f=0;
			for(int k=i;k<trans_res.getNb_lignes();k++) 
			{
				for(int l=index.get(0);l<trans_res.getNb_colones();l++) 
				{
					//System.out.println("\nici3");
					trans_res.init(k, l, tmp_b.get(e, f));
					f++;
				}
				f=0;
				e++;
			}
		}
		result.setTransition(trans_res);
		result.setInit_etat();
		result.setF_etat();
		return result;
	}
	
	private ArrayList<Object> copie(ArrayList<Character>a,ArrayList<Character>b) {
		ArrayList<Object> result = new ArrayList<>();
		ArrayList<Integer> index = new ArrayList<>();
		ArrayList<Character>c=new ArrayList<>();
		c.addAll(a);
		for(int i=0;i<b.size();i++) {
			if(!a.contains(b.get(i)))
				c.add(b.get(i));
			else
				index.add(c.indexOf(b.get(i)));
		}
		result.add(c);
		//System.out.println(c);
		//System.out.println(index);
		result.add(index);
		return result;
	}
	
	private void setF_etat() {
		boolean test=true;
		F_etat=new ArrayList<>();
		for(int i=0; i<this.transition.getNb_lignes(); i++) {
			for(int j=0; j<this.transition.getNb_colones(); j++){
				//System.out.print("\nvaleur = "+this.transition.get(i,j));
				if(this.transition.get(i,j) == null)
					test= test && true;
				else
					test=false;
			}
			if(test){
				this.F_etat.add(i);
			}
			test=true;
		}
		//System.out.print("\netats_accept = "+F_etat);
	}
	
	@SuppressWarnings("unchecked")
	private AutomateD or(AutomateD b, AutomateD a) {
		AutomateD result=new AutomateD();
		ArrayList<Object> alpha_index=new ArrayList<Object>();
		alpha_index = copie(a.getAlphabet(), b.getAlphabet());
		ArrayList<Character> alpha = new ArrayList<>();
		alpha = (ArrayList<Character>) alpha_index.get(0);
		result.setAlphabet(alpha);
		//System.out.println("\nalpha = "+alpha);
		ArrayList<Integer> index=new ArrayList<>();
		index=(ArrayList<Integer>) alpha_index.get(1);
		//System.out.println("\nindex = "+index);
		//System.out.print("\n"+result.getAlphabet());
		//initialisation des etats
		for(int i=0; i<a.getEtats().size()+b.getEtats().size()+2;i++)
			result.getEtats().add(i);
		
		Table<ArrayList<Integer>> trans_res = new Table<>(result.getEtats().size(),result.getAlphabet().size());
		
		ArrayList<Integer>tmp1=new ArrayList<>();
		ArrayList<Integer>tmp2=new ArrayList<>();
		tmp1.add(1);tmp1.add(a.getTransition().getNb_lignes()+1);
		tmp2.add(trans_res.getNb_lignes()-1);
		//initializing first line
		for(int j=0; j<trans_res.getNb_colones(); j++)
			trans_res.init(0,j,tmp1);
		Table<ArrayList<Integer>>shift_a=new Table<>(a.getTransition().getNb_lignes(),a.getTransition().getNb_colones());
		shift_a.setTable(shift(a.getTransition(),1));
		Table<ArrayList<Integer>>shift_b=new Table<>(b.getTransition().getNb_lignes(),b.getTransition().getNb_colones());;
		shift_b.setTable(shift(b.getTransition(),a.getTransition().getNb_lignes()+1));
		//decaler et recopier la table de a
		int i=0,j=0,e=0,f=0;
		for(i=1;i<a.getTransition().getNb_lignes();i++)  {
			for(j=0;j<a.getTransition().getNb_colones();j++) 
			{
				trans_res.init(i, j, shift_a.get(e, f));
				f++;
			}
			e++;f=0;
		}
		//complete la derniere ligne de a
		for(int k=0; k<trans_res.getNb_colones(); k++)
			trans_res.init(i,k,tmp2);
		//on decale b et on le recopie
		if(index.isEmpty()) {
			//System.out.println(index);
			int m=0, n=0,k=0,l=0;
			for(k=i+1;k<trans_res.getNb_lignes()-2;k++) 
			{
				for(l=j;l<trans_res.getNb_colones();l++) 
				{
					trans_res.init(k, l, shift_b.get(m, n));
					n++;
				}
				n=0;
				m++;
			}
			for(int z=0; z<trans_res.getNb_colones(); z++)
				trans_res.init(k,z,tmp2);
		}
		else {
			int m=0, n=0,k=0,l=0;
			for(k=i+1;k<trans_res.getNb_lignes()-2;k++) 
			{
				for(l=index.get(0);l<trans_res.getNb_colones();l++) 
				{
					trans_res.init(k, l, shift_b.get(m, n));
					n++;
				}
				n=0;
				m++;
			}
			for(int z=0; z<trans_res.getNb_colones(); z++)
				trans_res.init(k,z,tmp2);
		}
		result.setTransition(trans_res);
		result.setInit_etat();
		result.setF_etat();
		return result;
	}
	
	public boolean isCorrecte(String s) 
	{
		int parent=0;
		int o=0,f=0;
		String op="+|.";
		
		for(int i=0;i<s.length();i++) 
		{
			if(op.indexOf(s.charAt(i))!=-1 &&op.indexOf(s.charAt(i+1))!=-1)
				return false;
			else if(s.charAt(i)=='(' && s.charAt(i+1)==')')
				return false;
			else if(s.charAt(i)=='(')
				o=o+1;
			else if(s.charAt(i)==')')
				f=f+1;
		}
		parent=o-f;
		if(parent==0)
		    return true;
		return false;
	}
	
	public String complete(String s) 
	{
		if(isCorrecte(s)) 
		{
			String st=new String("");
			String ope="+|*().";
			@SuppressWarnings("unused")
			String op="+|*.";
			
			for(int i=0; i<s.length();i++) 
			{
	            if(i<s.length()-1) 
	            {
	            	if(ope.indexOf(s.charAt(i))==-1 &&ope.indexOf(s.charAt(i+1))==-1) 
	                {
	                	st=st+s.charAt(i)+".";
	                }
	                else
	                {
	                	st=st+s.charAt(i);
	                	if(s.charAt(i)=='*' && ope.indexOf(s.charAt(i+1))==-1)
	                		st=st+'.';
	                	else if(s.charAt(i)==')' && ope.indexOf(s.charAt(i+1))==-1)
	                		st=st+'.';
						else if(s.charAt(i+1)=='(' && ope.indexOf(s.charAt(i))==-1)
							st=st+'.';
	                }
	            }
	            else
	            	st=st+s.charAt(i);
			}
			return st;
		}
		else 
		{
			System.out.println("error d'expresion");
			return "";
		}
	}
	 
	public AutomateD transform(AutomateD a)
	{
		AutomateD result=new AutomateD();
		    result.setAlphabet(a.getAlphabet());
		    result.getAlphabet().add('$');
		    Table<ArrayList<Integer>> tmp = new Table<>(a.getEtats().size(),a.getAlphabet().size());
		    for(int i=0;i<a.getEtats().size();i++) 
		    {
		    	for(int j=0;j<a.getAlphabet().size();j++) 
		    	{
		    	    
		    		if(a.getTransition().get(i, j)!=null  &&  a.getTransition().get(i, j).size()>1   ) 
		    		{
		    			//System.out.println(a.getTransition().get(i, j));
		    			tmp.init(i, a.getTransition().getNb_colones(), a.getTransition().get(i, j));
		    		    break;
		    		}
		    		else if(a.getTransition().get(i, j)!=null &&a.getTransition().get(i, j+1)!=null &&  a.getTransition().get(i, j).get(0)==a.getTransition().get(i, j+1).get(0)) 
		    		{
		    			tmp.init(i, a.getTransition().getNb_colones(), a.getTransition().get(i, j));
		    		    break;
		    		}
		    		else
		    		  tmp.init(i, j, a.getTransition().get(i, j));
		    	}
		    }

		    result.setTransition(tmp);
		    result.getInit_etat().addAll(a.getInit_etat());
		    result.getF_etat().addAll(a.getF_etat());
		    result.getEtats().addAll(a.getEtats());
		return result;
	}
	private boolean unaire(char c) 
	{
		return (c=='*');
	}
	
	private boolean binaire(char c) 
	{
		return (c=='.'||c=='+'||c=='|');
	}
	
	private AutomateD transit(String chaine)
	{
		ArrayList<AutomateD>auto=new ArrayList<AutomateD>();
		String str = complete(chaine);
		if(str!="") 
		{

			Postfix pos=new Postfix(str);
			str = pos.doTrans();
			AutomateD a=new AutomateD(),b=new AutomateD();
		    for(int i=0;i<str.length();i++) 
		    {
		    	char c= str.charAt(i);
		    	if(!isOperator(c)) 
		    	{
		    		auto.add(auto_symbole(c));
		    	}
		    	else if(unaire(c)) 
		    	{
		    		a=auto.remove(auto.size()-1);
		    		auto.add(etoile(a));
		    	}
		    	else if(binaire(c)) 
		    	{		
		    		a=auto.remove(auto.size()-1);
		    		b=auto.remove(auto.size()-1);
		    		switch(c) 
		    		{
			    		case '+':
			    		case '|':auto.add(or(a,b));;break;
			    		case '.':auto.add(and(a,b));;break;
		    		}
		    	}
		    	
		    }
			
		}
		AutomateD aut=new AutomateD();
		aut=transform(auto.get(0));
		return aut;
		//return auto.get(0);
	}
	
	private void setInit_etat() {
		// TODO Auto-generated method stub
		this.Init_etat.add(0);
	}

	private Table<ArrayList<Integer>> shift(Table<ArrayList<Integer>> transition, int pas) {
		Table<ArrayList<Integer>> result = new Table<>(transition.getNb_lignes(),transition.getNb_colones());
		result.setTable(transition);
		for(int i=0; i<result.getNb_lignes();i++) {
			for(int j=0; j<result.getNb_colones(); j++)
				result.init(i, j,shift(transition.get(i, j), pas));
		}
		return result;
	}

	private ArrayList<Integer> shift(ArrayList<Integer> arrayList, int pas) {
		ArrayList<Integer> result =null;
		if(arrayList != null) {
			result= new ArrayList<>();
			for(int i=0; i<arrayList.size();i++) {
				result.add(arrayList.get(i)+pas);
			}
		}
		return result;
	}

	public ArrayList<Character> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(ArrayList<Character> alphabet) {
		this.alphabet = alphabet;
	}

	public ArrayList<Integer> getEtats() {
		return etats;
	}

	public void setEtats(ArrayList<Integer> etats) {
		this.etats = etats;
	}

	public ArrayList<Integer> getInit_etat() {
		return Init_etat;
	}

	public void setInit_etat(ArrayList<Integer> init_etat) {
		Init_etat = init_etat;
	}

	public ArrayList<Integer> getF_etat() {
		return F_etat;
	}

	public void setF_etat(ArrayList<Integer> f_etat) {
		F_etat = f_etat;
	}

	public Table<ArrayList<Integer>> getTransition() {
		return transition;
	}

	public void setTransition(Table<ArrayList<Integer>> transition) {
		this.transition = transition;
	}

}
