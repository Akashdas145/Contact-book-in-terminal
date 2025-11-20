import java.util.Scanner;

public class Main {
     public static void main(String[] args) {
        ContactManager manager = new ContactManager();
        Scanner sc = new Scanner(System.in);
        manager.loadContacts();

        while (true) {
            System.out.println("\n===== CONTACT BOOK MENU =====");
            System.out.println("1. Add Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! Try again.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    manager.addContact(new Contact(name, phone, email));
                }
                case 2 -> manager.viewContacts();
                case 3 -> {
                    System.out.print("Enter name or phone to search: ");
                    String key = sc.nextLine();
                    manager.searchContact(key);
                }
                case 4 -> {
                    System.out.print("Enter name to delete: ");
                    String name = sc.nextLine();
                    manager.deleteContact(name);
                }
                case 5 -> {
                    manager.saveContacts();
                    System.out.println("Contacts saved. Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
