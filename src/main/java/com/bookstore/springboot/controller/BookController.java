package com.bookstore.springboot.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.springboot.model.Book;
import com.bookstore.springboot.model.repository.BookRepository;

@RestController
//@CrossOrigin(origins = "https://bookstore-angular-app.herokuapp.com/")
@CrossOrigin(origins = "*")
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	private byte[] bytes;
	
	@GetMapping("/get")
	@CrossOrigin(origins = "*")
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}
	
	@PostMapping("/upload")
	@CrossOrigin(origins = "*")
	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		this.bytes = file.getBytes();
	}

	@PostMapping("/add")
	@CrossOrigin(origins = "*")
	public void createBook(@RequestBody Book book) throws IOException {
		book.setPicByte(this.bytes);
		bookRepository.save(book);
		this.bytes = null;
	}
	
	
	@DeleteMapping(path = { "/delete/{id}" })
	@CrossOrigin(origins = "*")
	public Book deleteBook(@PathVariable("id") long id) {
		Book book = bookRepository.getById(id);
		bookRepository.deleteById(id);
		return book;
	}
	
	@PutMapping("/update")
	public void updateBook(@RequestBody Book book) {
		bookRepository.save(book);
	}
	
}


