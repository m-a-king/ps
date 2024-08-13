package baekJoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BJ13317 {

    private static class Edge {
        int u, v, d;

        public Edge(int u, int v, int d) {
            this.u = u;
            this.v = v;
            this.d = d;
        }
    }

//    private static boolean bellmanFord(boolean isWrong, int n, List<Edge> edges) {
//        int[] dist = new int[n + 1];
//        Arrays.fill(dist, Integer.MAX_VALUE);
//        dist[1] = 0;
//
//        for (int i = 0; i < n - (isWrong ? 2 : 1); i++) {
//            for (Edge edge : edges) {
//                int u = edge.u;
//                int v = edge.v;
//                int d = edge.d;
//
//                if (dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + d) {
//                    dist[v] = dist[u] + d;
//                }
//            }
//        }
//
//        for (Edge edge : edges) {
//            int u = edge.u;
//            int v = edge.v;
//            int d = edge.d;
//
//            if (dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + d) {
//                return false;  // 음수 사이클이 존재함
//            }
//        }
//
//        return true;  // 음수 사이클이 존재하지 않음
//    }

    public static void main(String[] args) {
        int n = 50;
        int m = n;

        List<Edge> edges = new ArrayList<>();
//        for (int i = 1; i < m; i++) {
//            edges.add(new Edge(i, i + 1, -1));
//        }
//        edges.add(new Edge(1, n, -1));


        for (int i = 0; i < m - 1; i++) {
            edges.add(new Edge(m - i - 1, m - i, -1));
        }
        edges.add(new Edge(1, n, -1));

//        boolean correct = bellmanFord(false, n, edges);
//        boolean wrong = bellmanFord(true, n, edges);

//        if (correct != wrong) {
            System.out.println(n + " " + m);
            for (Edge edge : edges) {
                System.out.println(edge.u + " " + edge.v + " " + edge.d);
            }
//        }
    }
}

/*
* 그래프 구성

정점

	•	A, B, C, D, E

간선 가중치

	•	모든 간선의 가중치는 -1

1. 정방향 연결

간선의 연결 순서:

	•	A → B (가중치 -1)
	•	B → C (가중치 -1)
	•	C → D (가중치 -1)
	•	D → E (가중치 -1)
	•	E → A (가중치 -1)

벨만-포드 알고리즘 수행

초기 상태:

	•	시작점 A에서 출발:
	•	dist[A] = 0 (시작점이므로)
	•	dist[B] = ∞
	•	dist[C] = ∞
	•	dist[D] = ∞
	•	dist[E] = ∞

1번째 반복:

	1.	A → B:
	•	dist[B] = dist[A] + (-1) = 0 + (-1) = -1
	2.	B → C:
	•	dist[C] = dist[B] + (-1) = -1 + (-1) = -2
	3.	C → D:
	•	dist[D] = dist[C] + (-1) = -2 + (-1) = -3
	4.	D → E:
	•	dist[E] = dist[D] + (-1) = -3 + (-1) = -4
	5.	E → A:
	•	dist[A] = dist[E] + (-1) = -4 + (-1) = -5
	•	여기서 A가 갱신되었지만, 이 값은 다음 반복에서 다른 정점들에게 다시 반영됩니다.

2번째 반복:

	1.	A → B:
	•	dist[B] = dist[A] + (-1) = -5 + (-1) = -6
	2.	B → C:
	•	dist[C] = dist[B] + (-1) = -6 + (-1) = -7
	3.	C → D:
	•	dist[D] = dist[C] + (-1) = -7 + (-1) = -8
	4.	D → E:
	•	dist[E] = dist[D] + (-1) = -8 + (-1) = -9
	5.	E → A:
	•	dist[A] = dist[E] + (-1) = -9 + (-1) = -10
	•	이 과정이 반복되면 결국 사이클로 인해 값이 계속 줄어들게 됩니다.

3번째, 4번째 반복:

	•	동일한 방식으로 값이 갱신되어, 음수 사이클이 있다는 것을 감지할 수 있습니다.

2. 역순 연결

간선의 연결 순서:

	•	E → A (가중치 -1)
	•	D → E (가중치 -1)
	•	C → D (가중치 -1)
	•	B → C (가중치 -1)
	•	A → B (가중치 -1)

벨만-포드 알고리즘 수행

초기 상태:

	•	시작점 A에서 출발:
	•	dist[A] = 0
	•	dist[B] = ∞
	•	dist[C] = ∞
	•	dist[D] = ∞
	•	dist[E] = ∞

1번째 반복:

	1.	E → A:
	•	dist[A] = dist[E] + (-1)이지만, dist[E] = ∞이므로 여전히 dist[A] = 0 (변화 없음)
	2.	D → E:
	•	dist[E] = dist[D] + (-1)이지만, dist[D] = ∞이므로 여전히 dist[E] = ∞
	3.	C → D:
	•	dist[D] = dist[C] + (-1)이지만, dist[C] = ∞이므로 여전히 dist[D] = ∞
	4.	B → C:
	•	dist[C] = dist[B] + (-1)이지만, dist[B] = ∞이므로 여전히 dist[C] = ∞
	5.	A → B:
	•	dist[B] = dist[A] + (-1) = 0 + (-1) = -1
	•	여기서 비로소 B가 갱신되었음.

2번째 반복:

	1.	E → A:
	•	dist[A] = dist[E] + (-1)이지만, dist[E] = ∞이므로 여전히 dist[A] = 0 (변화 없음)
	2.	D → E:
	•	dist[E] = dist[D] + (-1)이지만, dist[D] = ∞이므로 여전히 dist[E] = ∞
	3.	C → D:
	•	dist[D] = dist[C] + (-1)이지만, dist[C] = ∞이므로 여전히 dist[D] = ∞
	4.	B → C:
	•	dist[C] = dist[B] + (-1) = -1 + (-1) = -2
	•	여기서 C가 갱신되었습니다.
	5.	A → B:
	•	dist[B] = dist[A] + (-1)이지만, 이미 갱신된 값이므로 변화 없음.

3번째 반복:

	1.	E → A:
	•	dist[A] = dist[E] + (-1)이지만, dist[E] = ∞이므로 여전히 dist[A] = 0 (변화 없음)
	2.	D → E:
	•	dist[E] = dist[D] + (-1)이지만, dist[D] = ∞이므로 여전히 dist[E] = ∞
	3.	C → D:
	•	dist[D] = dist[C] + (-1) = -2 + (-1) = -3
	•	여기서 D가 갱신되었습니다.
	4.	B → C:
	•	dist[C] = dist[B] + (-1)이지만, 이미 갱신된 값이므로 변화 없음.
	5.	A → B:
	•	dist[B] = dist[A] + (-1)이지만, 이미 갱신된 값이므로 변화 없음.

결과:

	•	이 경우, 간선들이 역순으로 탐색되면서 dist 배열의 값들이 뒤늦게 갱신됩니다.
	•	만약 이 과정을 n-2번만 반복했다면, 간선들 중 일부는 탐색되지 않아, 최종 결과에서 음수 사이클이 제대로 감지되지 않을 수 있습니다.

결론

정방향 연결에서는 최단 경로가 자연스럽게 갱신되므로, 반복 횟수가 부족해도 최종 결과에 큰 영향을 미치지 않을 수 있습니다. 반면 역순 연결에서는 정점들이 뒤늦게 갱신되며, 특히 잘못된 반복(n-2번)에서 음수 사이클이 제대로 반영되지 않을 수 있습니다. 이로 인해 벨만-포드 알고리즘의 정확한 반복 횟수와 탐색 순서가 매우 중요하다는 것을 알 수 있습니다.
* */