package org.mycode.Search;

public class BinarySearchPractice {
    //找算术平方根
    public int Sqrt(int num){
        int[] arr = new int[num + 1];
        for (int i = 0;i<=num;i++){
            arr[i] = i;
        }

        int left = 0;
        int right = arr.length - 1;
        int result = 0;
        int mid = 0;
        //需要等于这个条件 不然会漏掉一些情况
        while (left <= right){
            mid = left + (right - left) / 2;
            if (Math.pow(arr[mid],2) > num){
                right = mid - 1;
            }else {
                result = arr[mid];
                left = mid + 1;
            }
        }
        return result;
    }

    //搜索旋转排序数组
    public int search(int[] arr,int target){
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            int mid = left +(right - left) / 2;

            if(arr[mid] == target){
                return  mid;
            }else if (arr[mid] < arr[right]){
                //在非旋转区
                if (arr[mid] > target){
                    right = mid - 1;
                }else {
                    if (target <= arr[right]){
                        left = mid + 1;
                    }else {
                        right = mid - 1;
                    }
                }
            }else {
                //在旋转区
                if (arr[mid] < target){
                    left = mid + 1;
                }else {
                    if (target >= arr[left]){
                        right = mid - 1;
                    }else {
                        left = mid + 1;
                    }
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        BinarySearchPractice binarySearchPractice = new BinarySearchPractice();

        int res = binarySearchPractice.Sqrt(9);

        System.out.println(res);

        int[] arr = {5, 1, 2, 3, 4};
        int searchRes = binarySearchPractice.search(arr,1);
        System.out.println(searchRes == -1 ? "查找失败":"查找成功");

    }
}
