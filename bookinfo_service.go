package main

import (
	"context"
	"log"

	pb "github.com/Mnarique217/go-api/booksapp"
	"github.com/gofrs/uuid"
	"google.golang.org/grpc/codes"
	"google.golang.org/grpc/status"
)

type server struct {
	bookMap map[string]*pb.Book
}

func (s *server) AddBook(ctx context.Context, in *pb.Book) (*pb.BookID, error) {
	out, err := uuid.NewV4()
	if err != nil {
		return nil, status.Errorf(codes.Internal,
			"Error while generating Book ID", err)
	}
	in.Id = out.String()
	if s.bookMap == nil {
		s.bookMap = make(map[string]*pb.Book)
	}
	s.bookMap[in.Id] = in
	return &pb.BookID{Value: in.Id}, status.New(codes.OK, "").Err()
}

func (server *server) GetBook(ctx context.Context, in *pb.BookID) (*pb.Book, error) {
	value, exists := server.bookMap[in.Value]
	if exists {
		return value, status.New(codes.OK, "").Err()
	}
	return nil, status.Errorf(codes.NotFound, "Book does not exist.", in.Value)
}

func (server *server) DeleteBook(ctx context.Context, in *pb.BookID) (*pb.Result, error) {
	_, exists := server.bookMap[in.Value]
	if exists {
		//return value, status.New(codes.OK, "").Err() borrar
		delete(server.bookMap, in.Value)
	} else {
		return nil, status.Errorf(codes.NotFound, "Book does not exist.", in.Value)
	}
	//return nil, status.Errorf(codes.NotFound, "Book does not exist.", in.Value)
	return &pb.Result{Status: "Correct", Mensaje: "Eliminado correctamente"}, status.New(codes.OK, "").Err()
}

func (s *server) UpdateBook(ctx context.Context, in *pb.Book) (*pb.Book, error) {
	value, exists := s.bookMap[in.Id]
	if exists {
		log.Printf("===============================")
		log.Printf("===============================")
		return value, status.New(codes.OK, "").Err()
	}
	return nil, status.Errorf(codes.NotFound, "Book does not exist.", "")
}
