package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BJ10814 {

    static class Person implements Comparable<Person> {
        int age;
        String name;
        int idx;

        public Person(int age, String name, int idx) {
            this.age = age;
            this.name = name;
            this.idx = idx;
        }

        @Override
        public int compareTo(Person o) {
            if (this.age == o.age) {
                return this.idx - o.idx;
            } else {
                return this.age - o.age;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());

        List<Person> people = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] s = bufferedReader.readLine().split(" ");
            people.add(new Person(parseInt(s[0]), s[1], i));

        }

        Collections.sort(people);

        for (Person p : people) {
            System.out.println(p.age + " " + p.name);
        }
    }
}
