/*
打印一个数的所有乘数组合，从大到小，不要有重复
*/
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class Factor {
    public static void main(String[] args) {
        Factor obj = new Factor();
        List<List<Integer>> resultList = obj.getAllFactor(240);
        for (List<Integer> list : resultList) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    
    public List<List<Integer>> getAllFactor(int n) {
        List<List<Integer>> resultList = new ArrayList<>();
        List<Integer> singleList = new ArrayList<>();
        getAllFactorCore(n, n / 2, singleList, resultList);
        return resultList;
    }   

    private void getAllFactorCore(int n, int end, List<Integer> singleList, List<List<Integer>> resultList) {
        if (n == 1 || n == -1 || n == 0) {
            List<Integer> newList = new ArrayList<Integer>(singleList);
            resultList.add(newList);
            return;
        }
        for (int i = end; i >= 2; i--) {
            if ((n % i) != 0) {
                continue;
            }
            singleList.add(i);
            getAllFactorCore(n / i, i, singleList, resultList);
            singleList.remove(singleList.size() - 1);
        }
    }
}
