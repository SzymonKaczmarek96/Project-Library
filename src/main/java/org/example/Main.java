package org.example;

// ToDo: metoda dodawania i odczytywania z pliku, Menu programu

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBooksToReposiroty(new Book("1","J.R.R Tolkien","Lord of the rings", Status.AVAILABLE));
    }
}