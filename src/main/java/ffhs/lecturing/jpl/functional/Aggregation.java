package ffhs.lecturing.jpl.functional;

import java.util.ArrayList;
import java.util.Arrays;

class Aggregation {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);

        QueryEngine queryEngine = new QueryEngine();

        queryEngine.SetAggregatorStrategy(new Sum());
        int sum = queryEngine.Aggregate(numbers);

        queryEngine.SetAggregatorStrategy(new Average());
        int avg = queryEngine.Aggregate(numbers);

        queryEngine.SetAggregatorStrategy(new Median());
        int median = queryEngine.Aggregate(numbers);

        System.out.println(sum);
        System.out.println(avg);
        System.out.println(median);
    }
}

class QueryEngine {
    private Aggregator aggregator;

    public void SetAggregatorStrategy(Aggregator aggregator) {
        this.aggregator = aggregator;
    }

    public Integer Aggregate(ArrayList<Integer> numbers) {
        return aggregator.execute(numbers);
    }
}

interface Aggregator {
    Integer execute(ArrayList<Integer> numbers);
}

class Sum implements Aggregator {
    @Override
    public Integer execute(ArrayList<Integer> numbers) {
        Integer result = 0;
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        return result;
    }
}

class Average implements Aggregator {
    @Override
    public Integer execute(ArrayList<Integer> numbers) {
        Integer result = 0;
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        return result / numbers.size();
    }
}

class Median implements Aggregator {
    @Override
    public Integer execute(ArrayList<Integer> numbers) {
        Integer[] numArray = new Integer[numbers.size()];
        numbers.toArray(numArray);
        Arrays.sort(numArray);
        int middle = numArray.length / 2;
        int medianValue = 0;
        if (numArray.length % 2 == 1)
            medianValue = numArray[middle];
        else
            medianValue = (numArray[middle-1] + numArray[middle]) / 2;
        return medianValue;
    }
}