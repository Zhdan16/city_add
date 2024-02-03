package zhdans.city_people;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import zhdans.city_people.entity.City;
import zhdans.city_people.entity.Person;

import java.util.Scanner;

public class PersonAdd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя человека: ");
        String name = scanner.nextLine();

        System.out.print("\nВведите id города: ");
        int city_id = Integer.parseInt(scanner.nextLine());

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();

            City city = manager.find(City.class, city_id);
            Person person = new Person();
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
