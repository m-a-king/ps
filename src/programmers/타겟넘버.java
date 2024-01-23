package programmers;

    class 타겟넘버 {

        public int solution(int[] numbers, int target) {

            return dfs(0, 0, numbers, target);
        }

        private static int dfs(int total, int current, int[] numbers, int target) {

            if (current == numbers.length ) {
                return total == target ? 1 : 0;
            }

            return dfs(total + numbers[current], current + 1, numbers, target) + dfs(total - numbers[current], current + 1, numbers, target);

        }
    }
