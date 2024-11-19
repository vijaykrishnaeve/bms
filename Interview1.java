/**
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 */
public class Interview1 {
	
	
	
	private static ArrayList<Integer> numbers = new ArrayList<Integer>();
	
	
	
	public static void main(String[] args) {
		
		
		List<String> words = Arrays.asList("apple", "banana", "pear", "kiwi", "mango", "orange");
		System.out.println(words.stream().filter(s-> s.length() >5).map(s-> s.toUpperCase()).collect(Collectors.toList()));
		
		String str = "ilovecapgemini";//Print each character and 
		//its count in descending order.


		Map<String, String> map = new HashMap<String, String>();
		//Stream.of(map).filter(s -> s.values());

		
		
		numbers.add(10);		// 10,20,30,40,50
		numbers.add(20);
		numbers.add(30);
		numbers.add(40);
		numbers.add(50);
		numbers.add(90);
		numbers.add(70);
		numbers.add(60);
		
		int bigNum = 0;
		int secondHighNum = 0;
		Collections.sort(numbers);
		for(int i=0; i< numbers.size(); i++) {
			Integer num = numbers.get(i);
			if(bigNum < num.intValue()) {
				secondHighNum=bigNum;
				bigNum = num;
			}
		}
		System.out.println(secondHighNum);
		
	}
	
	
	
}




