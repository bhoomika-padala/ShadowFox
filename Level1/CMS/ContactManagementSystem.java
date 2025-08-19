// CMS/ContactManagementSystem.java
import java.util.ArrayList;
import java.util.Scanner;

// Represents one contact
class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name  = name.trim();
        this.phone = phone.trim();
        this.email = email.trim();
    }

    public String getName()  { return name;  }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    public void setName(String name)   { this.name = name.trim(); }
    public void setPhone(String phone) { this.phone = phone.trim(); }
    public void setEmail(String email) { this.email = email.trim(); }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
    }
}

public class ContactManagementSystem {

    private static final ArrayList<Contact> contacts = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = -1;
        do {
            System.out.println("\n--- Contact Management System ---");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Update Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between 1-6.");
                continue;
            }

            switch (choice) {
                case 1: addContact();
                break;
                case 2 : viewContacts();
                break;
                case 3: searchContact();
                break;
                case 4 : updateContact();
                break;
                case 5 :deleteContact();
                break;
                case 6 :System.out.println("Exiting... Goodbye!");
                break;
                default :System.out.println("Invalid choice! Enter 1-6.");
            }
        } while (choice != 6);

        sc.close();
    }

    // ---------- CRUD ----------

    private static void addContact() {
        String name  = readNonEmpty("Enter Name: ");
        String phone = readPhone("Enter Phone (10 digits, starts 6-9): ");
        String email = readEmail("Enter Email: ");

        if (phoneExists(phone, null)) {
            System.out.println("A contact with this phone already exists.");
            return;
        }
        if (emailExists(email, null)) {
            System.out.println("A contact with this email already exists.");
            return;
        }

        contacts.add(new Contact(name, phone, email));
        System.out.println("Contact added successfully!");
    }

    private static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }
        System.out.println("\n--- Contact List ---");
        for (Contact c : contacts) System.out.println(c);
    }

    private static void searchContact() {
        String kw = readNonEmpty("Enter keyword (name/phone/email): ").toLowerCase();
        boolean found = false;

        for (Contact c : contacts) {
            if (c.getName().toLowerCase().contains(kw) ||
                c.getEmail().toLowerCase().contains(kw) ||
                c.getPhone().contains(kw)) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found) System.out.println("No contact found with that keyword.");
    }

    private static void updateContact() {
        String phoneToFind = readPhone("Enter phone number of contact to update: ");

        Contact target = null;
        for (Contact c : contacts) {
            if (c.getPhone().equals(phoneToFind)) { target = c; break; }
        }
        if (target == null) {
            System.out.println("Contact not found!");
            return;
        }

        String newName  = readNonEmpty("Enter New Name: ");
        String newPhone = readPhone("Enter New Phone (10 digits, starts 6-9): ");
        String newEmail = readEmail("Enter New Email: ");

        if (phoneExists(newPhone, target)) {
            System.out.println("Another contact already uses that phone.");
            return;
        }
        if (emailExists(newEmail, target)) {
            System.out.println("Another contact already uses that email.");
            return;
        }

        target.setName(newName);
        target.setPhone(newPhone);
        target.setEmail(newEmail);
        System.out.println("Contact updated successfully!");
    }

    private static void deleteContact() {
        String phoneToDelete = readPhone("Enter phone number of contact to delete: ");

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhone().equals(phoneToDelete)) {
                contacts.remove(i);
                System.out.println("Contact deleted successfully!");
                return;
            }
        }
        System.out.println("Contact not found!");
    }

    // ---------- Validation / Utilities ----------

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input cannot be empty. Try again.");
        }
    }

    // Phone validation 
    private static String readPhone(String prompt) {
        while (true) {
            System.out.print(prompt);
            String p = sc.nextLine().trim();

            if (p.length() != 10) {
                System.out.println("Phone must be exactly 10 digits.");
                continue;
            }
            boolean allDigits = true;
            for (char c : p.toCharArray()) {
                if (!Character.isDigit(c)) {
                    allDigits = false;
                    break;
                }
            }
            if (!allDigits) {
                System.out.println("Phone must contain only digits.");
                continue;
            }
            if (p.charAt(0) < '6' || p.charAt(0) > '9') {
                System.out.println("Phone must start with 6, 7, 8, or 9.");
                continue;
            }
            boolean allSame = true;
            for (int i = 1; i < p.length(); i++) {
                if (p.charAt(i) != p.charAt(0)) {
                    allSame = false;
                    break;
                }
            }
            if (allSame) {
                System.out.println("Phone cannot be all the same digit.");
                continue;
            }
            return p;
        }
    }

    // Email validation 
    private static String readEmail(String prompt) {
        while (true) {
            System.out.print(prompt);
            String e = sc.nextLine().trim();

            int at = e.indexOf('@');
            int lastAt = e.lastIndexOf('@');
            if (at <= 0 || at != lastAt || at == e.length() - 1) {
                System.out.println("Invalid email: must contain a single '@' not at start/end.");
                continue;
            }

            String local = e.substring(0, at);
            String domain = e.substring(at + 1);

            if (local.startsWith(".") || local.endsWith(".")) {
                System.out.println("Local part cannot start or end with a dot.");
                continue;
            }
            if (domain.startsWith(".") || domain.endsWith(".")) {
                System.out.println("Domain cannot start or end with a dot.");
                continue;
            }
            if (e.contains("..")) {
                System.out.println("Email cannot contain consecutive dots.");
                continue;
            }

            String[] labels = domain.split("\\.");
            if (labels.length < 2) {
                System.out.println("Domain must contain a dot (like example.com).");
                continue;
            }

            boolean bad = false;
            for (String label : labels) {
                if (label.isEmpty()) { bad = true; break; }
                if (label.startsWith("-") || label.endsWith("-")) { bad = true; break; }
                for (char c : label.toCharArray()) {
                    if (!(Character.isLetterOrDigit(c) || c == '-')) {
                        bad = true;
                        break;
                    }
                }
                if (bad) break;
            }

            String tld = labels[labels.length - 1];
            if (tld.length() < 2 || !tld.chars().allMatch(Character::isLetter)) {
                bad = true;
            }

            if (bad) {
                System.out.println("Invalid domain in email address.");
                continue;
            }

            return e;
        }
    }

    private static boolean phoneExists(String phone, Contact except) {
        for (Contact c : contacts) {
            if (c.getPhone().equals(phone) && c != except) return true;
        }
        return false;
    }

    private static boolean emailExists(String email, Contact except) {
        for (Contact c : contacts) {
            if (c.getEmail().equalsIgnoreCase(email) && c != except) return true;
        }
        return false;
    }
}
