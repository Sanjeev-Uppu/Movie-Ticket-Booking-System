import java.util.*;

// Seat Class
class Seat {
    private int row;
    private int seatNumber;
    private boolean isBooked;

    public Seat(int row, int seatNumber) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.isBooked = false;
    }

    public int getRow() {
        return row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookSeat() {
        isBooked = true;
    }
}

// Movie Class
class Movie {
    private String title;
    private List<Seat> seats;

    public Movie(String title, int rows, int seatsPerRow) {
        this.title = title;
        seats = new ArrayList<>();
        initializeSeats(rows, seatsPerRow);
    }

    private void initializeSeats(int rows, int seatsPerRow) {
        for (int row = 1; row <= rows; row++) {
            for (int seatNumber = 1; seatNumber <= seatsPerRow; seatNumber++) {
                seats.add(new Seat(row, seatNumber));
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Seat findAvailableSeat(int row, int seatNumber) {
        for (Seat seat : seats) {
            if (seat.getRow() == row && seat.getSeatNumber() == seatNumber && !seat.isBooked()) {
                return seat;
            }
        }
        return null;
    }
}

// Booking System
public class MovieTicketBookingSystem {
    private List<Movie> movies;

    public MovieTicketBookingSystem() {
        movies = new ArrayList<>();
        initializeMovies();
    }

    private void initializeMovies() {
        movies.add(new Movie("Avatar: The Way of Water", 5, 10));
        movies.add(new Movie("Spider-Man: No Way Home", 5, 10));
        movies.add(new Movie("The Batman", 5, 10));
    }

    public void displayMovies() {
        System.out.println("Available Movies:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i).getTitle());
        }
    }

    public void displaySeats(Movie movie) {
        System.out.println("\nSeat Layout for " + movie.getTitle() + ":");
        for (int row = 1; row <= 5; row++) {
            for (int seatNumber = 1; seatNumber <= 10; seatNumber++) {
                Seat seat = movie.findAvailableSeat(row, seatNumber);
                if (seat == null || seat.isBooked()) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" O ");
                }
            }
            System.out.println();
        }
    }

    public void bookTicket() {
        Scanner scanner = new Scanner(System.in);
        displayMovies();

        System.out.println("\nSelect a movie by entering the corresponding number:");
        int movieChoice = scanner.nextInt();
        if (movieChoice < 1 || movieChoice > movies.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Movie selectedMovie = movies.get(movieChoice - 1);
        displaySeats(selectedMovie);

        System.out.println("\nEnter Row Number:");
        int row = scanner.nextInt();
        System.out.println("Enter Seat Number:");
        int seatNumber = scanner.nextInt();

        Seat seat = selectedMovie.findAvailableSeat(row, seatNumber);
        if (seat != null) {
            seat.bookSeat();
            System.out.println("Ticket booked successfully for " + selectedMovie.getTitle() + ", Row: " + row + ", Seat: " + seatNumber);
        } else {
            System.out.println("Selected seat is already booked or invalid.");
        }
    }

    public static void main(String[] args) {
        MovieTicketBookingSystem system = new MovieTicketBookingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Movie Ticket Booking System ---");
            System.out.println("1. Display Movies");
            System.out.println("2. Book Ticket");
            System.out.println("3. Exit");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    system.displayMovies();
                    break;
                case 2:
                    system.bookTicket();
                    break;
                case 3:
                    System.out.println("Thank you for using the Movie Ticket Booking System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}