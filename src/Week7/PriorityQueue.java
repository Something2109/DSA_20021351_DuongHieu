package Week7;

class PriorityQueue {

    Integer[] arr;
    int size;

    PriorityQueue() {
        arr = new Integer[2];
        size = 0;
    }

    private void increaseSize() {
        Integer[] newArr = new Integer[arr.length * 2];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    private void decreaseSize() {
        Integer[] newArr = new Integer[arr.length / 2];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    void insert(int n) {
        if (arr.length == size) {
            increaseSize();
        }
        arr[size] = Integer.valueOf(n);
        int index = size - 1;
        while (index >= 0 && arr[index + 1] > arr[index]) {
            Integer temp = arr[index];
            arr[index] = arr[index + 1];
            arr[index + 1] = temp;
            index--;
        }
        size++;
    } 

    int deleteMin() {
        int result = arr[size - 1].intValue();
        arr[size - 1] = null;
        size--;
        if (size == arr.length / 4) {
            decreaseSize();
        }
        return result;
    }
}