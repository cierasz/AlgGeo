import java.io.*;
import java.util.ArrayList;

public class Solid {

    ArrayList<Face> solid = new ArrayList<>();

    public ArrayList<Face> readSolidFromFile(String filename) {

        String separator = ";";
        String[] parsedLine;
        String line = "";


        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                parsedLine = line.split(separator);
                solid.add(
                        new Face(
                                new Point(Double.valueOf(parsedLine[0]), Double.valueOf(parsedLine[1]), Double.valueOf(parsedLine[2])),
                                new Point(Double.valueOf(parsedLine[3]), Double.valueOf(parsedLine[4]), Double.valueOf(parsedLine[5])),
                                new Point(Double.valueOf(parsedLine[6]), Double.valueOf(parsedLine[7]), Double.valueOf(parsedLine[8]))
                        )
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return solid;
    }
}
