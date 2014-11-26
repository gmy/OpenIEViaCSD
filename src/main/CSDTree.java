package main;

import java.util.ArrayList;

import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeFactory;

public class CSDTree extends Tree{
	private String csdTag = "";
	private int indextInLevel = -1;
	private ArrayList<CSDTree> head = new ArrayList<CSDTree>();
	
	public String getCsdTag() {
		return csdTag;
	}

	public void setCsdTag(String csd_tag) {
		this.csdTag = csd_tag;
	}
	
	public void setIndexInLevel(int index){
		this.indextInLevel = index;
	}
	
	public int getIndexInLevel(){
		return this.indextInLevel;
	}
	
	public void addHead(CSDTree tree){
		head.add(tree);
	}
	
	public CSDTree getFirstHead(){
		if (!head.isEmpty())
			return head.get(0);
		else
			return null;
	}
	
	public ArrayList<CSDTree> getAllHeads(){
		return head;
	}

	@Override
	public Tree[] children() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeFactory treeFactory() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
