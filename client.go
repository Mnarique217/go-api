package main

import (
	"context"
	"encoding/csv"
	"fmt"
	"log"
	"os"
	"time"

	"github.com/Mnarique217/go-api/booksapp"
	pb "github.com/Mnarique217/go-api/booksapp"
	"google.golang.org/grpc"
)

var books []Book
var bookIDs []string

func main() {
	/* Create conexion*/
	address := "localhost:8080" //os.Getenv("ADDRESS")
	conn, err := grpc.Dial(address, grpc.WithInsecure())
	if err != nil {
		log.Fatalf("did not connect: %v", err)
	}
	defer conn.Close()
	conexion := pb.NewBookInfoClient(conn)
	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()

	/* load CSV*/
	actionPerformed("load CSV")
	readData("books.csv")
	showLoadedBookdFromCSV()
	/*Add books from csv*/
	addBooks(conexion, ctx)
	/*get all books*/
	getAll(conexion, ctx)
	/*Update a book*/
	updateBook(conexion, ctx)
	/*Delete a book*/
	deleteBook(conexion, ctx, bookIDs[0])
	/*get all books*/
	actionPerformed("Read all books")
	for i := 1; i < len(bookIDs); i++ {
		book1, err := conexion.GetBook(ctx, &pb.BookID{Value: bookIDs[i]})
		if err != nil {
			log.Fatalf("Could not delete the book: %v", err)
		}
		fmt.Println(book1.String())
	}

}

func showLoadedBookdFromCSV() {
	for _, book := range books {
		fmt.Println(book)
	}
}
func addBooks(c booksapp.BookInfoClient, ctx context.Context) {
	actionPerformed("Add books from csv")

	for _, book := range books {

		r, err := c.AddBook(ctx, &pb.Book{
			Id:        book.Id,
			Title:     book.Title,
			Edition:   book.Edition,
			Copyright: book.Copyright,
			Language:  book.Language,
			Pages:     book.Pages,
			Author:    book.Author,
			Publisher: book.Publisher})
		if err != nil {
			log.Fatalf("Could not add book: %v", err)
		}
		bookIDs = append(bookIDs, r.Value)
		fmt.Println(r.Value)
	}
}

func updateBook(c booksapp.BookInfoClient, ctx context.Context) {
	bookid := bookIDs[0]
	/*UPDATE*/
	actionPerformed("UPDATE BOOK")
	fmt.Println("original:")
	getBook(c, ctx, bookid)
	bookData := &pb.Book{
		Id:        "1",
		Title:     "UPDATED TITLE",
		Edition:   "10th",
		Copyright: "2052",
		Language:  "ESPANOL",
		Pages:     "800",
		Author:    "MANRIQUE",
		Publisher: "MANRIQUE"}
	idToUpdate := &pb.BookID{Value: bookid}
	bkUpdate := &pb.BookUpdate{Update: bookData, Id: idToUpdate}

	_, err := c.UpdateBook(ctx, bkUpdate)
	if err != nil {
		log.Fatalf("Could not get book: %v", err)
	}
	fmt.Println("updated:")
	getBook(c, ctx, bookid)

}

func deleteBook(c booksapp.BookInfoClient, ctx context.Context, bookid string) {
	/*DELETE*/
	actionPerformed("DELETE BOOK")
	result, err := c.DeleteBook(ctx, &pb.BookID{Value: bookid})
	if err != nil {
		log.Fatalf("Could not delete the book: %v", err)
	}
	fmt.Println("Boock to delete")
	fmt.Println(bookid)
	fmt.Println(result.String())
}

func getBook(c booksapp.BookInfoClient, ctx context.Context, id string) {

	book1, err := c.GetBook(ctx, &pb.BookID{Value: id})
	if err != nil {
		log.Fatalf("Could not get book: %v", err)
	}
	fmt.Println(book1.String())
}
func getAll(c booksapp.BookInfoClient, ctx context.Context) {
	actionPerformed("Read all books")
	for _, id := range bookIDs {
		book1, err := c.GetBook(ctx, &pb.BookID{Value: id})
		if err != nil {
			log.Fatalf("Could not delete the book: %v", err)
		}
		fmt.Println(book1.String())
	}
}

func actionPerformed(title string) {
	fmt.Println("")
	fmt.Println("=======================================")
	fmt.Println("               ", title, "             ")
	fmt.Println("=======================================")
}

type Book struct {
	Id        string `json:"id"`
	Title     string `json:"title"`
	Edition   string `json:"edition"`
	Copyright string `json:"copyright"`
	Language  string `json:"language"`
	Pages     string `json:"pages"`
	Author    string `json:"author"`
	Publisher string `json:"publisher"`
}

func checkError(message string, err error) {
	if err != nil {
		log.Fatal(message, err)
	}
}

func readData(filePath string) {
	file, err1 := os.Open(filePath)
	checkError("Unable to read input file "+filePath, err1)
	defer file.Close()

	csvReader := csv.NewReader(file)
	records, err2 := csvReader.ReadAll()
	checkError("Unable to parse file as CSV for "+filePath, err2)
	defer file.Close()

	books = []Book{}

	for _, record := range records {
		book := Book{
			Id:        record[0],
			Title:     record[1],
			Edition:   record[2],
			Copyright: record[3],
			Language:  record[4],
			Pages:     record[5],
			Author:    record[6],
			Publisher: record[7]}
		books = append(books, book)
	}
	file.Close()
}
