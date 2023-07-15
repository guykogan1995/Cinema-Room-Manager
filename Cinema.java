import java.util.Scanner;

public class Cinema {
    static int rows;
    static int cols;
    static int sum = 0;
    static int totalTickets = 0;
    static int totalSeats;
    static String cinemaRoom = "";
    static int oddRows = 0;
    static int ticketPrice = 0;
    static int totalPrice = 0;
    static String cinemaRoomArray[][];
    static Scanner scanner = new Scanner(System.in);

    public Cinema(int row1, int col1){
        this.rows = row1;
        this.cols = col1;
        cinemaRoomArray = new String[rows][cols];
        this.totalSeats = rows * cols;
    }

    public static String printArray(String[][] array) {
        int count = 0;
        int index = 0;
        int count2 = 0;
        cinemaRoom = "Cinema:\n";
        for (int z = 0; z <= cols * 2; z++) {
            if(count == 0 || count % 2 != 0) {
                cinemaRoom += " ";
            } else if(count % 2 == 0) {
                cinemaRoom += count2 + 1;
                count2++;
            }
            if(count2 == cols){
                cinemaRoom += "\n";
                count = 0;
            }
            count++;
        }
        count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= cols * 2; j++) {
                if(j == 0) {
                    count++;
                    cinemaRoom += count;
                }else if (j % 2 == 0) {
                    cinemaRoom += array[i][index];
                    index++;
                } else {
                    cinemaRoom += " ";
                }
                if (index == cols) {
                    index = 0;
                    cinemaRoom += "\n";
                }
            }
        }
        return cinemaRoom;
    }
    public static String checkTotalPrice(){
        if (totalSeats <= 60) {
            totalPrice = totalSeats * 10;
        } else if (totalSeats > 60 && rows % 2 == 0) {
            ticketPrice = 8;
            totalPrice = (totalSeats/2 * 10) + (totalSeats/2 * 8);
        } else {
            totalPrice = ((rows - 1)/2 * cols * 10) + ((rows/2 + 1) * cols * 8);
            oddRows = 1;
        }
        return "Total income: $"+totalPrice +"\n";
    }
    public static void createTheatre(){
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        cols = scanner.nextInt();
        System.out.println();
    }
    public static void ticketPrice() {
        int row;
        int col;
        System.out.println("Enter a row number:");
        row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        col = scanner.nextInt();
        System.out.println();
        while (col > cols || cols < 0 || row > rows || row < 0) {
            System.out.println("\nWrong input!\n");
            System.out.println("Enter a row number:");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            col = scanner.nextInt();
            System.out.println();
        }
        while (cinemaRoomArray[row - 1][col - 1] == "B") {
            System.out.println("That ticket has already been purchased!\n");
            System.out.println("Enter a row number:");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            col = scanner.nextInt();
            System.out.println();
        }
        if(totalSeats <= 60) {
            ticketPrice = 10;
        } else if (totalSeats > 60 && rows % 2 == 0) {
            if(rows/2 >= row) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        } else if(totalSeats > 60 && rows % 2 != 0) {
            if(rows/2 >= row) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
        sum += ticketPrice;
        totalTickets++;
        cinemaRoomArray[row - 1][col - 1] = "B";
        System.out.println("Ticket price: $" + ticketPrice +"\n");
    }

    public static void initialArray(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cinemaRoomArray[i][j] = "S";
            }
        }
    }

    public static void stats(){
        System.out.println("Number of purchased tickets: " + totalTickets);
        double percentage;
        if(totalTickets == 0) {
            percentage = 0.0;
        } else {
            percentage = ((double) (totalTickets) / (double) (rows * cols) * 100);
        }
        System.out.println(String.format("Percentage: %.2f%%",percentage));
        System.out.println("Current income: $" + sum);
        System.out.println(checkTotalPrice());
    }

    public static void main(String[] args) {
        createTheatre();
        new Cinema(rows,cols);
        initialArray();
        int commandLine = 3;
        while(commandLine != 0) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            commandLine = scanner.nextInt();
            System.out.println();
            switch (commandLine) {
                case 1:
                    System.out.println(printArray(cinemaRoomArray));
                    break;
                case 2:
                    ticketPrice();
                    break;
                case 3:
                    stats();
                    break;
                case 0:
                    break;

            }
        }
        scanner.close();
    }
}
