import java.util.Scanner; : digunakan untuk mengimpor kelas Scanner.

public class UasAtlet {} : digunakan untuk mendeklarasikan kelas.

public static void main(String[] args) {} : sebagai main methode.

Scanner sc = new Scanner(System.in); : merupakan objek Scanner untuk membaca inputan.

if (jumlahPoliteknik <= 0) {
    System.out.println("Jumlah politeknik tidak valid.");
    return; 
} 
: apabila jumlah politeknik <= 0 maka program akan keluar/terhenti.

 String[][][] atlet = new String[3][jumlahPoliteknik][3]; : menandakan array 3 dimensi.

 String[] cabor = {"Badminton", "Basket", "Bola Voli"}; : array 1 dimensi yang berisi nama dari cabor.

for (int olahraga = 0; olahraga < cabor.length; olahraga++) {
    System.out.println("Masukkan nama atlet untuk cabang " + cabor[olahraga] + ":");
    for (int politeknik = 0; politeknik < jumlahPoliteknik; politeknik++) {
        System.out.println("Politeknik " + (politeknik + 1) + ":");
        for (int nama = 0; nama < 3; nama++) {
            System.out.print("Atlet " + (nama + 1) + ": ");
            String namaAtlet = sc.nextLine(); 
        }
    }
}
: merupakan perulangan dari setiap cabor, politeknik, dan atlet. 
                    
if (namaAtlet.isEmpty()) {
    System.out.println("Nama atlet tidak boleh kosong. Silakan masukkan kembali.");
    nama--;
} else {
    atlet[olahraga][politeknik][nama] = namaAtlet;
}
: apabila nama atlet kosong maka akan mengulang untuk memasukkan nama atlet kembali, namun jika nama atlet terisi maka nama atlet akan tersimpan di array atlet.

System.out.println("\nInformasi Nama Atlet:");
for (int olahraga = 0; olahraga < cabor.length; olahraga++) {
    System.out.println("Cabang " + cabor[olahraga] + ":");
    for (int politeknik = 0; politeknik < jumlahPoliteknik; politeknik++) {
        System.out.println("Politeknik " + (politeknik + 1) + ":");
        for (int nama = 0; nama < 3; nama++) {
            System.out.println("Atlet " + (nama + 1) + ": " + atlet[olahraga][politeknik][nama]);
        }
    }
}
: untuk menampilkan informasi para atlet dari perulangan setiap cabor, politeknik, dan nama atlet 