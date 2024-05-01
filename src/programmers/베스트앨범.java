package programmers;

import java.util.*;

public class 베스트앨범 {


    class Solution {

        // 각 노래의 입력 순서와 재생 횟수를 표현할 수 있는 클래스 정의
        private static class Song implements Comparable<Song>{
            int idx, play;

            public Song(int idx, int play) {
                this.idx = idx;
                this.play = play;
            }

            // 우선 순위 큐 사용을 위한 비교 메서드 정의 (내림차순)
            @Override
            public int compareTo(Song o) {
                return o.play - this.play;
            }
        }

        public int[] solution(String[] genres, int[] plays) {

            // 각 장르의 총 재생 횟수를 저장
            HashMap<String, Integer> accMap = new HashMap<>();

            // 각 장르에 포함되는 노래들을 우선 순위 큐를 사용해서 저장
            HashMap<String, PriorityQueue<Song>> pqMap = new HashMap<>();

            // plays 배열 요소를 순회하기 위한 인덱스
            int idx = 0;

            for (String genre : genres) {
                // 해당 장르에 재생 횟수를 더함
                accMap.put(genre, accMap.getOrDefault(genre, 0) + plays[idx]);

                // 해당 장르에 Song 객체를 추가함
                // getOrDefault를 사용했기 때문에 아래와 같이 적으면 안됨 (Default 로 생성되는 pq에서 문제 발생)
                // (오류 발생) pqMap.getOrDefault(genre, new PriorityQueue<>()).offer(new Song(idx, plays[idx++]);
                PriorityQueue<Song> pq = pqMap.getOrDefault(genre, new PriorityQueue<>());
                pq.offer(new Song(idx, plays[idx++]));
                pqMap.put(genre, pq);
            }

            // 각 장르의 총 재생 횟수를 저장하는 accMap을 Map.Entry<String, Integer> 형태로 리스트로 변환
            List<Map.Entry<String, Integer>> entryList = new ArrayList<>(accMap.entrySet());

            // 리스트의 요소(Entry)들을 총 재생 횟수를 기준으로 내림차순 정렬
            Collections.sort(entryList, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

            // 정답 저장 리스트
            // 배열로 구현시 각 장르에 1개의 곡이 있으면 예외 처리가 필요하므로 리스트로 정답을 저장
            List<Integer> answer = new ArrayList<>();

            // 정렬된 entryList의 요소를 순회
            for (Map.Entry<String, Integer> entry : entryList) {
                // 해당 장르에 속하는 우선 순위 큐를 가져옴
                PriorityQueue<Song> pq = pqMap.get(entry.getKey());

                int count = 0;
                // pq가 비어있지 않아야 함 && 각 장르당 노래가 2개를 초과하면 안됨
                while (!pq.isEmpty() && count++ != 2) {
                    // 우선 순위 큐이므로 해당 장르의 가장 많은 재생 횟수를 가진 노래부터 dequeue
                    Song song = pq.poll();
                    // dequeue 된 노래의 입력 순서를 저장
                    answer.add(song.idx);
                }
            }

            // 리스트로 저장된 answer 를 배열로 변경해서 출력 양식을 맞춤.
            return answer.stream().mapToInt(i -> i).toArray();
        }


    }
}