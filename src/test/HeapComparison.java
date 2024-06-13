package test;

public class HeapComparison {
    // 비교와 이동 횟수를 저장하는 변수들
    static int heapifyComparisons = 0;
    static int heapifyMoves = 0;
    static int insertComparisons = 0;
    static int insertMoves = 0;
    static int insertRootComparisons = 0;
    static int insertRootMoves = 0;

    public static void main(String[] args) {
        // test arr
        int[] array1 = {3, 1, 2, 5, 9, 6, 8, 7, 4, 12, 11, 15, 14, 13, 10};
        int[] array2 = {
                10, 23, 35, 47, 59, 68, 72, 83, 91, 15, 27, 38, 49, 60, 74,
                85, 97, 100, 18, 29, 40, 52, 63, 75, 88, 92, 21, 33, 44, 55,
                67, 79, 81, 93, 99, 14, 26, 37, 48, 58, 69, 73, 84, 95, 17,
                28, 39, 50, 61, 77, 89, 90, 22, 34, 45, 56, 66, 78, 80, 94,
                98, 13, 25, 36, 46, 57, 70, 71, 82, 96, 16, 30, 41, 53, 65,
                76, 87, 91, 20, 32, 43, 54, 64, 83, 91, 95, 12, 24, 35, 47,
                59, 68, 72, 83, 91, 15, 27, 38, 49, 60, 74
        };


        // 배열 1 비교
        System.out.println(">>>>> Array 1:");
        compareHeapMethods(array1);
        System.out.println();

        // 변수 초기화
        heapifyComparisons = 0;
        heapifyMoves = 0;
        insertComparisons = 0;
        insertMoves = 0;
        insertRootComparisons = 0;
        insertRootMoves = 0;

        // 배열 2 비교
        System.out.println(">>>>> Array 2:");
        compareHeapMethods(array2);
        System.out.println();
    }

    public static void compareHeapMethods(int[] array) {
        int[] arrayCopy1 = array.clone();
        int[] arrayCopy2 = array.clone();

        // 초기 배열 출력
        System.out.print("Initial Array: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("arrayLength = " + array.length);
        System.out.println();

        // Heapify (힙화)
        buildHeap(array);
        System.out.print("Heap after Heapify: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Heapify Method:");
        System.out.println("Comparisons: " + heapifyComparisons);
        System.out.println("Moves: " + heapifyMoves);
        System.out.println("Is Max Heap: " + isMaxHeap(array));
        System.out.println();

        // 마지막 요소에 삽입 (Insertion at last)
        buildHeapByInsertion(arrayCopy1);
        System.out.print("Heap after Insertion (last element): ");
        for (int num : arrayCopy1) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Insertion Method (last element):");
        System.out.println("Comparisons: " + insertComparisons);
        System.out.println("Moves: " + insertMoves);
        System.out.println("Is Max Heap: " + isMaxHeap(arrayCopy1));
        System.out.println();

        // 루트에 삽입 (Insertion at root)
        buildHeapByInsertionAtRoot(arrayCopy2);
        System.out.print("Heap after Insertion (root element): ");
        for (int num : arrayCopy2) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Insertion Method (root element):");
        System.out.println("Comparisons: " + insertRootComparisons);
        System.out.println("Moves: " + insertRootMoves);
        System.out.println("Is Max Heap: " + isMaxHeap(arrayCopy2));
    }

    // 힙화 (Heapify)
    public static void buildHeap(int[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
    }

    public static void heapify(int[] array, int n, int i) {
        int largest = i; // 현재 노드가 가장 크다고 가정
        int left = 2 * i + 1; // 왼쪽 자식
        int right = 2 * i + 2; // 오른쪽 자식

        // 왼쪽 자식이 현재 노드보다 큰 경우
        if (left < n) {
            heapifyComparisons++;
            if (array[left] > array[largest]) {
                largest = left;
            }
        }

        // 오른쪽 자식이 현재 노드보다 큰 경우
        if (right < n) {
            heapifyComparisons++;
            if (array[right] > array[largest]) {
                largest = right;
            }
        }

        // 가장 큰 노드가 현재 노드가 아닌 경우, 교환 후 재귀호출
        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            heapifyMoves++;

            heapify(array, n, largest);
        }
    }

    // 마지막 요소에 삽입 (Insertion_last)
    public static void buildHeapByInsertion(int[] array) {
        int n = array.length;
        int[] heap = new int[n];
        int size = 0;

        for (int value : array) {
            insertHeap(heap, size, value);
            size++;
        }

        // 힙을 원래 배열로 복사
        // 삽입 과정에서 직접 원래 배열을 수정하지 않고 별도의 배열에 힙을 만들기 때문에
        // 힙을 구성한 결과를 원래 배열에 반영
        System.arraycopy(heap, 0, array, 0, n);
    }

    public static void insertHeap(int[] heap, int size, int value) {
        // 힙의 마지막 요소에 새로운 값을 추가
        heap[size] = value;
        int current = size;

        // 부모 노드와 비교하여 힙의 속성을 유지하도록 조정
        while (current > 0) {
            int parent = (current - 1) / 2;

            insertComparisons++; // 비교 횟수 증가
            if (heap[current] > heap[parent]) {
                // 현재 노드가 부모 노드보다 크면 교환
                int swap = heap[current];
                heap[current] = heap[parent];
                heap[parent] = swap;
                insertMoves++; // 이동 횟수 증가
                current = parent; // 현재 노드를 부모 노드로 업데이트
            } else {
                // 현재 노드가 부모 노드보다 크지 않으면 종료
                break;
            }
        }
    }

    // 루트에 삽입 (Insertion_root)
    public static void buildHeapByInsertionAtRoot(int[] array) {
        int n = array.length;
        int[] heap = new int[n];
        int size = 0;

        for (int value : array) {
            insertHeapAtRoot(heap, size, value);
            size++;
        }

        // 힙을 원래 배열로 복사
        System.arraycopy(heap, 0, array, 0, n);
    }

    public static void insertHeapAtRoot(int[] heap, int size, int value) {
        if (size == 0) {
            heap[size] = value; // 힙이 비어있는 경우 루트에 값을 추가
            return;
        }

        // 새로운 값을 루트에 삽입하고 루트의 기존 값은 oldValue에 저장
        int oldValue = heap[0];
        heap[0] = value;
        int current = 0;

        // 자식 노드와 비교하여 힙의 속성을 유지하도록 조정
        while (true) {
            int left = 2 * current + 1;
            int right = 2 * current + 2;
            int largest = current;

            // 왼쪽 자식과 비교
            if (left < size) {
                insertRootComparisons++;
                if (heap[left] > heap[largest]) {
                    largest = left;
                }
            }

            // 오른쪽 자식과 비교
            if (right < size) {
                insertRootComparisons++;
                if (heap[right] > heap[largest]) {
                    largest = right;
                }
            }

            // 현재 노드가 가장 큰 노드가 아니면 교환
            if (largest != current) {
                int swap = heap[current];
                heap[current] = heap[largest];
                heap[largest] = swap;
                insertRootMoves++; // 이동 횟수 증가
                current = largest; // 현재 노드를 자식 노드로 업데이트
            } else {
                break;
            }
        }

        // oldValue를 다시 힙의 적절한 위치로 삽입 (최상단에서 하향 조정)
        current = size;
        heap[current] = oldValue;

        while (current > 0) {
            int parent = (current - 1) / 2;

            insertRootComparisons++;
            if (heap[current] > heap[parent]) {
                int swap = heap[current];
                heap[current] = heap[parent];
                heap[parent] = swap;
                insertRootMoves++;
                current = parent;
            } else {
                break;
            }
        }
    }


    // 최대힙 검증
    public static boolean isMaxHeap(int[] heap) {
        int n = heap.length;
        for (int i = 0; i <= (n / 2) - 1; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < n && heap[i] < heap[left]) {
                return false;
            }
            if (right < n && heap[i] < heap[right]) {
                return false;
            }
        }
        return true;
    }
}

// 삽입 T(n)= O(1)+O(log2)+O(log3)+O(log4)+…+O(log(n))
// 힙화 T(n)= log(n)*1+(log(n)-1)*2+(log(n)-2)*4+(log(n)-3)*8 ... + (log(n)-m)*2^m
