import java.util.Scanner;

public class Moo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input Nama Sapi
        String namaSapi;
        while (true) {
            namaSapi = scanner.nextLine();
            if (namaSapi.matches("[a-zA-Z]+")) {
                break;
            } else {
                System.out.println("Mooo! Nama sapi harus pakai huruf, bukan angka atau simbol!");
            }
        }
        
        // Input Berat Sapi
        int berat = 0;
        while (true) {
            if (scanner.hasNextInt()) {
                berat = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (berat >= 1 && berat <= 80) {
                    break;
                } else {
                    System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
                }
            } else {
                System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
                scanner.nextLine(); // consume invalid input
            }
        }
        
        // Input Jenis Layanan
        String layanan;
        while (true) {
            layanan = scanner.nextLine();
            if (layanan.equals("spa") || layanan.equals("potong_kuku") || layanan.equals("grooming")) {
                break;
            } else {
                System.out.println("Pilih spa, potong_kuku, atau grooming! Sapi kamu mau dirawat apa, sih?");
            }
        }
        
        // Input Kelas Layanan
        String kelas;
        while (true) {
            kelas = scanner.nextLine();
            if (kelas.equals("reguler") || kelas.equals("vip")) {
                break;
            } else {
                System.out.println("Pilih reguler atau vip! Sapi kamu mau treatment sultan atau biasa aja?");
            }
        }
        
        // Hitung biaya
        double hargaPerKg = 0;
        switch (layanan) {
            case "spa":
                hargaPerKg = 8000;
                break;
            case "potong_kuku":
                hargaPerKg = 6000;
                break;
            case "grooming":
                hargaPerKg = 10000;
                break;
        }
        
        double biayaDasar = berat * hargaPerKg;
        double diskon = 0;
        if (berat > 30) {
            diskon = biayaDasar * 0.1;
        }
        
        double biayaTambahanVIP = 0;
        if (kelas.equals("vip")) {
            biayaTambahanVIP = biayaDasar * 0.2;
        }
        
        double subtotal = biayaDasar - diskon + biayaTambahanVIP;
        double pajak = subtotal * 0.08;
        double totalBiaya = subtotal + pajak;
        
        // Cek nama sapi spesial
        boolean sapiSpesial = namaSapi.equals("Moo") || namaSapi.equals("Mooo") || namaSapi.equals("Moooo");
        if (sapiSpesial) {
            totalBiaya = 0;
        }
        
        // Output struk
        System.out.println("============= NOTA KLINIK SAPI =============");
        System.out.println("Nama Sapi: " + namaSapi);
        System.out.println("Berat: " + berat + " kg");
        
        String displayLayanan = "";
        switch (layanan) {
            case "spa":
                displayLayanan = "spa";
                break;
            case "potong_kuku":
                displayLayanan = "potong_kuku";
                break;
            case "grooming":
                displayLayanan = "grooming";
                break;
        }
        System.out.println("Jenis Layanan: " + displayLayanan);
        System.out.println("Kelas: " + kelas);
        System.out.println("Biaya Dasar: Rp " + String.format("%.1f", biayaDasar));
        System.out.println("Diskon: Rp " + String.format("%.1f", diskon));
        System.out.println("Biaya Tambahan VIP: Rp " + String.format("%.1f", biayaTambahanVIP));
        System.out.println("Subtotal: Rp " + String.format("%.1f", subtotal));
        System.out.println("Pajak: Rp " + String.format("%.1f", pajak));
        System.out.println("Total Biaya: Rp " + String.format("%.1f", totalBiaya));
        System.out.println("============================================");
        
        if (sapiSpesial) {
            System.out.println("Terima kasih, " + namaSapi + " ! Sapi spesial memang beda perlakuan~");
        } else {
            System.out.println("Terima kasih, " + namaSapi + " ! Semoga sapinya makin glow up.");
        }
        
        scanner.close();
    }
}