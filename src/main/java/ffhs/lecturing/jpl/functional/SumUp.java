package ffhs.lecturing.jpl.functional;

import java.util.ArrayList;

class SumUp {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);
        System.out.println(Sum(numbers));
    }

    private static Integer Sum(ArrayList<Integer> numbers) {
        Integer result = 0;
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        return result;
    }
}

