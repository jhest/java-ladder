package ladder.domain;

import java.util.List;

public class Person {
    public static final int MAX_LENGTH = 5;

    private final String name;
    private int position;

    public Person(String name, int position) {
        if (name.isEmpty() || name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("이름은 1 ~ 5 자입니다.");
        }
        this.name = name;
        this.position = position;
    }

    public String name() {
        return name;
    }

    public Integer position() {
        return position;
    }

    public String spaceAddedName() {
        return String.format("%6s", name);
    }

    public int move(List<Boolean> condition) {
        if (isLastPosition(condition) && isLineExist(condition, position)) {
            this.position = position + 1;
            return position;
        }
        if (isFirstPosition() && isLineExist(condition, position - 1)) {
            this.position = position - 1;
            return position;
        }
        return position;
    }

    private boolean isLastPosition(List<Boolean> lineCondition) {
        return position < lineCondition.size();
    }

    private boolean isFirstPosition() {
        return position - 1 >= 0;
    }

    private Boolean isLineExist(List<Boolean> lineCondition, int position) {
        return lineCondition.get(position);
    }
}
