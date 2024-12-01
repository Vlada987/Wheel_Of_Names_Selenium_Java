package myUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.github.javafaker.Faker;

import interfaces.Helper;

public class MyUtil {

	static Faker faker = new Faker();

	public static Helper<List<String>, String> toList = (String text) -> {

		String[] namesArray = text.split("\n");
		return Arrays.stream(namesArray).map(String::trim).collect(Collectors.toList());
	};

	public static Helper<String, Integer> wheelData = (Integer size) -> {
		String data = "";
		for (int a = 0; a <= size; a++) {
			data = data + faker.address().city() + "\n";
		}
		return data;
	};

	public static boolean areEqualIgnorOrder(List<String> list1, List<String> list2) {

		Set<String> set1 = list1.stream().collect(Collectors.toSet());
		Set<String> set2 = list2.stream().collect(Collectors.toSet());

		return set1.equals(set2);
	}

	public static boolean isSorted(List<String> list) {

		return IntStream.range(0, list.size() - 1).allMatch(i -> list.get(i).compareTo(list.get(i + 1)) <= 0);
	}

	public static String rndMessage() {
		return "This is my wheel " + faker.animal().name();
	}

	public static List mergeLists(List list1, List list2) {
		return (List) Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());
	}

	public static String findElementOccurringMoreThanTwoTimes(List<String> list) {

		return list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
				.stream().filter(entry -> entry.getValue() > 2).map(Map.Entry::getKey).findFirst().orElse(null);
	}

}
