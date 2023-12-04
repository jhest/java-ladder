package ladder.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Result {
    public static final int MAX_LENGTH = 5;

    private final List<String> results;

    public Result(List<String> results) {
        if (isOverLength(results.stream()) || isEmpty(results.stream())) {
            throw new IllegalArgumentException("결과는 1 ~ 5 자로 입력하세요.");
        }
        this.results = results;
    }

    public Result(String[] resultData, String[] personData) {
        if (isOverLength(Arrays.stream(resultData)) || isEmpty(Arrays.stream(resultData))) {
            throw new IllegalArgumentException("결과는 1 ~ 5 자로 입력하세요.");
        }
        if (isSameSize(resultData, personData)) {
            throw new IllegalArgumentException("참가자 숫자와 동일한 결과를 입력하세요.");
        }
        this.results = Arrays.stream(resultData).collect(Collectors.toList());
    }

    private static boolean isOverLength(Stream<String> results) {
        return results.anyMatch(r -> r.length() > MAX_LENGTH);
    }

    private static boolean isEmpty(Stream<String> results) {
        return results.anyMatch(String::isEmpty);
    }

    private static boolean isSameSize(String[] resultData, String[] personData) {
        return Arrays.stream(resultData).count() != Arrays.stream(personData).count();
    }

    public List<String> elements() {
        return results;
    }

    public List<String> findResult(Game game, String keyword) {
        if (keyword.equals("all")) {
            return game.findAllResult(keyword, results);
        }

        Optional<Integer> findResult = game.findSingleResult(keyword, results);

        if (findResult.isEmpty()) {
            return Collections.singletonList("일치하는 이름이 없습니다.");
        }

        return Collections.singletonList(results.get(findResult.get()));
    }

    public String formattedResults() {
        return results.stream()
                .map(r -> String.format("%6s", r))
                .reduce((r1, r2) -> r1 + r2)
                .get();
    }
}
