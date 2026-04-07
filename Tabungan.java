
import java.util.*;

class Student {
    protected String name;
    protected int balance;
    protected String type;
    
    public Student(String name, String type) {
        this.name = name;
        this.type = type;
        this.balance = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public int getBalance() {
        return balance;
    }
    
    public void save(int amount) {
        balance += amount;
    }
    
    public boolean take(int amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
    public String getType() {
        return type;
    }
}

class Reguler extends Student {
    public Reguler(String name) {
        super(name, "REGULER");
    }
    
    @Override
    public boolean take(int amount) {
        return super.take(amount);
    }
}

class Beasiswa extends Student {
    public Beasiswa(String name) {
        super(name, "BEASISWA");
    }
    
    @Override
    public boolean take(int amount) {
        int fee = 1000;
        int totalDeduction = amount + fee;
        if (balance >= totalDeduction) {
            balance -= totalDeduction;
            return true;
        }
        return false;
    }
}

public class Tabungan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Student> students = new HashMap<>();
        
        int N = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        for (int i = 0; i < N; i++) {
            String command = scanner.nextLine();
            String[] parts = command.split(" ");
            
            if (parts[0].equals("CREATE")) {
                String type = parts[1];
                String name = parts[2];
                
                if (students.containsKey(name)) {
                    System.out.println("Akun sudah terdaftar");
                } else {
                    Student student;
                    if (type.equals("REGULER")) {
                        student = new Reguler(name);
                    } else { // BEASISWA
                        student = new Beasiswa(name);
                    }
                    students.put(name, student);
                    System.out.println(type + " " + name + " berhasil dibuat");
                }
                
            } else if (parts[0].equals("SAVE")) {
                String name = parts[1];
                int amount = Integer.parseInt(parts[2]);
                
                if (!students.containsKey(name)) {
                    System.out.println("Akun tidak ditemukan");
                } else {
                    Student student = students.get(name);
                    student.save(amount);
                    System.out.println("Saldo " + name + ": " + student.getBalance());
                }
                
            } else if (parts[0].equals("TAKE")) {
                String name = parts[1];
                int amount = Integer.parseInt(parts[2]);
                
                if (!students.containsKey(name)) {
                    System.out.println("Akun tidak ditemukan");
                } else {
                    Student student = students.get(name);
                    if (!student.take(amount)) {
                        System.out.println("Saldo " + name + " tidak cukup");
                    } else {
                        System.out.println("Saldo " + name + ": " + student.getBalance());
                    }
                }
                
            } else if (parts[0].equals("CHECK")) {
                String name = parts[1];
                Student student = students.get(name);
                System.out.println(name + " | " + student.getType() + " | saldo: " + student.getBalance());
            }
        }
        
        scanner.close();
    }
}