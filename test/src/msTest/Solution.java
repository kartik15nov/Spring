package msTest;

public class Solution {
    public static int minSweet(int[] arr) {
//        int lastmin = 1;
//        int lastbig = 0;
//        int lastBigNum = arr[0];
//        int res = 1;
//        int count=0;
//        for (int i = 1; i < n; i++) {
//            if (arr[i] > lastBigNum) {
//                lastBigNum = arr[i];
//                lastbig++;
//                res += lastbig;
//                count=0;
//            }else  if(arr[i]==lastBigNum){
//                ++count;
//                if (count>=1)
//                    res += lastbig;
//            }
//            res += lastmin;
//
//        }
        int n=arr.length;
        int res = 0;
        int[] count = new int[n];
        for (int i = 0; i < n; i++)
            count[i] = 1;

        int lastNum = 0;
        int sameNumber = 0;
        for (int i = 1; i < n - 1; i++) {
            if (i == 2)
                lastNum = arr[i-1];

            if (arr[i] == lastNum)
                ++sameNumber;
            else
                lastNum = arr[i];

            if (arr[i] > arr[i - 1]) {
                count[i] = count[i] + count[i - 1];
            }
            if (arr[i] < arr[i - 1] && arr[i] > arr[i + 1]) {
                count[i] = count[i] + count[i + 1];

                if (sameNumber == 0)
                    count[i - 1] = count[i - 1] + 1;
            }
            if (arr[i] != lastNum)
                sameNumber = 0;
        }


        if (arr[n - 1] > arr[n - 2]) {
            count[n - 1] += count[n - 2];
        }


        for (int cout :
                count) {
            res += cout;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] case1 = {1, 2, 2};
        System.out.println(minSweet(case1));

        int[] case2 = {1, 4, 3, 6, 2, 1};
        System.out.println(minSweet(case2));

        int[] case3 = {4, 4, 4, 4};
        System.out.println(minSweet(case3));
        int[] case4 = {4, 4, 4, 4, 1};
        System.out.println(minSweet(case4));

        int[] case5 = {1, 2, 3, 4};
        System.out.println(minSweet(case5));

        int[] case6 = {1, 2, 3, 4, 4, 3, 2, 1};
        System.out.println(minSweet(case6));

    }
}
