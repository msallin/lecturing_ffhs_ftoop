package ffhs.lecturing.jpl.functional.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

class Aggregation {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);

        QueryEngine queryEngine = new QueryEngine();

        Function<ArrayList<Integer>, Integer> aggregator = (n) -> {
            Integer result = 0;
            for (int i = 0; i < n.size(); i++) {
                result += n.get(i);
            }
            return result;
        };

        queryEngine.Aggregate(numbers, aggregator);

        aggregator = (n) -> {
            Integer result = 0;
            for (int i = 0; i < n.size(); i++) {
                result += n.get(i);
            }
            return result / n.size();
        };


        queryEngine.Aggregate(numbers, aggregator);

        aggregator = (n) -> {
            Collections.sort(n);
            int middle = n.size() / 2;
            int medianValue = 0;
            if (n.size() % 2 == 1)
                medianValue = n.get(middle);
            else
                medianValue = (n.get(middle - 1) + n.get(middle)) / 2;
            return medianValue;
        };
        queryEngine.Aggregate(numbers, aggregator);
    }
}

class QueryEngine {
    public Integer Aggregate(ArrayList<Integer> numbers, Function<ArrayList<Integer>, Integer> aggregator) {
        return aggregator.apply(numbers);
    }
}