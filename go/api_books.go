/*
 * Books API
 *
 * This web service offers information on books
 *
 * API version: 0.1.9
 * Generated by: Swagger Codegen (https://github.com/swagger-api/swagger-codegen.git)
 */
package swagger

import (
	"encoding/json"
	"net/http"
	"path"
)

/***************************************************************************/
//                              BOOKS
/***************************************************************************/
var books = []Book{
	Book{BookId: "Book1", Title: "Operating System Concepts", Edition: "9th",
		Copyright: "2012", Language: "ENGLISH", Pages: "976",
		AuthorsIds: []string{"author"}, PublishersIds: []string{"Publisher1"}},
}

func find(x string) int {
	for i, book := range books {
		if x == book.BookId {
			return i
		}
	}
	return -1
}

func BooksBookIdDelete(w http.ResponseWriter, r *http.Request) {
	id := path.Base(r.URL.Path)
	i := find(id)
	if i == -1 {
		return
	}
	books = append(books[:i], books[i+1:]...)
	w.Header().Set("Content-Type", "application/json; charset=UTF-8")
	w.WriteHeader(http.StatusOK)
}

func BooksBookIdGet(w http.ResponseWriter, r *http.Request) {
	id := path.Base(r.URL.Path)
	i := find(id)
	if i == -1 {
		return
	}
	dataJson, _ := json.Marshal(books[i])
	w.Header().Set("Content-Type", "application/json; charset=UTF-8")
	w.Write(dataJson)
	w.WriteHeader(http.StatusOK)
}

func BooksBookIdPut(w http.ResponseWriter, r *http.Request) {

	id := path.Base(r.URL.Path)
	i := find(id)
	if i == -1 {
		return
	}
	bookRef := &books[i]

	len := r.ContentLength
	body := make([]byte, len)
	r.Body.Read(body)
	book := Book{}
	json.Unmarshal(body, &book)

	bookRef.Title = isValidUpdate(bookRef.Title, book.Title)
	bookRef.Edition = isValidUpdate(bookRef.Edition, book.Edition)
	bookRef.Copyright = isValidUpdate(bookRef.Copyright, book.Copyright)
	bookRef.Language = isValidUpdate(bookRef.Language, book.Language)
	bookRef.Pages = isValidUpdate(bookRef.Pages, book.Pages)
	bookRef.Author = isValidUpdate(bookRef.Author, book.Author)
	bookRef.Publisher = isValidUpdate(bookRef.Publisher, book.Publisher)
	bookRef.PublishersIds = book.PublishersIds
	bookRef.AuthorsIds = book.AuthorsIds

	w.Header().Set("Content-Type", "application/json; charset=UTF-8")
	w.WriteHeader(http.StatusOK)
}

func BooksPost(w http.ResponseWriter, r *http.Request) {
	var book Book
	err := json.NewDecoder(r.Body).Decode(&book)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	books = append(books, book)
	w.Header().Set("Content-Type", "application/json; charset=UTF-8")
	w.WriteHeader(http.StatusOK)
}

func BooksBookIdAuthorsGet(w http.ResponseWriter, r *http.Request) {
	dir := path.Dir(r.URL.Path)
	id := path.Base(dir)
	i := find(id)
	if i == -1 {
		return
	}
	book := &books[i]

	var response []Author

	for _, bkId := range book.AuthorsIds {
		j := findAuthor(bkId)
		if j == -1 {
			return
		}
		response = append(response, authors[j])
	}

	dataJson, _ := json.Marshal(books)

	w.Header().Set("Content-Type", "application/json; charset=UTF-8")
	w.Write(dataJson)
	w.WriteHeader(http.StatusOK)
}

func BooksBookIdPublishersGet(w http.ResponseWriter, r *http.Request) {
	dir := path.Dir(r.URL.Path)
	id := path.Base(dir)
	i := find(id)
	if i == -1 {
		return
	}
	book := &books[i]

	var response []Publisher

	for _, bkId := range book.PublishersIds {
		j := findPublisher(bkId)
		if j == -1 {
			return
		}
		response = append(response, publishers[j])
	}

	dataJson, _ := json.Marshal(books)

	w.Header().Set("Content-Type", "application/json; charset=UTF-8")
	w.Write(dataJson)
	w.WriteHeader(http.StatusOK)
}
