package main

import (
	"encoding/json"
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
func handlePut(w http.ResponseWriter, r *http.Request) (err error) {
	len := r.ContentLength
	body := make([]byte, len)
	r.Body.Read(body)
	book := Book{}
	json.Unmarshal(body, &book)
	books = append(books, book)
	w.WriteHeader(200)
	return
}

func handlePost(w http.ResponseWriter, r *http.Request) (err error) {
	w.WriteHeader(200)
	return
}

func handleDelete(w http.ResponseWriter, r *http.Request) (err error) {
	w.WriteHeader(200)
	return
}
