package ladder.domain;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Game {
    private final PersonList persons;
    private final Ladder ladder;

    public Game(PersonList personList, int ladderHeight) {
        this.persons = personList;
        this.ladder = new Ladder(persons.listSize(), ladderHeight);
    }

    public Game(PersonList personList, List<LineCondition> conditions) {
        this.persons = personList;
        this.ladder = new Ladder(persons.listSize(), conditions);
    }

    public String formattedNames() {
        return persons.transformNames();
    }

    public List<Line> ladderLines() {
        return Collections.unmodifiableList(ladder.lines());
    }

    public void playGame() {
        persons.movePersons(ladder);
    }

    public List<String> findAllResult(String keyword, List<String> results) {
        return persons.allResults(keyword, results);
    }

    public Optional<Integer> findSingleResult(String keyword, List<String> results) {
        return persons.singleResult(keyword, results);
    }
}
