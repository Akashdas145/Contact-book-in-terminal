import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ContactManager {
    private static final String FILE_NAME = "contacts.dat";
    private ArrayList<Contact> contacts = new ArrayList<>();

    // Load contacts from file
    @SuppressWarnings("unchecked")
    public void loadContacts() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            contacts = (ArrayList<Contact>) ois.readObject();
            System.out.println("Contacts loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }

    // Save contacts to file
    public void saveContacts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    // Add a new contact
    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact added successfully.");
    }

    // View all contacts
    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }
        System.out.println("\nHere is the Contact List:");
        for (Contact c : contacts) {
            System.out.println(c);
        }
    }

    // Search contact
    public void searchContact(String key) {
        boolean found = false;
        for (Contact c : contacts) {
            if (c.getName().toLowerCase().contains(key.toLowerCase()) || c.getPhone().contains(key)) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found) System.out.println("No matching contact found.");
    }

    // Delete contact
    public void deleteContact(String name) {
        boolean removed = contacts.removeIf(c -> c.getName().equalsIgnoreCase(name));
        if (removed) System.out.println("Contact deleted successfully!");
        else System.out.println("Contact not found.");
    }
}

