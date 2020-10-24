package com.view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.SwingConstants;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

public class DrawAutomate {
	
	private int[][] Dtran_mini;
	private ArrayList<Integer> etats_final;
	private ArrayList<Integer>[][] Dtran;
	private ArrayList<Character> symbols;
	private ArrayList<Integer> etats_final_mini;
	private BufferedImage image;
	
	
	/**
	 * CONSTRUCTOR
	 */
	
	public DrawAutomate(int[][] Dtran_mini,ArrayList<Character> symbols,ArrayList<Integer> etats_final_mini) {
	     
		 this.setDtran_mini(Dtran_mini);
		 this.setSymbols(symbols);
		 this.etats_final_mini = etats_final_mini; 
		 
		 
	}

    public DrawAutomate(ArrayList<Integer>[][] Dtran,ArrayList<Character> symbols,ArrayList<Integer> etats_final) {

        this.setDtran_mini(Dtran_mini);
        this.setSymbols(symbols);
        this.etats_final = etats_final;
        this.Dtran = Dtran;

    }

	
	public BufferedImage painAFn() {
		
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		
		// Sets the default vertex style
		Map<String, Object> style = graph.getStylesheet().getDefaultVertexStyle();
		style.put(mxConstants.STYLE_FILLCOLOR, "#48dbfb");
       // style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);

		String stylesec =mxConstants.STYLE_SHAPE  +"="+mxConstants.SHAPE_ELLIPSE;
        String stylesec2 = mxConstants.STYLE_SHAPE +"="+mxConstants.SHAPE_DOUBLE_ELLIPSE;

		ArrayList<Object> vertex = new ArrayList<>();
		
		graph.getModel().beginUpdate();
		
		try
		{
			for(int i=0;i<this.Dtran.length;i++) {
				
				if(!in_array(this.etats_final, i))
				{
				   vertex.add(graph.insertVertex(parent, null, "Q"+i, 0, 0, 45, 45,stylesec));
				}
				else
				{
					vertex.add(graph.insertVertex(parent, null, "Q"+i, 0, 0, 45, 45,stylesec2));
				}
				
			}

			char etiquette = ' ';

			for(int i=0;i<this.Dtran.length;i++) {
				
				for(int j=0;j<this.symbols.size();j++) {

					ArrayList<Integer> cibles = Dtran[i][j];

					if (cibles.size() != 0) {

                        for (int c = 0; c < cibles.size(); c++) {

                                if(symbols.get(j) == '$')
                                    etiquette = 'ɛ';
                                else
                                    etiquette = symbols.get(j);

                                graph.insertEdge(parent, null,etiquette, vertex.get(i), vertex.get(cibles.get(c)));
                        }

                    }
				}
				
			}
			
			mxIGraphLayout layout = new mxHierarchicalLayout(graph,SwingConstants.WEST);
			layout.execute(parent);
		}
		finally
		{
			graph.getModel().endUpdate();
		}
		
		image = mxCellRenderer.createBufferedImage(graph, null,1, Color.WHITE, true, null);
		
     	
     	return this.image;
	}


	public BufferedImage paintAFd(){

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        // Sets the default vertex style
        Map<String, Object> style = graph.getStylesheet().getDefaultVertexStyle();
        style.put(mxConstants.STYLE_FILLCOLOR, "#48dbfb");

		String stylesec =mxConstants.STYLE_SHAPE  +"="+mxConstants.SHAPE_ELLIPSE;
		String stylesec2 = mxConstants.STYLE_SHAPE +"="+mxConstants.SHAPE_DOUBLE_ELLIPSE;

        ArrayList<Object> vertex = new ArrayList<>();

        graph.getModel().beginUpdate();

        try {
            for(int i=0;i<this.Dtran_mini.length;i++) {

                if(!in_array(this.etats_final_mini, i))
                {
                    style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
                    vertex.add(graph.insertVertex(parent, null, "Q"+i, 0, 0, 45, 45,stylesec));
                }
                else
                {
                    vertex.add(graph.insertVertex(parent, null, "Q"+i, 0, 0, 45, 45,stylesec2));
                }
            }
            for(int i=0;i<this.Dtran_mini.length;i++) {
                for(int j=0;j<this.symbols.size()-1;j++) {
                    int cible = Dtran_mini[i][j];
                    if(cible != -1)
                        graph.insertEdge(parent, null,symbols.get(j),vertex.get(i),vertex.get(cible));
                }
            }
			mxIGraphLayout layout = new mxHierarchicalLayout(graph,SwingConstants.WEST);
			layout.execute(parent);
        }
        finally {
            graph.getModel().endUpdate();
        }
        image = mxCellRenderer.createBufferedImage(graph, null,1, Color.WHITE, true, null);
        return this.image;
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

	public int[][] getDtran_mini() {
            return Dtran_mini;
	}

	public void setDtran_mini(int[][] dtran_mini) {
		Dtran_mini = dtran_mini;
	}

	public ArrayList<Character> getSymbols() {
		return symbols;
	}

	public void setSymbols(ArrayList<Character> symbols) {
		this.symbols = symbols;
	}

	public ArrayList<Integer> getEtats_final_mini() {
		return etats_final_mini;
	}

	public void setEtats_final_mini(ArrayList<Integer> etats_final_mini) {
		this.etats_final_mini = etats_final_mini;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
