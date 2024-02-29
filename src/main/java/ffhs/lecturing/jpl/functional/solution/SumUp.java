import java.util.ArrayList;
import java.util.function.Function;

class SumUp {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);

        Function<ArrayList<Integer>, Integer> sum = n -> {
            Integer result = 0;
            for (int i = 0; i < n.size(); i++) {
                result += n.get(i);
            }
            return result;
        };

        System.out.println(sum.apply(numbers));
    }
}
