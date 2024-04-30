package programmers;

import java.util.*;

public class 베스트앨범 {


    class Solution {

        private static class Genre {
            int idx, plays;

            public Genre(int idx, int plays) {
                this.idx = idx;
                this.plays = plays;
            }
        }

        public int[] solution(String[] genres, int[] plays) {

            HashMap<String, Integer> accMap = new HashMap<>();
            HashMap<String, List<Genre>> listMap = new HashMap<>();
            int idx = 0;

            for (String genre : genres) {
                accMap.put(genre, accMap.getOrDefault(genre, 0) + plays[idx]);
                List<Genre> list = listMap.getOrDefault(genre, new ArrayList<>());
                list.add(new Genre(idx, plays[idx++]));
                listMap.put(genre, list); // 이 리스트를 다시 맵에 저장합니다.
            }

            List<Map.Entry<String, Integer>> entryList = new ArrayList<>(accMap.entrySet());
            Collections.sort(entryList, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
            List<Integer> answer = new ArrayList<>();

            for (Map.Entry<String, Integer> entry : entryList) {
                List<Genre> list = listMap.get(entry.getKey());
                list.sort((a, b) -> b.plays - a.plays);

                int count = 0;
                for (Genre genre : list) {
                    count++;
                    answer.add(genre.idx);
                    if (count == 2) {
                        break;
                    }
                }
            }

            return answer.stream().mapToInt(i -> i).toArray();
        }


    }
}