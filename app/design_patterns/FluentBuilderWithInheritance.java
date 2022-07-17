package design_patterns;

public class FluentBuilderWithInheritance {

    public static class Person {

        private String name;
        private String position;
    }


    public static class PersonBuilder<SELF extends PersonBuilder<SELF>> {

        protected Person person = new Person();


        public SELF withName(String name) {
            person.name = name;
            return self();
        }


        public Person build() {
            return person;
        }


        protected SELF self() {
            return (SELF) this;
        }
    }

    public static class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {

        public EmployeeBuilder worksAt(String position) {
            person.position = position;
            return self();
        }


        @Override
        protected EmployeeBuilder self() {
            return this;
        }
    }


    public static void main(String[] args) {
        Person person = new EmployeeBuilder()
                .withName("Mike")
                .worksAt("Google")
                .build();
    }
}
