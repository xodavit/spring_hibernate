package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      userService.add(new User("A", "A", "user11@mail.ru",new Car("BMW_39",11111111)));
      userService.add(new User("B", "B", "user22@mail.ru",new Car("BMW_46",22222222)));
      userService.add(new User("C", "C", "user33@mail.ru",new Car("BMW_39",33333333)));
      userService.add(new User("D", "D", "user44@mail.ru",new Car("BMW_46",44444444)));

      users = userService.listUsers();
      for (User user : users) {
         if (user.getCar() != null) {
            System.out.println("Id = "+user.getId());
            System.out.println("First Name = "+user.getFirstName());
            System.out.println("Last Name = "+user.getLastName());
            System.out.println("Email = "+user.getEmail());
            System.out.println("Car = model: "+user.getCar().getModel()+", series: "+ user.getCar().getSeries());
            System.out.println();
         }
      }

      System.out.println(userService.getUserByCar("BMW_39",11111111));

      context.close();
   }
}
