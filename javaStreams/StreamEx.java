import java.lang.String;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;
import java.io.IOException;

class StreamEx{
    public static void main(String[] args) throws IOException {
        // Source --> filter -> sort -> map --> Collect
        /* Intermediate operations
        anyMatch() flatmap()
        distinct() map()
        filter() skip()
        findFirst() sorted()

        Terminal operations
        forEach and collect for each elements,
        below methods reduce the result to single summary element
        count() min()
        max() reduce()
        average() summaryStatistics()*/

        // 1.Integer Stream
        IntStream
        	.range(1,10)
        	.forEach(System.out::print);
        System.out.println();

        // 2. Integer stream with Skip
        IntStream
        	.range(1,10)
        	.skip(5)
        	.forEach(x -> System.out.println(x));
        System.out.println();

        // 3. Integer stream with Sum
        System.out.println(
        	IntStream
        		.range(1,6)
        		.sum());
        System.out.println();

        //4. Stream.of, sorted and findFirst
        Stream.of("Ana", "Adam", "Anil")
        	.sorted()
        	.findFirst()
        	.ifPresent(System.out::println);

        //5.stream of array sort filter and print
        String[] names = {"Kiran","Kumar","rao", "amanda", "anna", "lilly"};
        Arrays.stream(names)
        	.filter(x -> x.startsWith("K"))
        	.sorted()
        	.forEach(System.out::println);

        //6. Avg of squares of an int array
        Arrays.stream(new int[]{2,4,6,8,10})
        	.map(x -> x*x)
        	.average()
        	.ifPresent(System.out::println);

        // 7. stream from list, filter and print
        List<String> lt = Arrays.asList("Kiran","Kumar","rao", "amanda", "anna", "lilly");
        lt.stream()
        	.map(String::toLowerCase)
        	.filter(x -> x.startsWith("k"))
        	.forEach(System.out::println);

        // 8. stream row from text file, sort filter print
        Stream<String> text = Files.lines(Paths.get("some.txt"));
        text.sorted()
        	.filter(x -> x.length() > 15)
        	.forEach(System.out::println);
        text.close();

        //9. stream row from text file and sae to list
        List<String> text2 = Files.lines(Paths.get("some.txt"))
        	.filter(x -> x.contains("and"))
        	.collect(Collectors.toList());
        text2.forEach(x -> System.out.println(x));

        // 10. stream rows from csv file and count
        Stream<String> stm = Files.lines(Paths.get("data.txt"));
        int rowCount = (int)stm.map(x -> x.split(","))
        						.filter(x -> x.length == 3)
        						.count();
        System.out.println(rowCount + " rows.");
        stm.close();

        // 11. stream rows from csv file and parse data from row
        Stream<String> stm2 = Files.lines(Paths.get("data.txt"));
        stm2.map(x -> x.split(","))
        	.filter(x -> x.length == 3)
        	.filter(x -> Integer.parseInt(x[1]) > 15)
        	.forEach(x -> System.out.println(x[0] +" "+ x[1]+" "+x[2]));
        stm2.close();

        // 12. stream rows from csv file and store data in hashmap
        Stream<String> stm3 = Files.lines(Paths.get("data.txt"));
        Map<String, Integer> map = new HashMap<>();
        map = stm3.map(x -> x.split(","))
        		.filter(x -> x.length == 3)
        		.filter(x -> Integer.parseInt(x[1]) > 15)
        		.collect(Collectors.toMap(
        			x -> x[0],
        			x -> Integer.parseInt(x[1])));
        stm3.close();
        for(String k: map.keySet()){
        	System.out.println(k+" "+map.get(k));
        }

        // 13. reduction - sum
        double total = Stream.of(7.3, 1.5, 4.8)
        	.reduce(0.0, (Double a, Double b) -> a + b);
       	System.out.println("Total "+ total);

       	// 14. Summary Statistics
       	IntSummaryStatistics summary = IntStream.of(7,2, 19,88, 73, 4, 10)
       		.summaryStatistics();
       	System.out.println(summary);
    }
}