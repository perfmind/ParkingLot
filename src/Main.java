import exceptions.InvalidParkingCommandException;
import services.ParkingService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("===================================================================");
        System.out.println("=================      APLIKASI PARKING LOT     ===================");
        System.out.println("===================================================================");
        printUsage();
        System.out.println("Please Enter 'exit' to end Execution");
        System.out.println("Input:");
        try {
            runApp();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printUsage() {
        StringBuffer menu = new StringBuffer();
        menu.append("\nSilakan pilih menu di bawah ini:\n");
        menu.append("1. Buat Parking Lot\n");
        menu.append("2. Parkir Kendaraan\n");
        menu.append("3. Keluar Parkir\n");
        menu.append("4. Tampilkan Status\n");
//        menu.append("5. Tampilkan Kendaraan berdasarkan warna\n");
//        menu.append("6. Tampilkan slot parkir berdasarkan warna kendaraan\n");
//        menu.append("7. Tampilkan slot parkir berdasarkan nomor kendaraan\n");
        System.out.println(menu);
    }

    private static void runApp() throws InvalidParkingCommandException {
        BufferedReader bufferReader = null;
        ParkingService parkingService = new ParkingService();
        String input = null;
        while (true) {
            try {
                bufferReader = new BufferedReader(new InputStreamReader(System.in));
                input = bufferReader.readLine().trim();
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Terima kasih telah menggunakan aplikasi ini");
                    System.exit(0);
                } else {
                    parkingService.runCommand(input);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                printUsage();
            }
        }
    }
}