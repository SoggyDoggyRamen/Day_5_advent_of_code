import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File f = null;
        BigInteger one = new BigInteger("1");
        boolean segment = false;
        Code code = new Code();
        BigInteger minNum = new BigInteger("0");
        BigInteger min = new BigInteger("100000000000000000000000000000000000000000000000000000000000000000");
        String str = "4106085912 135215567 529248892 159537194 1281459911 114322341 1857095529 814584370 2999858074 50388481 3362084117 37744902 3471634344 240133599 3737494864 346615684 1585884643 142273098 917169654 286257440 ";
        while(!str.isEmpty()) {
            BigInteger start = new BigInteger(str.substring(0, str.indexOf(" ")));
            str = str.substring(str.indexOf(" ") + 1);
            BigInteger seedRange = new BigInteger(str.substring(0, str.indexOf(" ")));
            str = str.substring(str.indexOf(" ") + 1);
            while (start.compareTo(start.add(seedRange)) == -1 || start.compareTo(start.add(seedRange)) != 0) {
                System.out.println(start);
                BigInteger seed = start;
                BigInteger item = start;
                BigInteger savedItem = start;
                try {
                    f = new File("inputFile");
                    Scanner s = new Scanner(f);
                    while (s.hasNextLine()) {
                        String sc;
                        String d;
                        String r;
                        String currentLine = s.nextLine();
                        if (currentLine.equals("change") && item == savedItem) {
                            item = item;
                            segment = false;
                        }
                        else if (currentLine.equals("change")) {
                            savedItem = item;
                            segment = false;
                        }
                        else if (!segment){
                            //Finding the Destination and range
                            d = currentLine.substring(0, currentLine.indexOf(" "));
                            BigInteger destination = new BigInteger(d);
                            sc = currentLine.substring(currentLine.indexOf(" ") + 1);
                            sc = sc.substring(0, sc.indexOf(" "));
                            BigInteger source = new BigInteger(sc);
                            r = currentLine.substring(currentLine.indexOf(" ") + 1);
                            r = r.substring(r.indexOf(" ") + 1);
                            BigInteger range = new BigInteger(r);


                            //Calculating the stuff
                            if (item.compareTo(source) == 1 && item.compareTo(source.add(range)) == -1) {
                                item = item.subtract(source);
                                item = item.add(destination);
                                segment = true;
                            }
                            else if (item.compareTo(source) == 0 && item.compareTo(source.add(range)) == -1) {
                                item = destination;
                                segment = true;
                            }
                        }
                    }
                    if (item.compareTo(min) == -1) {
                        min = item;
                        minNum = seed;
                    }
                    start = start.add(one);
                }
                catch (FileNotFoundException e) {
                    System.out.println("File not found");
                }
            }
        }
        System.out.println("SEED VALUE: " + minNum);
        System.out.print("LOCATION VALUE: " + min);
    }
}
