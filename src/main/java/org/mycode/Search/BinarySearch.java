package org.mycode.Search;

public class BinarySearch {
    //升序数组
    public int BinarySearch(int[] arr,int target){
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right){
            mid = left + (right - left) / 2;
            if (arr[mid] == target){
                return 1;
            }else if (arr[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();

        int[] arr = {1,4,7,8,9,33,44};

        int result = binarySearch.BinarySearch(arr,77);

        System.out.println(result == 1?"查找成功":"查找失败");

    }
}
