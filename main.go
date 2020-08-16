package main

import (
	"log"
	"net"
	"os"

	pb "github.com/Mnarique217/go-api"
	"google.golang.org/grpc"
)

func main() {
	port := os.Getenv("PORT")
	lis, err := net.Listen("tcp", ":8080")
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}

	s := grpc.NewServer()
	pb.RegisterBookInfoServer(s, &server{})

	log.Printf("Starting gRPC listener on port " + port)

	if err := s.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
