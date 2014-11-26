package main;

import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeFactory;

public class CSDTree extends Tree{
	private String csdTag="";
	
	public String getCsdTag() {
		return csdTag;
	}

	public void setCsdTag(String csd_tag) {
		this.csdTag = csd_tag;
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
