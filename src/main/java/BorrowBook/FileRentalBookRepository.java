package BorrowBook;

import User.Users;
import org.example.Book;
import org.example.FileBookRepository;
import org.example.Status;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//ToDo : : ID-ksiązki|title|author|borrower-id|borrower-name|borrower-last name|rental-date|rental-in-days

public class FileRentalBookRepository implements RentalBookRepository{

    private final String FILE_NAME_TO_BOOK_REPOSITORY = "src/main/resources/BookRepository.txt";

    private final String FILE_NAME_TO_RENTAL_REPOSITORY = "src/main/resources/Rental.txt";

    private FileBookRepository fileBookRepository;

    @Override
    public void possibilityOfRental() {
        fileBookRepository = new FileBookRepository(FILE_NAME_TO_BOOK_REPOSITORY);
        try(Scanner scanner = new Scanner(new File(FILE_NAME_TO_BOOK_REPOSITORY))){
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                if(checkAvailable(line)){
                  for(Book books: fileBookRepository.convertToBook(line))
                      System.out.println(books);;
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("File, not exist");
        }
    }

    @Override
    public void viewsBorrowedBooks() {
        List<RentalInfo> viewRentalBooks = new ArrayList<>();

        viewRentalBooks.addAll(convertRentalList());

        for(RentalInfo rentalInfo: viewRentalBooks) {
            System.out.println(rentalInfo);
            System.out.println();
        }

    }

    @Override
    public void saveBorrowedBooks() {
        BookSelection bookSelection = new BookSelection();
        UserSelection userSelection = new UserSelection();
        RentalInfo rentalInfo = new RentalInfo(userSelection.createUserForBorrowedBook(),bookSelection.bookToBorrow());

        if (rentalInfo.getUsers().getIdForRental().equals("error")
                || rentalInfo.getUsers().getFirstName().equals("error")
                || rentalInfo.getUsers().getLastName().equals("error")
                || rentalInfo.getBook().getIsbn().equals("error"))  {
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
    public void deleteReturnedBooks() {

    }
    private boolean checkAvailable (String bookAvailableInformation) {
        String[] availableAboutBook = bookAvailableInformation.split(",");
        if(availableAboutBook.length == 4) {
            String availability = availableAboutBook[3].trim();
            if(availability.equals("AVAILABLE")){
                return true;
            }
        }
        return false;
    }

    private List<RentalInfo> convertToRentalInformation(String informationFromTheFile) {
        fileBookRepository = new FileBookRepository(FILE_NAME_TO_BOOK_REPOSITORY);
        List<RentalInfo> rentalInfo = new ArrayList<>();
        String[] convertInformationFromTheFile = informationFromTheFile.split(",");

        if(convertInformationFromTheFile.length == 8){
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

            rentalInfo.add(new RentalInfo(new Users(id,userName,userLastName),
                    (new Book(isbn,author,title,status)),
                    date));

        }
        return rentalInfo;
    }

    private List<RentalInfo> convertRentalList() {
        List<RentalInfo> borrowedBooks = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(FILE_NAME_TO_RENTAL_REPOSITORY))){
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                List<RentalInfo> rentalInfoList = convertToRentalInformation(line);
                borrowedBooks.addAll(rentalInfoList);
            }
        }catch (FileNotFoundException e){
            System.out.println("File not exist");
        }
        return borrowedBooks;
    }

    }





