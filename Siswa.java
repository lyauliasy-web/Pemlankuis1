import java.util.*;

class Vehicle {
    protected String code;
    protected String name;
    protected int pricePerDay;
    protected boolean isAvailable;
    
    public Vehicle(String code, String name, int pricePerDay) {
        this.code = code;
        this.name = name;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }
    
    public String getCode() {
        return code;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
    public int calculateRent(int days, boolean hasPromo) {
        int total = pricePerDay * days;
        if (hasPromo) {
            if (this instanceof Car) {
                total = Math.max(0, total - 20000);
            } else if (this instanceof Bike) {
                total = Math.max(0, total - 10000);
            }
        }
        return total;
    }
    
    public String getType() {
        if (this instanceof Car) return "CAR";
        if (this instanceof Bike) return "BIKE";
        return "UNKNOWN";
    }
    
    public String getStatus() {
        return isAvailable ? "TERSEDIA" : "DISEWA";
    }
    
    public void displayDetail() {
        System.out.printf("%s | %s | %s | harga: %d | status: %s\n", 
                         code, getType(), name, pricePerDay, getStatus());
    }
}

class Car extends Vehicle {
    public Car(String code, String name, int pricePerDay) {
        super(code, name, pricePerDay);
    }
}

class Bike extends Vehicle {
    public Bike(String code, String name, int pricePerDay) {
        super(code, name, pricePerDay);
    }
}

public class Siswa {
    private Map<String, Vehicle> vehicles = new HashMap<>();
    
    public void addVehicle(String type, String code, String name, int price) {
        if (vehicles.containsKey(code)) {
            System.out.println("Kendaraan sudah terdaftar");
            return;
        }
        
        Vehicle vehicle;
        if (type.equals("CAR")) {
            vehicle = new Car(code, name, price);
        } else if (type.equals("BIKE")) {
            vehicle = new Bike(code, name, price);
        } else {
            return;
        }
        
        vehicles.put(code, vehicle);
        System.out.printf("%s %s berhasil ditambahkan\n", type, code);
    }
    
    public void rentVehicle(String code, int days, boolean hasPromo) {
        if (!vehicles.containsKey(code)) {
            System.out.println("Kendaraan tidak ditemukan");
            return;
        }
        
        Vehicle vehicle = vehicles.get(code);
        if (!vehicle.isAvailable()) {
            System.out.println("Kendaraan sedang disewa");
            return;
        }
        
        int total = vehicle.calculateRent(days, hasPromo);
        vehicle.setAvailable(false);
        System.out.printf("Total sewa %s: %d\n", code, total);
    }
    
    public void returnVehicle(String code) {
        if (!vehicles.containsKey(code)) {
            System.out.println("Kendaraan tidak ditemukan");
            return;
        }
        
        Vehicle vehicle = vehicles.get(code);
        if (vehicle.isAvailable()) {
            System.out.println("Kendaraan belum disewa");
            return;
        }
        
        vehicle.setAvailable(true);
        System.out.printf("%s berhasil dikembalikan\n", code);
    }
    
    public void detailVehicle(String code) {
        if (!vehicles.containsKey(code)) {
            System.out.println("Kendaraan tidak ditemukan");
            return;
        }
        
        vehicles.get(code).displayDetail();
    }
    
    public void countVehicles() {
        System.out.printf("Total kendaraan: %d\n", vehicles.size());
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Siswa system = new Siswa();
        
        int n = Integer.parseInt(scanner.nextLine());
        
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            
            switch (parts[0]) {
                case "ADD":
                    String type = parts[1];
                    String code = parts[2];
                    String name = parts[3];
                    int price = Integer.parseInt(parts[4]);
                    system.addVehicle(type, code, name, price);
                    break;
                    
                case "RENT":
                    code = parts[1];
                    int days = Integer.parseInt(parts[2]);
                    boolean hasPromo = parts.length > 3 && parts[3].equals("PROMO");
                    system.rentVehicle(code, days, hasPromo);
                    break;
                    
                case "RETURN":
                    code = parts[1];
                    system.returnVehicle(code);
                    break;
                    
                case "DETAIL":
                    code = parts[1];
                    system.detailVehicle(code);
                    break;
                    
                case "COUNT":
                    system.countVehicles();
                    break;
            }
        }
        
        scanner.close();
    }
}