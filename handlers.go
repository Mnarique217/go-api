package main

import (
	"encoding/json"
	"log"
	"net/http"
)

func find(x string) int {
	for i, book := range books {
		if x == book.Id {
			return i
		}
	}
	return -1
}

func handleGet(w http.ResponseWriter, r *http.Request) (err error) {

	param, ok := r.URL.Query()["id"]

	if !ok || len(param[0]) < 1 {
		dataJson, err := json.Marshal(books)
		checkError("Parse error", err)
		w.Header().Set("Content-Type", "application/json")
		w.Write(dataJson)
	} else {
		id := param[0]
		i := find(id)
		if i == -1 {
			return
		}
		dataJson, err := json.Marshal(books[i])
		checkError("Parse error", err)
		w.Header().Set("Content-Type", "application/json")
		w.Write(dataJson)
	}

	return
}
func handlePut(res http.ResponseWriter, req *http.Request) (err error) {
	len := req.ContentLength
	body := make([]byte, len)
	req.Body.Read(body)
	book := Book{}
	json.Unmarshal(body, &book)
	books = append(books, book)
	res.WriteHeader(200)
	return
}

func handlePost(res http.ResponseWriter, req *http.Request) (err error) {

	param, ok := req.URL.Query()["id"]
	if !ok || len(param[0]) < 1 {
		return
	}

	id := param[0]
	i := find(id)
	bookRef := &books[i]
	log.Println(bookRef)

	len := req.ContentLength
	body := make([]byte, len)
	req.Body.Read(body)
	book := Book{}
	json.Unmarshal(body, &book)

	bookRef.Title = isValidUpdate(bookRef.Title, book.Title)
	bookRef.Edition = isValidUpdate(bookRef.Edition, book.Edition)
	bookRef.Copyright = isValidUpdate(bookRef.Copyright, book.Copyright)
	bookRef.Language = isValidUpdate(bookRef.Language, book.Language)
	bookRef.Pages = isValidUpdate(bookRef.Pages, book.Pages)
	bookRef.Author = isValidUpdate(bookRef.Author, book.Author)
	bookRef.Publisher = isValidUpdate(bookRef.Publisher, book.Publisher)

	res.WriteHeader(200)
	return
}

func handleDelete(res http.ResponseWriter, req *http.Request) (err error) {

	param, ok := req.URL.Query()["id"]

	if !ok || len(param[0]) < 1 {
		return
	}

	id := param[0]
	i := find(id)
	books = append(books[:i], books[i+1:]...)
	res.WriteHeader(200)
	return
}

func isValidUpdate(current string, new string) string {
	if new != "" {
		return new
	}

	return current
}
