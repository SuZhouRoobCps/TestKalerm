// BookManager.aidl
package com.example.service;
import com.example.service.Book;
// Declare any non-default types here with import statements

interface BookManager {

    Book addBook(in Book book);
}
