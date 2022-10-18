import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Long countJunior = persons.stream().
                filter(p -> p.getAge() < 18).
                count();
        System.out.println("Количество несовершеннолетних = " + countJunior);

        List<String> personArmy = persons.stream().
                filter(p -> p.getAge() >= 18 && p.getAge() < 27 && p.getSex() == Sex.MAN).
                map(p -> p.getFamily()).
                collect(Collectors.toList());
        System.out.println("Количество мужчин призывного возраста = " + personArmy.size());

        List<Person> personWork = persons.stream().
                filter(p -> p.getEducation() == Education.HIGHER && p.getAge() >= 18).
                filter(p -> (p.getSex() == Sex.MAN && p.getAge() <= 65) || (p.getSex() == Sex.WOMEN && p.getAge() <= 60)).
                sorted(Comparator.comparing(p -> p.getFamily())).
                collect(Collectors.toList());
        System.out.println("Количество трудоспособного населения = " + personWork.size());
        if (!personWork.isEmpty()) {
            System.out.println("Первый человек в списке = " + personWork.get(0));
            System.out.println("Последний человек в списке = " + personWork.get(personWork.size() - 1));
        }
    }
}
