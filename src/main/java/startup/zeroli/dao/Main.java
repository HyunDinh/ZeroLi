package startup.zeroli.dao;

import startup.zeroli.model.Bookstore;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookDAO bookDAO=new BookDAO();
        List<Bookstore> bookstores = bookDAO.readBookstoresFromFile();
        Printer printer = new Printer();
        printer.printAllBookstores(bookstores);
    }
}

