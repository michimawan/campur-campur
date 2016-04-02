package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Library.SuffixArray;

public class BWT {
	private String text;
	public BWT() {}
	
	public BWT(String text) {
		this.text = text;
	}
	
	public String compress() {
		return doEncode(this.text);
	}
	
	public String compress(String text) {
		return doEncode(text);
	}
	
	public String doEncode(String text) {
		if(text.length() == 0)
			return "";
		return getLastColumnBySuffixArray(text);
	}
	
	public String getLastColumnBySuffixArray(String text) {
		SuffixArray sa = new SuffixArray(text);
		
		String tmp = "";
		int length = text.length();
		for(int i=0; i<length; i++) {
			int index = (sa.index(i) + length - 1) % length;
			
			int selected = sa.indexToKeys[index];
			tmp += sa.select(selected).charAt(0);
		}
		
		return tmp;
	}
	
	public String decompress(String text) {
		return decode(text);
	}

	public int getNextAddedIndex(String text, int hasXIndexBefore, char desiredChar) {
		int i = 0;
		int desiredIndex = -1;
		do{
			desiredIndex = text.indexOf(desiredChar, desiredIndex+1);
			i++;
		} while(i <= hasXIndexBefore);	
		
		return desiredIndex;
	}
	
	public int hasXIndexBefore(String text, int currentIndex, char currentChar) {
		int tmp = 0;
		int i = 0;
		while(i < currentIndex) {
			i = text.indexOf(currentChar, i);
			
			if(i == currentIndex)
				break;
			else if(i < currentIndex)
				tmp++;
			
			i++;
		}
		return tmp;
	}
	
	public String decode(String text) {
		char [] firstColumn = text.toCharArray();
		Arrays.sort(firstColumn);
		String firstColumnString = new String(firstColumn);
		int indexOfCorrectRow = text.indexOf("|");

		int length = text.length();
		String tmp = "";
		tmp += firstColumn[indexOfCorrectRow];

		int indexCurrentChar = indexOfCorrectRow;
		for(int i=0; i<length - 1; i++) {
			int hasXIndexBefore = hasXIndexBefore(firstColumnString, indexCurrentChar, tmp.charAt(i));
			int nextAddedIndex = getNextAddedIndex(text, hasXIndexBefore, tmp.charAt(i));
			indexCurrentChar = nextAddedIndex;
			
			tmp += firstColumn[nextAddedIndex];
		}
		
		return tmp;
	}
}

