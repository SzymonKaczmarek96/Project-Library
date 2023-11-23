package BorrowBook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class UpgradeBookStatus {

    private final String BOOK_FILE = "src/main/resources/BookRepository.txt";


    public void replaceBookStatus(String isbn) {

        try {
            List<String> lines = Files.readAllLines(Path.of(BOOK_FILE));

            for(int i=0; i < lines.size(); i++){

                String line = lines.get(i);
                String[] parts = line.split(",");

                if(parts.length >= 4) {
                    if(parts[0].equals(isbn)) {
                        if (parts[3].equals("AVAILABLE")) {
                            parts[3] = "BORROWED";
                            lines.set(i, String.join(",", parts));
                        } else if (parts[3].equals("BORROWED")) {
                            parts[3] = "AVAILABLE";
                            lines.set(i, String.join(",", parts));
                        }
                    }
                }
            }
            Files.write(Path.of(BOOK_FILE), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
