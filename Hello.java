/**
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 
 */
public class Hello{
	
	 public static List<City> cities = new ArrayList<>(); 
	
	 
	 
	 private static List<City> prepareTemperature() 
	{ 
	    return cities; 
	}
	 
//	 public static String reverse(String str) {
//	        return IntStream.range(0, str.length())
//	                .mapToObj(i -> str.charAt(str.length() - i - 1))
//	                .collect(Collectors.joining());
//	    }
	 
	 
	 public static void main2(String[] args) {
			Stream strings = Stream.of("apple", "banana", "apple", "cherry", "banana", "apple");
			System.out.println(strings.collect(Collectors.groupingBy(s->s.toString(), Collectors.counting())));
		}
	 
	 
	public static void main(String[] args) {
		
		
		 String str2 = "geeksforgeeks"; 
	        char ch = 'g'; 
	        
	        System.out.println(str2.chars().filter(c -> c == ch).count());
		
		char[] charArray = "Aniruddh".toCharArray();
	    IntStream.range(0, charArray.length)
	        .mapToObj(i -> charArray[(charArray.length - 1) - i])
	        .forEach(s-> s.charValue());
	    System.out.println("=-===========");
	    
	    String str = "Aniruddh";
	    List<StringBuilder> reverseStr = Stream.of(str)
                .map(string -> new StringBuilder(string).reverse())
                .collect(Collectors.toList());
	    System.out.println("reverseStr "+reverseStr);
	    
	    String capStr = "Welcome Capgemini";
	    String reverseStr1 = Arrays
                .stream(capStr.split(" "))
                .map(word -> new StringBuilder(word).reverse())
                .collect(Collectors.joining(" "));
	    System.out.println("reverseStr1 "+reverseStr1);
	    
//	    IntStream.range(0, str.length())
//	    		.mapToObj(i -> str.charAt(str.length() - i - 1))
//	    		.reduce("", String::concat, (s1,s2) -> s2+s1);
	    
	    System.out.println("reduce Func "+str.chars()
	    .mapToObj(c -> (char)c)
	    .reduce("", (s,c) -> c+s, (s1,s2) -> s2+s1));
	    
	    
		cities.add(new City("New Delhi", 33.5)); 
	    cities.add(new City("Mexico", 14)); 
	    cities.add(new City("New York", 13)); 
	    cities.add(new City("Dubai", 43)); 
	    cities.add(new City("London", 15)); 
	    cities.add(new City("Alaska", 1)); 
	    cities.add(new City("Kolkata", 30)); 
	    cities.add(new City("Sydney", 11)); 
	    cities.add(new City("Mexico", 14)); 
	    cities.add(new City("Dubai", 43)); 
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		
		
		BinaryOperator<Integer> bo = (i,j) -> i+j;
		System.out.println("HI "+numbers.stream().reduce(0, bo));
		System.out.println(prepareTemperature()
                .stream() 
                .collect(Collectors.groupingBy(
                            City::getName, 
                            
                            Collectors.collectingAndThen(Collectors.counting(), f -> f.intValue())
                            )));
		
		// groupingBy is returning a map<T, U> return type until we are not defining downstream to it, based on downstream the map<T, R> return type.
		System.out.println(prepareTemperature()
                .stream() 
                .collect(Collectors.groupingBy(
                            City::getName, 
                            Collectors.counting())
                            ));

		System.out.println("name grouping "+prepareTemperature() 
                .stream() 
                .collect(Collectors.groupingBy(
                            City::getName, 
                            Collectors.mapping( 
                                    City::getTemperature, 
                                    Collectors.toList())))); 
		
		
		List<Integer> even = numbers.stream().filter(a-> a%2 == 0).collect(Collectors.toList());
		System.out.println(even);
		
		
		 List<Integer> myList = Arrays.asList(10,15,8,49,25,98,32);
		 myList.stream().map(s-> s+"").filter(s->s.startsWith("1")).forEach(
				 s -> System.out.println(s));
		 List<Integer> duplicatesList = Arrays.asList(10,15,10,49,25,49,32);
		 Set items = new HashSet<Integer>();
		 System.out.println(duplicatesList.stream().map(a->a+"")
				 .filter(n -> !items.add(n)).collect(Collectors.toSet()));
		 
		 Integer var = myList.stream().max((x,y)->Integer.compare(x, y)).get(); 
		 System.out.println("max value in list is "+var);
		 
		 long count = myList.stream().count();
		 System.out.println(" count "+count);
		 IntStream.of(2);
		 
		 Consumer<String> s= t-> System.out.println(t);
		 
	}
	
	
	
}


class City { 
	  
    // Initializing the properties 
    // of the city class 
    private String name; 
    private double temperature; 
  
    // Parameterized constructor to 
    // initialize the city class 
    public City(String name, double temperature) 
    { 
        this.name = name; 
        this.temperature = temperature; 
    } 
  
    // Getters to get the name and 
    // temperature 
    public String getName() 
    { 
        return name; 
    } 
  
    public Double getTemperature() 
    { 
        return temperature; 
    } 
  
    // Overriding the toString method 
    // to return the name and temperature 
    @Override
    public String toString() 
    { 
        return name + " --> " + temperature; 
    } 
} 
