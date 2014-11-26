import java.io.StringReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.shiftreduce.ShiftReduceParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.Tree;


public class Main {
	Set<String> PUNCS = new HashSet<String>(Arrays.asList(new String[] {",",";"}));
	
	public void markEnum(CSDTree node){
		
		if (node.isLeaf())
			return;
		
		if(PUNCS.contains(node.value())){
			return;
		}
		
		boolean ifHavePunc = false;
		String uniqueTag = "";
		boolean success = true;
		int countText = 0;
		
		for (int i = 0; i < node.numChildren(); i++){
			
			CSDTree child = (CSDTree)node.getChild(i);
			if (PUNCS.contains(child.value())){ //if child is cc or punc
				ifHavePunc = true;
			}else{
				// else check if the children's csdtag are all of the same type
				String value = child.value();
				if (uniqueTag == ""){
					uniqueTag = value;
					countText++;
				}else{
					if (uniqueTag != value){
						success = false;
						break;
					}
				}
				
			}
			
		}
		
		// if the children are of the same type. but interleaved by punctuation or conjunctive constructions,
		// mark this ENUM
		if (success && ifHavePunc && uniqueTag!=""&&countText>=2)
			node.setCsdTag("ENUM");
					
		for (int i = 0; i < node.numChildren(); i++){
			markEnum((CSDTree)node.getChild(i));	
		}				
	}
	
	public CSDTree TreeToCSDTree(Tree tree){
		CSDTree csd = new CSDTree();
		csd.setValue(tree.value());
		
		if(!tree.isLeaf()){
			int size = tree.getChildrenAsList().size();
			for(int i = 0; i < size; i++){
				Tree child = tree.getChild(i);	
				csd.add(TreeToCSDTree(child));
			}
		}
		
		return csd;
		
	}
	
	public static void main(String[] args) {
		String modelPath = "edu/stanford/nlp/models/srparser/englishSR.ser.gz";
	    String taggerPath = "english-left3words-distsim.tagger";

	 
	    String text = "My dog likes to shake his stuffed chickadee toy.";

	    MaxentTagger tagger = new MaxentTagger(taggerPath);
	    ShiftReduceParser model = ShiftReduceParser.loadModel(modelPath);

	    DocumentPreprocessor tokenizer = new DocumentPreprocessor(new StringReader(text));
	    for (List<HasWord> sentence : tokenizer) {
	      List<TaggedWord> tagged = tagger.tagSentence(sentence);
	      Tree tree = model.apply(tagged);
	      
	      Tree tree_copy = tree.deepCopy();
	      
	      System.err.println(tree);
	    }

	}

}
