package textgen;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText) {
		// TODO: Implement this method
		if (wordList.isEmpty()){
			String[] words = sourceText.split("[\\s]+");
			starter = words[0];
			String prevWord = starter;
			for (int i = 1; i < words.length; i++) {
				boolean found = false;
				String w = words[i];
				for (ListNode ln : wordList) {
					if (prevWord.equals(ln.getWord())) {
						ln.addNextWord(w);
						found = true;
						//System.out.println(ln.nextWords);
						break;
					}
				}
				if (!found) {
					ListNode ln = new ListNode(prevWord);
					ln.addNextWord(w);
					wordList.add(ln);
				}
				prevWord = w;
			}
			boolean found = false;
			for (ListNode ln : wordList) {
				if (prevWord.equals(ln.getWord())) {
					ln.addNextWord(starter);
					break;
				}
			}
			if (!found) {
				ListNode newNode = new ListNode(prevWord);
				newNode.addNextWord(starter);
				wordList.add(newNode);
			}

		}
		
	}

	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		// TODO: Implement this method
		if (numWords == 0 || wordList.isEmpty()) {
			return "";
		}
		int l = 1;
		String currWord = starter;
		StringBuffer output = new StringBuffer("");
		output.append(currWord);
		while (l < numWords) {
			// System.out.println("W List :"+wordList);
			for (ListNode ln : wordList) {

				if (currWord.equals(ln.getWord()))

				{
					String nextWord = ln.getRandomNextWord(this.rnGenerator);
					// System.out.println(nextWord);
					output.append(" " + nextWord);
					currWord = nextWord;
					l++;
					break;
				}
			}
		}
		return output.toString();
	}

	// Can be helpful for debugging
	@Override
	public String toString() {
		String toReturn = "";
		for (ListNode n : wordList) {
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText) {
		this.wordList.clear();
		this.starter = ""; 
		this.train(sourceText);
		// TODO: Implement this method.
		
	}

	// TODO: Add any private helper methods you need here.

	/**
	 * This is a minimal set of tests. Note that it can be difficult to test
	 * methods/classes with randomized behavior.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		// gen.train("hi there hi Leo");
		// System.out.println(gen);
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		//textString = "hi there hi Leo";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, " + "You say stop, and I say go, go, go, "
				+ "Oh no. You say goodbye and I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "I say high, you say low, "
				+ "You say why, and I say I don't know. " + "Oh no. "
				+ "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "Why, why, why, why, why, why, "
				+ "Do you say goodbye. " + "Oh no. " + "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "You say yes, I say no, "
				+ "You say stop and I say go, go, go. " + "Oh, oh no. "
				+ "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));

	}

}

/**
 * Links a word to the next words in the list You should use this class in your
 * implementation.
 */
class ListNode {
	// The word that is linking to the next words
	private String word;

	// The next words that could follow it
	public List<String> nextWords;

	ListNode(String word) {
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord() {
		return word;
	}

	public void addNextWord(String nextWord) {
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator) {
		// TODO: Implement this method
		// The random number generator should be passed from
		// the MarkovTextGeneratorLoL class
		//Debugging be like :p 
		// System.out.println(nextWords.size());
		//System.out.println(nextWords);
		int rn = generator.nextInt(nextWords.size());
		// System.out.println(rn);
		return this.nextWords.get(rn);
	}

	public String toString() {
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

}
