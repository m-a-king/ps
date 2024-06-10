package test.huffmanCoding;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HuffmanDecoding {

    // 허프만 인코딩을 위한 테이블과 키, 값 위치 변경 (인코딩의 역순)
    static Map<String, Character> huffmanCodes = new HashMap<>();

    public static void main(String[] args) {
        String filePath = "src/test/huffmanCoding/encodedText.txt";  // 텍스트 파일 경로
        String text = Objects.requireNonNull(readFile(filePath)).trim();

        System.out.println();
        System.out.println(">>>>>>>>>> ENCODED TEXT START <<<<<<<<<<");
        System.out.println(text);
        System.out.println(">>>>>>>>>>> ENCODED TEXT END <<<<<<<<<<<");
        System.out.println();

        // 헤더와 인코딩된 텍스트 분리
        String[] parts = text.split("\n", 2);
        String header = parts[0].substring("header = ".length());
        String encodedText = parts[1].substring("encodedText = ".length());

        parseHeader(header);

        String decodedText = decodeText(encodedText); // == originalText

        System.out.println();
        System.out.println(">>>>>>>>>> DECODED TEXT START <<<<<<<<<<");
        System.out.println(decodedText);
        System.out.println(">>>>>>>>>>> DECODED TEXT END <<<<<<<<<<<");
        System.out.println();

        // 디코딩된 텍스트를 파일에 작성
        String outputFilePath = "src/test/huffmanCoding/decodedText.txt"; // 출력 파일 경로 설정
        writeFile(outputFilePath, decodedText);
    }


    private static String decodeText(String encodedText) {
        StringBuilder decodedText = new StringBuilder();
        String tempText = "";

        for (char bit : encodedText.toCharArray()) {
            tempText += bit;

            if (huffmanCodes.containsKey(tempText)) {
                decodedText.append(huffmanCodes.get(tempText));
                tempText = "";
            }
        }

        return decodedText.toString();
    }

    private static void parseHeader(String header) {
        // 중괄호 제거
        header = header.substring(1, header.length() - 1);

        // 쉼표를 기준으로 키-값 쌍 분리
        String[] pairs = header.split(", ");

        for (String pair : pairs) {
            // 등호를 기준으로 키와 값 분리
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                // 키는 문자열, 값은 문자로 변환하여 맵에 삽입
                char key = keyValue[0].charAt(0);
                String value = keyValue[1];
                huffmanCodes.put(value, key); // (ex) 010 : a
            }
        }
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
}
