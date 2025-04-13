import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteBinaryFile
{
    public static void main (String [] args)
    {
        File workDir = new File(System.getProperty("user.dir"));
        Path outFile = Paths.get(workDir.getPath() + "\\src\\data.bin");

        String name = "Devon Mabrey";
        long idNum = 123456789;
        double mpg = 25.5;

        try
        {
            // Create a FileOutputStream to write to the file
            FileOutputStream fos = new FileOutputStream(outFile.toFile());
            DataOutputStream dos = new DataOutputStream(fos);

            // Write the data to the file
            dos.writeUTF(name);
            dos.writeLong(idNum);
            dos.writeDouble(mpg);

            // Close the output stream
            dos.close();
            System.out.println("Data written to file: " + outFile.toString());
        }
        catch (IOException e)
        {
            System.out.println("An error occurred while writing to the file.");
        }
    }
}
