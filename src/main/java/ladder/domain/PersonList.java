package ladder.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PersonList {
    private List<Person> personList = new ArrayList<>();

    public PersonList(List<Person> personList) {
        this.personList = personList;
    }

    public PersonList(String[] personData) {
        if (isDuplicated(personData)) {
            throw new IllegalArgumentException("중복된 이름이 있습니다.");
        }
        IntStream.range(0, personData.length).forEach(i -> {
            Person person = new Person(personData[i], i);
            personList.add(person);
        });
    }

    private static boolean isDuplicated(String[] personData) {
        return Arrays.stream(personData).distinct().count() != Arrays.stream(personData).count();
    }

    public List<Person> persons() {
        return personList;
    }

    public int listSize() {
        return personList.size();
    }

    public void movePersons(Ladder ladder) {
        personList.forEach(p -> ladder.lines().forEach(line -> p.move(line.points())));
    }

    public String transformNames() {
        return personList.stream()
                .map(Person::spaceAddedName)
                .reduce((n1, n2) -> n1 + n2)
                .get();
    }

    public List<String> allResults(String keyword, List<String> results) {
        return personList.stream()
                .map(p -> p.name() + " : " + results.get(p.position()))
                .collect(Collectors.toList());
    }

    public Optional<Integer> singleResult(String keyword, List<String> results) {
        return personList.stream()
                .filter(p -> p.name().equals(keyword))
                .map(Person::position)
                .findAny();
    }
}
