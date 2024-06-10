package test.huffmanCoding;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class HuffmanEncoding {

    static class AlpStatus implements Comparable<AlpStatus> {
        int character, count;

        AlpStatus left, right;
        int code;

        AlpStatus(int character, int count) {
            this.character = character;
            this.count = count;
            this.left = null;
            this.right = null;
        }

        AlpStatus(int count, AlpStatus left, AlpStatus right) {
            this.character = -1; // parent node
            this.count = count;
            this.left = left;
            this.right = right;
        }

        public void setCode(int code) {
            this.code = code;
        }

        @Override
        public int compareTo(AlpStatus o) {
            return this.count - o.count;

        }
    }

    // 문자에 대한 허프만 코드를 저장할 Map 생성
    static Map<Character, String> huffmanCodes = new HashMap<>();

    public static void main(String[] args) {
        String filePath = "/Users/making/IdeaProjects/ps/src/test/huffmanCoding/originalText.txt";  // 텍스트 파일 경로
        String originalText = Objects.requireNonNull(readFile(filePath)).trim();

        System.out.println();
        System.out.println(">>>>>>>>>> ORIGINAL TEXT START <<<<<<<<<<");
        System.out.println(originalText);
        System.out.println(">>>>>>>>>>> ORIGINAL TEXT END <<<<<<<<<<<");
        System.out.println();

        // 허프만 코딩 시작
        // 알파벳 개수만큼의 배열 선언 (소문자만 존재함을 가정)
        int[] alpCount = new int[26];

        // 허프만 코딩, 그리디 알고리즘 -> 우선순위큐 (오름차순, 빈도수가 적은 것 부터)
        PriorityQueue<AlpStatus> pq = new PriorityQueue<>();

        for (int i = 0; i < originalText.length(); i++) {
            char currentChar = originalText.charAt(i);

            // 소문자만 존재함을 가정
            if (currentChar >= 'a' && currentChar <= 'z') {
                int diff = currentChar - 'a';
                alpCount[diff]++;
            }
        }

        // 빈도수가 0이 아닌 알파벳을 우선순위큐에 추가
        for (int i = 0; i < 26; i++) {
            if (alpCount[i] != 0) {
                pq.offer(new AlpStatus(i, alpCount[i]));
            }
        }

        // 큐가 비어있을 경우 예외 처리
        if (pq.isEmpty()) {
            System.out.println("No lowercase alphabet characters in originalText.");
            return;
        }

        while (pq.size() >= 2) {
            AlpStatus firstChild = pq.poll();
            AlpStatus secondChild = pq.poll();

            AlpStatus parent = new AlpStatus(firstChild.count + secondChild.count, firstChild, secondChild);
            pq.offer(parent);
        }

        // 최종 루트 노드 (허프만 트리의 루트)
        AlpStatus root = pq.poll();


        // 루트부터 허프만 코드 구하기
        generateCodes(root, "");

        // 입력 텍스트를 허프만 코드로 암호화
        StringBuilder encodedText = new StringBuilder();
        for (char c : originalText.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                encodedText.append(huffmanCodes.get(c));
            }
        }

        String content =
                "header = " + huffmanCodes +
                "\n" +
                "encodedText = " + encodedText;


        System.out.println();
        System.out.println(">>>>>>>>>> ENCODED TEXT START <<<<<<<<<<");
        System.out.println(content);
        System.out.println(">>>>>>>>>>> ENCODED TEXT END <<<<<<<<<<<");
        System.out.println();


        // 인코딩된 텍스트를 파일에 작성
        String outputFilePath = "/Users/making/IdeaProjects/ps/src/test/huffmanCoding/encodedText.txt"; // 출력 파일 경로 설정
        writeFile(outputFilePath, content);

        // 파일 크기 비교
        long originalFileSize = getFileSize(filePath) * 8;
        long encodedFileSize = getFileSize(outputFilePath);

        System.out.println("Original file size: " + originalFileSize);
        System.out.println("Encoded file size: " + encodedFileSize);

        // 압축률 계산
        double compressionRatio = (double) encodedFileSize / originalFileSize * 100;
        double compressionFactor = 100 - compressionRatio;

        System.out.println("Compression Ratio: " + String.format("%.2f", compressionRatio) + "%");
        System.out.println("Compression Factor: " + String.format("%.2f", compressionFactor) + "%");
    }

    private static void generateCodes(AlpStatus node, String code) {
        if (node == null) {
            return;
        }

        // 리프 노드인 경우 코드 저장
        if (node.left == null && node.right == null) {
            huffmanCodes.put((char) ('a' + node.character), code);
        }

        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");

    }


    private static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
        return content.toString();
    }

    private static void writeFile(String filePath, String content) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
            System.out.println("Encoded text written to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static long getFileSize(String filePath) {
        File file = new File(filePath);
        return file.length();
    }
}
