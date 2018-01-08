package HW1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Nuke2 {
    public static void main(String[] args) throws Exception{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter the name of a company (without spaces): ");
        System.out.flush();
        String target = input.readLine();
        System.out.println(target.charAt(0) + target.substring(2, target.length()));
    }
}
