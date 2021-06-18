package it.polimi.ingsw;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IpTest {

    static List<Integer> list;

    public static void remove(List<Integer> list){
        list.removeAll(list.subList(0, 2));
        //printList(list);
    }

    public static void printList(List<Integer> list){
        for (Integer i: list){
            System.out.print(i+",");
        }

        System.out.println("\n\n");
    }

    public static void main( String[] args ){
    /*
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.print("ip:");
        input = scanner.nextLine().toLowerCase();


        try {

            Socket socket = new Socket(input,1337);
            ObjectOutputStream out  = new ObjectOutputStream(socket.getOutputStream());



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
*/
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        printList(list);
        remove(list);
        printList(list);



    }






}
