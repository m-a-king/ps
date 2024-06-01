package test;

import java.util.Arrays;

public class SortIdentifier {

    static class SortStatus {
        String method;
        int accuracy;

        public SortStatus(String method, int accuracy) {
            this.method = method;
            this.accuracy = accuracy;
        }
    }

    static String[] original = {
            "navy", "plum", "coal", "jade", "blue", "pink", "rose", "gray", "teal",
            "ruby", "mint", "lime", "silk", "corn", "bark", "wine", "dusk", "leaf",
            "herb", "sage", "cafe", "mist", "pine", "palm"
    };

    static String[] sorted;

    public static void main(String[] args) {
        sorted = original.clone();
        Arrays.sort(sorted);

        String[] midArr1 = {
                "corn", "mist", "coal", "jade", "blue", "cafe", "herb", "gray", "leaf",
                "dusk", "mint", "lime", "bark", "navy", "silk", "wine", "ruby", "teal",
                "rose", "sage", "pink", "plum", "pine", "palm"
        };

        String[] midArr2 = {
                "blue", "coal", "corn", "gray", "jade", "lime", "mint", "navy", "pink",
                "plum", "rose", "ruby", "silk", "teal", "bark", "wine", "dusk", "leaf",
                "herb", "sage", "cafe", "mist", "pine", "palm"
        };

        String[] midArr3 = {
                "wine", "teal", "silk", "plum", "sage", "pink", "rose", "jade", "navy",
                "ruby", "pine", "palm", "coal", "corn", "bark", "gray", "dusk", "leaf",
                "herb", "blue", "cafe", "mist", "mint", "lime"
        };

        String[] midArr4 = {
                "bark", "blue", "cafe", "coal", "corn", "dusk", "gray", "herb", "jade",
                "leaf", "lime", "mint", "silk", "plum", "navy", "wine", "pink", "ruby",
                "rose", "sage", "teal", "mist", "pine", "palm"
        };

        String[] midArr5 = {
                "bark", "blue", "coal", "corn", "dusk", "cafe", "gray", "herb", "jade",
                "leaf", "lime", "mint", "mist", "navy", "palm", "pine", "pink", "plum",
                "rose", "ruby", "sage", "silk", "teal", "wine"
        };


        System.out.println("Identified Sort 1: " + identifySort(midArr1));
        System.out.println("Identified Sort 2: " + identifySort(midArr2));
        System.out.println("Identified Sort 3: " + identifySort(midArr3));
        System.out.println("Identified Sort 4: " + identifySort(midArr4));
        System.out.println("Identified Sort 5: " + identifySort(midArr5));
    }

    public static String identifySort(String[] midState) {
        SortStatus quickSortStatus = new SortStatus("Quick Sort", checkQuickSort(midState));
        SortStatus insertionSortStatus = new SortStatus("Insertion Sort", checkInsertionSort(midState));
        SortStatus heapSortStatus = new SortStatus("Heap Sort", checkHeapSort(midState));
        SortStatus selectionSortStatus = new SortStatus("Selection Sort", checkSelectionSort(midState));
        SortStatus bubbleSortStatus = new SortStatus("Bubble Sort", checkBubbleSort(midState));

        SortStatus[] statuses = {
                quickSortStatus, insertionSortStatus, heapSortStatus, selectionSortStatus, bubbleSortStatus
        };

        SortStatus bestMatch = statuses[0];
        for (SortStatus status : statuses) {
            if (status.accuracy > bestMatch.accuracy) {
                bestMatch = status;
            }
        }

        return bestMatch.method;
    }

    public static int checkQuickSort(String[] midState) {
        int accuracy = 0;
        String pivot = original[0];  // 피봇은 오리지날 배열의 첫번째 값으로 설정

        boolean isPivotValid = true;
        for (int i = 0; i < midState.length; i++) {
            if (midState[i].equals(pivot)) {
                for (int j = 0; j < i; j++) {
                    if (midState[j].compareTo(pivot) > 0) {
                        isPivotValid = false;
                        break;
                    }
                }
                for (int j = i + 1; j < midState.length; j++) {
                    if (midState[j].compareTo(pivot) <= 0) {
                        isPivotValid = false;
                        break;
                    }
                }
                if (isPivotValid) {
                    accuracy++;
                }
                break;
            }
        }
        return accuracy;
    }

    public static int checkInsertionSort(String[] midState) {
        int sortedLength = 0;
        int accuracy = 0;

        for (int i = 1; i < midState.length; i++) {
            if (midState[i - 1].compareTo(midState[i]) <= 0) {
                sortedLength = i;
                accuracy++;
            } else {
                break;
            }
        }

        for (int i = sortedLength + 1; i < midState.length; i++) {
            if (!(midState[i].equals(original[i]))) {
                return -1; // 중요: 중간 상태와 원본 배열이 일치하지 않으면 정확도를 -1로 설정
            }
        }
        return accuracy;
    }

    public static int checkHeapSort(String[] midState) {
        int accuracy = 0;
        for (int i = 0; i < midState.length / 2; i++) {
            if (2 * i + 1 < midState.length && midState[i].compareTo(midState[2 * i + 1]) >= 0) {
                accuracy++;
            } else {
                accuracy--;
            }
            if (2 * i + 2 < midState.length && midState[i].compareTo(midState[2 * i + 2]) >= 0) {
                accuracy++;
            } else {
                accuracy--;
            }
        }
        return accuracy;
    }

    public static int checkSelectionSort(String[] midState) {
        int sortedLength = 0;
        int accuracy = 0;
        for (int i = 0; i < midState.length - 1; i++) {
            if (midState[i].compareTo(midState[i + 1]) <= 0) {
                sortedLength = i;
                accuracy++;
            } else {
                break;
            }
        }

        for (int i = 0; i <= sortedLength; i++) {
            if (midState[i].equals(sorted[i])) {
                accuracy++;
            }
        }
        return accuracy;
    }

    public static int checkBubbleSort(String[] midState) {
        int sortedLength = midState.length;
        int accuracy = 0;
        for (int i = midState.length - 1; i > 0; i--) {
            if (midState[i - 1].compareTo(midState[i]) <= 0) {
                sortedLength = i;
                accuracy++;
            } else {
                break;
            }
        }

        for (int i = sortedLength; i < midState.length; i++) {
            if (midState[i].equals(sorted[i])) {
                accuracy++;
            }
        }

        return accuracy;
    }
}
