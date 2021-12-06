package com.company;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {

        Person person = new Person.Builder("Kirill", 21).height(180).weight(69).build();
        var stream = ThreadLocalRandom.current().ints().limit(20);
        stream.forEachOrdered(System.out::println);

        Employee employee = Person.valueOf();


    }


    static class Holder {
        private int x;

        public Holder(int x) {
            this.x = x;
        }

        public static Holder valueOf(int x) {
            return new Holder(x);
        }
    }


    static class Person {

        private String name;
        private int age;
        private int height;
        private int weight;

        static class Builder {
            private String name;
            private int age;
            private int height = 0;
            private int weight = 0;

            public Builder(String name, int age) {
                this.name = name;
                this.age = age;
            }

            public Builder height(int value) {
                height = value;
                return this;
            }

            public Builder weight(int value) {
                weight = value;
                return this;
            }

            public Person build() {
                return new Person(this);
            }

        }

        private Person(Builder builder) {
            name = builder.name;
            age = builder.age;
            weight = builder.weight;
            height = builder.height;
        }

        public static Employee valueOf() {
            return new Employee("lr",21);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && height == person.height && weight == person.weight && name.equals(person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, height, weight);
        }
    }


}




