import java.util.ArrayList;
import java.util.Scanner;
class Room {
    int roomNumber;
    String category;
    boolean isAvailable;
    double price;
    Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
        this.price = price;
    }
}
class Reservation {
    String guestName;
    Room room;
    boolean isPaid;
    Reservation(String guestName, Room room) {
        this.guestName = guestName;
        this.room = room;
        this.isPaid = false;
        room.isAvailable = false;
    }
    void processPayment() {
        this.isPaid = true;
        System.out.println("Payment successful for " + guestName);
    }
}
class HotelReservationSystem {
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initializing rooms
        rooms.add(new Room(101, "Single", 1000));
        rooms.add(new Room(102, "Double", 1500));
        rooms.add(new Room(103, "Suite", 2500));

        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Process Payment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    for (Room room : rooms) {
                        if (room.isAvailable) {
                            System.out.println("Room " + room.roomNumber + " - " + room.category + " ($" + room.price + ")");
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter guest name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter room number to book: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine();
                    for (Room room : rooms) {
                        if (room.roomNumber == roomNumber && room.isAvailable) {
                            Reservation reservation = new Reservation(name, room);
                            reservations.add(reservation);
                            System.out.println("Room booked successfully.");
                            break;
                        }
                    }
                    break;
                case 3:
                    for (Reservation res : reservations) {
                        System.out.println(res.guestName + " - Room " + res.room.roomNumber + " - Paid: " + res.isPaid);
                    }
                    break;
                case 4:
                    System.out.print("Enter guest name for payment: ");
                    String guestName = scanner.nextLine();
                    for (Reservation res : reservations) {
                        if (res.guestName.equalsIgnoreCase(guestName) && !res.isPaid) {
                            res.processPayment();
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}