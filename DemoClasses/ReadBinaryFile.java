import java.io.*;

public class ReadBinaryFile
{
    public static void main(String[] args)
    {
        File workDir = new File(System.getProperty("user.dir"));
        File inFile = new File(workDir.getPath() + "\\src\\data.bin");

        String name;
        long idNum;
        double mpg;
        int position = 0;

        try (FileInputStream fis = new FileInputStream(inFile);
             DataInputStream input = new DataInputStream(fis)) {

             name = input.readUTF();
             idNum = input.readLong();
             mpg = input.readDouble();

             fis.close();

            System.out.println("Name: " + name);
            System.out.println("ID Number: " + idNum);
            System.out.println("MPG: " + mpg);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }

        // the "r" in the RandomAccessFile constructor means read-only. "rw" means read/write
        try (RandomAccessFile raf = new  RandomAccessFile(inFile, "r"))
        {
            raf.seek(position);
            byte[] data = new byte[100];
            raf.read(data);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(0);

        }
    }
}
