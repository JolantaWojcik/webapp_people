package service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import model.Person;

public class DataService {
	
	//liczba osob urodzonych w danym miesiacu
	public long birthMonthly(String month, List<Person> people) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("LLLL", Locale.getDefault());
		return people.stream().filter(e -> dateFormat.format(e.getBirthdate()) == month)
				.count();
	}
	
	public  Map<String, Long> birthByMonth(List<Person>people){
		SimpleDateFormat dateFormat = new SimpleDateFormat("LLLL", Locale.getDefault());
		return people.stream().collect(Collectors.groupingBy
				(e -> dateFormat.format(e.getBirthdate()), Collectors.counting()));
	}
	
	public List<Person> getAllPeopleByMonth(String month, List<Person>people) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("LLLL", Locale.getDefault());
		return people.stream().filter(e -> dateFormat.format(e.getBirthdate()).equals(month)) 
				 .collect(Collectors.toList());
	}
}
