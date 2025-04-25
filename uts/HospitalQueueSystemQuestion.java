import java.util.ArrayList; // digunakan untuk menyimpan antrean pasien.
import java.util.Scanner; // digunakan untuk mengambil input dari pengguna melalui konsol.

public class HospitalQueueSystemQuestion { // Ini adalah kelas utama yang berisi semua logika manajemen antrean rumah sakit.
    private static ArrayList<Patient> patientQueue = new ArrayList<>(); // list untuk menyimpan pasien-pasien secara berurutan berdasarkan prioritas.
    private static Scanner scanner = new Scanner(System.in); // membuat objek scanner untuk input pengguna

    // Menampilkan menu dan menerima input pengguna dalam loop, dan memanggil fungsi berdasarkan pilihan yang dipilih, dan pilihan no 6 untuk keluar dari program.
    public static void main(String[] args) {
        boolean running = true;

        System.out.println("Welcome to Hospital Queue Management System");

        while (running) {
            displayMenu();
            int choice = getValidIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    serveNextPatient();
                    break;
                case 3:
                    displayQueue();
                    break;
                case 4:
                    updatePriority();
                    break;
                case 5:
                    searchPatient();
                    break;
                case 6:
                    System.out.println("Thank you for using Hospital Queue Management System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Menampilkan daftar menu pilihan kepada pengguna
    private static void displayMenu() {
        System.out.println("\n===== HOSPITAL QUEUE SYSTEM =====");
        System.out.println("1. Add a new patient to the queue");
        System.out.println("2. Serve next patient");
        System.out.println("3. Display current queue");
        System.out.println("4. Update patient priority");
        System.out.println("5. Search for a patient");
        System.out.println("6. Exit");
        System.out.println("=================================");
    }

    // Mengambil data pasien dari input pengguna, Menentukan posisi insert berdasarkan prioritas (semakin kecil nilai, semakin tinggi prioritas), Menambahkan pasien ke antrean pada posisi yang sesuai.
    private static void addPatient() {
        System.out.println("\n--- Add New Patient ---");
        String name = getValidStringInput("Enter patient's name: ");
        int age = getValidIntInput("Enter patient's age: ");
        String condition = getValidStringInput("Enter condition description: ");
        int priority = getValidIntInRange("Enter priority (1-Critical to 5-Non-urgent): ", 1, 5);

        Patient newPatient = new Patient(name, age, condition, priority);

        int index = 0;
        while (index < patientQueue.size() && patientQueue.get(index).getPriority() <= priority) {
            index++;
        }
        patientQueue.add(index, newPatient);
        System.out.println("Patient added successfully.");
    }

    // Mengambil dan menghapus pasien pertama dari antrean (prioritas tertinggi), Menampilkan data pasien yang sedang dilayani. 
    private static void serveNextPatient() {
        if (patientQueue.isEmpty()) {
            System.out.println("No patients in queue.");
        } else {
            Patient next = patientQueue.remove(0);
            System.out.println("Serving patient: " + next.getName() +
                " | Age: " + next.getAge() +
                " | Condition: " + next.getCondition() +
                " | Priority: " + getPriorityText(next.getPriority()));
        }
    }

    // Menampilkan seluruh pasien dalam antrean beserta detailnya.
    private static void displayQueue() {
        if (patientQueue.isEmpty()) {
            System.out.println("No patients in queue.");
        } else {
            System.out.println("\n--- Current Patient Queue ---");
            for (int i = 0; i < patientQueue.size(); i++) {
                Patient p = patientQueue.get(i);
                System.out.println((i + 1) + ". " + p.getName() +
                    " | Age: " + p.getAge() +
                    " | Condition: " + p.getCondition() +
                    " | Priority: " + getPriorityText(p.getPriority()));
            }
        }
    }

    // Mencari pasien jika ditemukan maka dapat menghapus antrean, ubah prioritas, dan menambahkan ke antrean berdasarkan prioritas baru. Jika kosong maka keluar dari fungsi
    private static void updatePriority() {
        if (patientQueue.isEmpty()) {
            System.out.println("No patients to update.");
            return;
        }

        String name = getValidStringInput("Enter the patient's name to update: ");
        boolean found = false;

        for (int i = 0; i < patientQueue.size(); i++) {
            Patient p = patientQueue.get(i);
            if (p.getName().equalsIgnoreCase(name)) {
                int newPriority = getValidIntInRange("Enter new priority (1-Critical to 5-Non-urgent): ", 1, 5);
                patientQueue.remove(i); 
                p.setPriority(newPriority);

                int index = 0;
                while (index < patientQueue.size() && patientQueue.get(index).getPriority() <= newPriority) {
                    index++;
                }
                patientQueue.add(index, p);
                System.out.println("Priority updated successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Patient not found.");
        }
    }

    // Mencari pasien dalam antrean dan menampilkan jika ditemukan.
    private static void searchPatient() {
        if (patientQueue.isEmpty()) {
            System.out.println("No patients in queue.");
            return;
        }

        String name = getValidStringInput("Enter the patient's name to search: ");
        boolean found = false;

        for (Patient p : patientQueue) {
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.println("Found: " + p.getName() +
                    " | Age: " + p.getAge() +
                    " | Condition: " + p.getCondition() +
                    " | Priority: " + getPriorityText(p.getPriority()));
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Patient not found.");
        }
    }

    // Mengubah angka prioritas (1–5) menjadi string yang lebih informatif dan mudah dibaca.
    private static String getPriorityText(int priority) {
        switch (priority) {
            case 1:
                return "1-Critical";
            case 2:
                return "2-Urgent";
            case 3:
                return "3-High";
            case 4:
                return "4-Medium";
            case 5:
                return "5-Non-urgent";
            default:
                return "Unknown";
        }
    }
    
    // Meminta input angka dari user. Akan terus mengulang hingga input yang diberikan adalah angka valid (integer).
    private static int getValidIntInput(String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return value;
    }

    // Memastikan bahwa angka yang dimasukkan berada dalam rentang tertentu (termasuk batas).
    private static int getValidIntInRange(String prompt, int min, int max) {
        int value;
        while (true) {
            value = getValidIntInput(prompt);
            if (value >= min && value <= max) {
                break;
            }
            System.out.println("Please enter a value between " + min + " and " + max + ".");
        }
        return value;
    }

    // Meminta input teks (string) dari user dan memastikan input tersebut tidak kosong.
    private static String getValidStringInput(String prompt) {
        String value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                break;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
        return value;
    }
}

// Digunakan untuk membuat objek Patient baru dengan data lengkap.
class Patient {
    private String name;
    private int age;
    private String condition;
    private int priority; // 1 (Critical) to 5 (Non-urgent)

    public Patient(String name, int age, String condition, int priority) {
        this.name = name;
        this.age = age;
        this.condition = condition;
        this.priority = priority;
    }

    // Metode untuk mengambil nilai nama.
    public String getName() {
        return name;
    }

    // Metode untuk mengambil nilai umur.
    public int getAge() {
        return age;
    }

    // Metode untuk mengambil nilai kondisi pasien.
    public String getCondition() {
        return condition;
    }

    // Metode untuk mengambil nilai prioritas.
    public int getPriority() {
        return priority;
    }

    // Hanya atribut priority yang dapat diubah setelah objek dibuat.
    public void setPriority(int priority) {
        this.priority = priority;
    }
}