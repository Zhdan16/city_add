package zhdans.city_people;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import zhdans.city_people.entity.City;
import zhdans.city_people.entity.Person;

import java.util.Scanner;

public class PersonAdd {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.print("1) Изменить существующего человека\n2) Создать нового человека\nВыберите действие: ");
        int num = Integer.parseInt(scanner.nextLine());

        String name = "";
        City city = new City();
        Person person = new Person();

        if (num == 1){
            System.out.print("\nВведите id человека: ");
            String person_id = scanner.nextLine();

            System.out.print("Введите имя человека: ");
            name = scanner.nextLine();

            System.out.print("Введите id города: ");
            int city_id = Integer.parseInt(scanner.nextLine());

            city = manager.find(City.class, city_id);
            person = manager.find(Person.class, person_id);

        }else if (num == 2){
            System.out.print("\nВведите имя человека: ");
            name = scanner.nextLine();

            System.out.print("Введите id города: ");
            int city_id = Integer.parseInt(scanner.nextLine());

            city = manager.find(City.class, city_id);
            person = new Person();

        }

        try {
            manager.getTransaction().begin();
            person.setName(name);
            person.setCity(city);

            manager.persist(person);
            manager.getTransaction().commit();
        }catch (Exception e){
            manager.getTransaction().rollback();
        }
        manager.close();
        factory.close();
    }

}
