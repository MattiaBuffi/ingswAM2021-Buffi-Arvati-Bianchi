package it.polimi.ingsw;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class IpTest {


    public static void main( String[] args ){

        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.print("ip:");
        input = scanner.nextLine().toLowerCase();


        try {

            Socket socket = new Socket(input,1337);
            ObjectOutputStream out  = new ObjectOutputStream(socket.getOutputStream());
            
            input = "Hello Server";
            
            do{
                out.reset();
                out.writeObject(input);
                out.flush();
                System.out.print("Write something: ");
            }while (!(input = scanner.nextLine().toLowerCase()).equals("q"));
            


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("digit anything to close");




    }






}
