package com.automate;

public class Table<Type> {
	int nb_lignes,nb_colones;
	Type [][] table=null;
	@SuppressWarnings("unchecked")
	public Table(int nb_lignes, int nb_colones) {
		this.setNb_lignes(nb_lignes);
		this.setNb_colones(nb_colones);
		table = (Type[][])new Object[this.nb_lignes][this.nb_colones];
	}
	public int getNb_lignes() {
		return nb_lignes;
	}
	public void setNb_lignes(int nb_lignes) {
		this.nb_lignes = nb_lignes;
	}
	public int getNb_colones() {
		return nb_colones;
	}
	public void setNb_colones(int nb_colones) {
		this.nb_colones = nb_colones;
	}
	public Type[][] getTable() {
		return table;
	}
	public void setTable(Type[][] table) {
		this.table = table;
	}
	public void init(int i, int j, Type obj) {
		try {
			table[i][j] = obj;
		}catch(IndexOutOfBoundsException e) {
			System.out.println("indices errones");
		}
	}
	
	public Type get(int i, int j) {
		if(i < nb_lignes && i >=0 && j < nb_colones && j >=0)
			return table[i][j];
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public void setTable(Table<Type> tab) {
		this.nb_colones=tab.getNb_colones();
		this.nb_lignes=tab.getNb_lignes();
		table = (Type[][])new Object[this.getNb_lignes()][this.getNb_colones()];
		for(int i=0; i<tab.getNb_lignes(); i++) {
			for(int j=0; j<tab.getNb_colones(); j++) {
				table[i][j] = tab.get(i, j);
			}
		}
	}
	
	public void affiche() {
		System.out.print("\n");
		for (int i=0; i<this.nb_lignes; i++) {
			for(int j=0; j<this.getNb_colones(); j++) {
				System.out.print(table[i][j]);
			}
			System.out.print("\n");
		}
	}
}
