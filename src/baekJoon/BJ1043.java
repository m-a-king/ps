package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ1043 {

    private static int[] parent;

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int M = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int peopleKnowTruthCount = Integer.parseInt(stringTokenizer.nextToken());
        if (peopleKnowTruthCount == 0) {
            System.out.println(M);
            return;
        }

        Set<Integer> truthPeople = new HashSet<>();
        if (peopleKnowTruthCount > 0) {
            for (int i = 0; i < peopleKnowTruthCount; i++) {
                int person = Integer.parseInt(stringTokenizer.nextToken());
                truthPeople.add(person);
            }
        }

        List<int[]> parties = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int participantCount = Integer.parseInt(stringTokenizer.nextToken());
            int[] participants = new int[participantCount];

            for (int j = 0; j < participantCount; j++) {
                participants[j] = Integer.parseInt(stringTokenizer.nextToken());
            }

            for (int j = 1; j < participantCount; j++) {
                union(participants[0], participants[j]);
            }

            parties.add(participants);
        }

        Set<Integer> truthGroups = new HashSet<>();
        for (int person : truthPeople) {
            truthGroups.add(find(person)); // 진실을 아는 사람들의 대표 노드 저장
        }

        int count = 0;
        for (int[] party : parties) {
            int partyRepresentative = find(party[0]); // 파티의 대표 그룹 찾기
            if (!truthGroups.contains(partyRepresentative)) {
                count++; // 진실을 모르는 그룹이면 과장된 이야기 가능
            }
        }

        System.out.println(count);
    }
}
