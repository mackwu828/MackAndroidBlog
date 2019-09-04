// BookManager.aidl
package com.mackwu.service;
import com.mackwu.service.ipc.Book;

// Declare any non-default types here with import statements

interface BookManager {

    List<Book> getBooks();

    void addBook(in Book book);
}
