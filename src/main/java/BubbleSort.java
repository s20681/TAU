import java.util.List;

class BubbleSort {
    List<Integer> sort(List<Integer> list) {
        int n = list.size();
        if (n == 0) {
            throw new IllegalArgumentException("Cannot be empty");
        }
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (list.get(j) > list.get(j + 1)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
        return list;
    }
}