import java.util.Scanner;
public class UasAtlet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan jumlah politeknik (harus lebih dari 0): ");
        int jumlahPoliteknik = sc.nextInt();
        sc.nextLine(); 
        
        if (jumlahPoliteknik <= 0) {
            System.out.println("Jumlah politeknik tidak valid.");
            return; 
        }

        String[][][] atlet = new String[3][jumlahPoliteknik][3];
        String[] cabor = {"Badminton", "Basket", "Bola Voli"};

        for (int olahraga = 0; olahraga < cabor.length; olahraga++) {
            System.out.println("Masukkan nama atlet untuk cabang " + cabor[olahraga] + ":");
            for (int politeknik = 0; politeknik < jumlahPoliteknik; politeknik++) {
                System.out.println("Politeknik " + (politeknik + 1) + ":");
                for (int nama = 0; nama < 3; nama++) {
                    System.out.print("Atlet " + (nama + 1) + ": ");
                    String namaAtlet = sc.nextLine();
                    
                    if (namaAtlet.isEmpty()) {
                        System.out.println("Nama atlet tidak boleh kosong. Silakan masukkan kembali.");
                        nama--;
                    } else {
                        atlet[olahraga][politeknik][nama] = namaAtlet;
                    }
                }
            }
        }

        System.out.println("\nInformasi Nama Atlet:");
        for (int olahraga = 0; olahraga < cabor.length; olahraga++) {
            System.out.println("Cabang " + cabor[olahraga] + ":");
            for (int politeknik = 0; politeknik < jumlahPoliteknik; politeknik++) {
                System.out.println("Politeknik " + (politeknik + 1) + ":");
                for (int nama = 0; nama < 3; nama++) {
                    System.out.println("Atlet " + (nama + 1) + ": " + atlet[olahraga][politeknik][nama]);
                }
                System.out.println();
            }
        }
    }
}