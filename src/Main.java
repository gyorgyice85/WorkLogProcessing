import java.io.*;
import java.io.IOException;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Main class for splitting the contents (lines) of a table into several files
 * based on the entered dates
 */
public class Main {


    public static void main(String[] args) throws IOException {

        //String path = "/Users/gyorgyi/Programozas/szabitol/feladat2/Fajldarabolas/input.csv";
        String path = args[0];
        File file = new File(path);

        FileWriter myWriter;
        File myObj;


        Main.makeDirectory();

        if (!file.exists()) {
            System.out.println("The file is not exist.");
        } else {
            try {
                Scanner sc = new Scanner(file);

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    Date date = Main.getDateFromLine(line);
                    String inhalt = line + "\n";
                    if(date != null) {
                        Main.writeToFile(date, inhalt);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * The method deletes the existing "Table" directory with the files or creates
     * a new "Table" directory for the files to be written
     *
     * @throws IOException
     */
    public static void makeDirectory() throws IOException {

        String dirName = "Table";
        File dir = new File(dirName);

        if (dir.exists()) {
            Main.deleteDirectory(dir);
        } else {
            dir.mkdir();
        }
    }

    /**
     * The method deletes the Table subfolder and the files in it
     * @param file the Table directory
     */
    public static void deleteDirectory(File file) {
        for (File subfile: file.listFiles()) {

            if (subfile.isDirectory()) {
                deleteDirectory(subfile);
            }


            subfile.delete(); // delete files and empty subfolders
        }
    }
    /**
     * The method searches for the date in the line
     * @param input The content of a table
     * @return Date - Date is the found date
     *
     * @throws ParseException
     */
    public static Date getDateFromLine(String input){
        String patternDate =".*(\\d{4}-\\d{2}-\\d{2}).*";
        Pattern pattern = Pattern.compile(patternDate);
        Matcher matcher = pattern.matcher(input);

        if(matcher.find()) {
            try {
                String d = matcher.group(1);
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(d);

                return date;

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * The method writes the passed content to a file
     *
     * @param date the date read from the line
     * @param content the content to be written to the file
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void writeToFile(Date date, String content) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                String filename = "Table/" + formatter.format(date);
                String header = "Key, UntaggedTag2, Date Started, UntaggedTag4, Time Spent, UntaggedTag6, Work Description";

                File myObj = new File(filename);
                if (myObj.createNewFile()) {

                    try {
                        FileWriter myWriter = new FileWriter(filename);
                        myWriter.write(header);
                        myWriter.write("\n");
                        myWriter.write(content);
                        myWriter.close();
                        System.out.println("File created: " + myObj.getName());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                } else {
                    Main.appendToFile(filename, content);
                }

            } catch (IOException e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }

    }
    /**
     * The method writes the passed line to the contents of the file
     *
     * @param filename the name of the written File
     * @param content  the line that is written to the contents of the file
     *
     * @throws IOException
     */
    public static void appendToFile(String filename,String content) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename,true));
            out.write(content);
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }


}