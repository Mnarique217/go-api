package main

import (
	"net/http"
	"os"

	"github.com/go-kit/kit/log"
	httptransport "github.com/go-kit/kit/transport/http"
	"github.com/gorilla/mux"
)

func main() {
	logger := log.NewLogfmtLogger(os.Stderr)

	r := mux.NewRouter()
	/* =============BOOKS================= */
	var svc BookService
	svc = NewService(logger)

	CreateBookHandler := httptransport.NewServer(
		makeCreateBookEndpoint(svc),
		decodeCreateBookRequest,
		encodeResponse,
	)
	GetByBookIdHandler := httptransport.NewServer(
		makeGetBookByIdEndpoint(svc),
		decodeGetBookByIdRequest,
		encodeResponse,
	)
	DeleteBookHandler := httptransport.NewServer(
		makeDeleteBookEndpoint(svc),
		decodeDeleteBookRequest,
		encodeResponse,
	)
	UpdateBookHandler := httptransport.NewServer(
		makeUpdateBookendpoint(svc),
		decodeUpdateBookRequest,
		encodeResponse,
	)

	http.Handle("/book", CreateBookHandler)
	http.Handle("/book/update", UpdateBookHandler)
	r.Handle("/book/{bookid}", GetByBookIdHandler).Methods("GET")
	r.Handle("/book/{bookid}", DeleteBookHandler).Methods("DELETE")

	/* =============Authors================= */
	var svcAuthor AuthorService
	svcAuthor = NewServiceAuthor(logger)

	CreateAuthorHandler := httptransport.NewServer(
		makeCreateAuthorEndpoint(svcAuthor),
		decodeCreateAuthorRequest,
		encodeResponse,
	)

	GetByAuthorIdHandler := httptransport.NewServer(
		makeGetAuthorByIdEndpoint(svcAuthor),
		decodeGetAuthorByIdRequest,
		encodeResponse,
	)
	DeleteAuthorHandler := httptransport.NewServer(
		makeDeleteAuthorEndpoint(svcAuthor),
		decodeDeleteAuthorRequest,
		encodeResponse,
	)
	UpdateAuthorHandler := httptransport.NewServer(
		makeUpdateAuthorendpoint(svcAuthor),
		decodeUpdateAuthorRequest,
		encodeResponse,
	)

	http.Handle("/author", CreateAuthorHandler)
	http.Handle("/author/update", UpdateAuthorHandler)
	r.Handle("/author/{authorid}", GetByAuthorIdHandler).Methods("GET")
	r.Handle("/author/{authorid}", DeleteAuthorHandler).Methods("DELETE")

	http.Handle("/", r)
	logger.Log("msg", "HTTP", "addr", ":"+os.Getenv("PORT"))
	logger.Log("err", http.ListenAndServe(":8080", nil))
}
