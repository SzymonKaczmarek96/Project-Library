package Repositories;

import Model.Book;
import Model.Status;
import Model.User;
import Services.BookSelection;
import Services.RentalInfo;
import Services.UpdateBookStatus;
import Services.UserAuthentication;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class FileRentalBookRepository implements RentalBookRepository {

    private final String FILE_NAME_TO_RENTAL_REPOSITORY = "src/main/resources/Rental.txt";

    private FileBookRepository fileBookRepository;

    private UpdateBookStatus updateBookStatus;


    @Override
    public void viewsBorrowedBooks() {
        List<RentalInfo> viewRentalBooks = new ArrayList<>();
        viewRentalBooks.addAll(convertRentalList());
        for (RentalInfo rentalInfo : viewRentalBooks) {
            System.out.println(rentalInfo);
            System.out.println();
        }

    }

    @Override
    public void saveBorrowedBooks() {
        BookSelection bookSelection = new BookSelection();
        UserAuthentication userAuthentication = new UserAuthentication();
        RentalInfo rentalInfo = new RentalInfo(userAuthentication.getLoggedUser(), bookSelection.attemptBookBorrowing());

        if (rentalInfo.getUsers().getIdForRental().equals("error")
                || rentalInfo.getUsers().getFirstName().equals("error")
                || rentalInfo.getUsers().getLastName().equals("error")
                || rentalInfo.getBook().getIsbn().equals("error")) {
            System.out.println("Something went wrong");
        } else {

            try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME_TO_RENTAL_REPOSITORY, true))) {
                String saveRentalInformation = rentalInfo.convertRentalInfoToSave();
                pw.println(saveRentalInformation);

            } catch (IOException e) {
                System.out.println("File not exist");
            }
        }
    }

    @Override
    public void removeBookFromRentalRepository(String ISBN) {
        updateBookStatus = new UpdateBookStatus();
        try {
            Files.write(
                    Paths.get(FILE_NAME_TO_RENTAL_REPOSITORY),
                    Files.lines(Paths.get(FILE_NAME_TO_RENTAL_REPOSITORY))
                            .filter(line -> !line.contains(ISBN))
                            .collect(Collectors.toList()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        updateBookStatus.replaceBookStatus(ISBN);
    }


    private List<RentalInfo> convertToRentalInformation(String informationFromTheFile) {
        fileBookRepository = new FileBookRepository();
        List<RentalInfo> rentalInfo = new ArrayList<>();
        String[] convertInformationFromTheFile = informationFromTheFile.split(",");

        if (convertInformationFromTheFile.length == 8) {
            String isbn = convertInformationFromTheFile[0].trim();
            String author = convertInformationFromTheFile[1].trim();
            String title = convertInformationFromTheFile[2].trim();
            String strStatus = convertInformationFromTheFile[3].trim();
            String id = convertInformationFromTheFile[4].trim();
            String userName = convertInformationFromTheFile[5].trim();
            String userLastName = convertInformationFromTheFile[6].trim();
            String rentalDate = convertInformationFromTheFile[7].trim();


            LocalDate date = LocalDate.parse(rentalDate);

            Status status = fileBookRepository.checkStatus(strStatus);

            rentalInfo.add(new RentalInfo(new User(id, userName, userLastName),
                    (new Book(isbn, author, title, status)),
                    date));

        }
        return rentalInfo;
    }

    private List<RentalInfo> convertRentalList() {
        List<RentalInfo> borrowedBooks = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_NAME_TO_RENTAL_REPOSITORY))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                List<RentalInfo> rentalInfoList = convertToRentalInformation(line);
                borrowedBooks.addAll(rentalInfoList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not exist");
        }
        return borrowedBooks;
    }


//    public List<String> loadRentalDataFromFile() {
//        List<String> rentalList = new ArrayList<>();
//        try (Scanner scanner = new Scanner(new File(FILE_NAME_TO_RENTAL_REPOSITORY))) {
//            while (scanner.hasNext()) {
//                String line = scanner.nextLine();
//                rentalList.addAll(parseRentalInfoLine(line));
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("File, not exist");
//        }
//
//        return rentalList;
//
//    }
//
//    public List<String> parseRentalInfoLine(String rentalInfo) {
//        List<String> isbnList = new ArrayList<>();
//
//        String[] arr = rentalInfo.split(",");
//        if (rentalInfo.length() != 4) {
//            String rentalISBN = arr[0].trim();
//            isbnList.add(rentalISBN);
//        }
//        return isbnList;
//    }

}





