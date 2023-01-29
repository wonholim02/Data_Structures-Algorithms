import java.util.Comparator;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;
public class Sorting {

    /**
     * InsertionSort
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Input is invalid");
        }
        for (int i = 1; i < arr.length; i++) {
            T temp = arr[i];
            int j = i - 1;
            while ((j > -1) && (comparator.compare(arr[j], temp) > 0)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    /**
     * Cocktail sort.
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Input is invalid");
        }
        int up = arr.length - 1;
        int down = 0;
        int last = 0;
        boolean swapping = true;
        while (swapping) {
            swapping = false;
            for (int i = last; i < up; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    last = i;
                    swapping = true;
                }
            }
            up = last;
            if (!swapping) {
                return;
            }
            swapping = false;
            for (int j = up; j > down; j--) {
                if (comparator.compare(arr[j - 1], arr[j]) > 0) {
                    swap(arr, j - 1, j);
                    last = j;
                    swapping = true;
                }
            }
            down = last;
        }
    }

    /**
     * Merge sort.
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Input is invalid");
        }
        int len = arr.length;
        int mid = len / 2;
        T[] left = (T[]) new Object[mid];
        T[] right = (T[]) new Object[len - mid];
        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }
        if (arr.length > 1) {
            mergeSort(left, comparator);
            mergeSort(right, comparator);
        }
        int currIdx = 0;
        int leftIdx = 0;
        int rightIdx = 0;
        while (currIdx < arr.length) {
            if (leftIdx >= left.length) {
                arr[currIdx] = right[rightIdx];
                rightIdx++;
                currIdx++;
            } else if (rightIdx >= right.length) {
                arr[currIdx] = left[leftIdx];
                leftIdx++;
                currIdx++;
            } else if (comparator.compare(left[leftIdx], right[rightIdx]) <= 0) {
                arr[currIdx] = left[leftIdx];
                leftIdx++;
                currIdx++;
            } else {
                arr[currIdx] = right[rightIdx];
                rightIdx++;
                currIdx++;
            }
        }
    }

    /**
     * Quick sort.
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws java.lang.IllegalArgumentException if the array or comparator or
     *                                            rand is null
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Input is invalid");
        }
        quickSort(arr, comparator, rand, 0, arr.length - 1);
    }

    /**
     * Helper method for quicksort.
     * @param arr target array
     * @param comparator the Comparator for comparison
     * @param rand random object for finding pivot
     * @param left left bound index
     * @param right rght bound index
     * @param <T> generic data type
     */
    private static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                      Random rand, int left, int right) {
        if (right <= left) {
            return;
        }
        int pivot = rand.nextInt(right - left + 1) + left;
        swap(arr, left, pivot);
        int i = left + 1;
        int j = right;
        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], arr[left]) <= 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], arr[left]) >= 0) {
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        swap(arr, left, j);
        quickSort(arr, comparator, rand, left, j - 1);
        quickSort(arr, comparator, rand, j + 1, right);
    }

    /**
     * Helper method for swapping elements.
     * @param arr target array
     * @param a index of element a
     * @param b index of element b
     * @param <T> element generic type of the array
     */
    private static <T> void swap(T[] arr, int a, int b) {
        T saved = arr[a];
        arr[a] = arr[b];
        arr[b] = saved;
    }

    /**
     * LSD radix sort.
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (arr.length == 0) {
            return;
        }
        int div = 1;
        int kVal = k(arr);
        for (int i = 0; i < kVal; i++) {
            LinkedList<Integer>[] dummy = (LinkedList<Integer>[]) new LinkedList[19];
            for (int j = 0; j < arr.length; j++) {
                int index = (arr[j] / div) % 10 + 9;
                if (dummy[index] == null) {
                    dummy[index] = new LinkedList<>();
                }
                dummy[index].addLast(arr[j]);
            }
            div *= 10;
            int newIdx = 0;
            for (LinkedList<Integer> list: dummy) {
                if (list != null) {
                    for (int j = 0; j < list.size(); j++) {
                        arr[newIdx] = list.get(j);
                        newIdx++;
                    }
                }
            }
        }
    }

    /**
     * Helper method for finding k operations for lsdRadixSort.
     * @param arr target array
     * @return length of the longest number in array
     */
    private static int k(int[] arr) {
        int k = 0;
        int target = Math.abs(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            int curr = Math.abs(arr[i]);
            if (curr == Integer.MIN_VALUE) {
                target = Integer.MIN_VALUE;
                break;
            } else if (curr > target) {
                target = curr;
            }
        }
        if (target == 0) {
            k = 1;
        }
        if (target == Integer.MIN_VALUE) {
            return 10;
        }
        while (target > 0) {
            target /= 10;
            k++;
        }
        return k;
    }

    /**
     * Heap sort.
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Input - data is null");
        }
        PriorityQueue<Integer> minheap = new PriorityQueue<Integer>(data);
        int[] sorted = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            sorted[i] = minheap.remove();
        }
        return sorted;
    }
}
