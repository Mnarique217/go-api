package main

import (
	"context"

	"github.com/go-kit/kit/log"
)

type Publisher struct {
	PublisherId string `json:"publisherId,omitempty"`

	BookIds []string `json:"bookIds,omitempty"`

	Name string `json:"name,omitempty"`

	Country string `json:"country,omitempty"`

	Founded string `json:"founded,omitempty"`

	Genere string `json:"genere,omitempty"`
}
type publisherservice struct {
	logger log.Logger
}

// Service describes the Publisher service.
type PublisherService interface {
	CreatePublisher(ctx context.Context, publisher Publisher) (string, error)
	GetPublisherById(ctx context.Context, id string) (interface{}, error)
	UpdatePublisher(ctx context.Context, publisher Publisher) (string, error)
	DeletePublisher(ctx context.Context, id string) (string, error)
}

var publishers = []Publisher{
	Publisher{PublisherId: "Publisher1", Name: "Publisher1", Country: "Publisher1", Founded: "Publisher1", Genere: "Publisher1", BookIds: []string{"Book1"}},
	Publisher{PublisherId: "Publisher2", Name: "Publisher2", Country: "Publisher2", Founded: "Publisher2", Genere: "Publisher2", BookIds: []string{"Book1"}},
}

func findPublisher(x string) int {
	for i, publisher := range publishers {
		if x == publisher.PublisherId {
			return i
		}
	}
	return -1
}

func NewPublisherService(logger log.Logger) PublisherService {
	return &publisherservice{
		logger: logger,
	}
}

func (s publisherservice) CreatePublisher(ctx context.Context, publisher Publisher) (string, error) {
	var msg = "success"
	publishers = append(publishers, publisher)
	return msg, nil
}

func (s publisherservice) GetPublisherById(ctx context.Context, id string) (interface{}, error) {
	var err error
	var publisher interface{}
	var empty interface{}
	i := findPublisher(id)
	if i == -1 {
		return empty, err
	}
	publisher = publishers[i]
	return publisher, nil
}
func (s publisherservice) DeletePublisher(ctx context.Context, id string) (string, error) {
	var err error
	msg := ""
	i := findPublisher(id)
	if i == -1 {
		return "", err
	}
	copy(publishers[i:], publishers[i+1:])
	publishers[len(publishers)-1] = Publisher{}
	publishers = publishers[:len(publishers)-1]
	return msg, nil
}
func (s publisherservice) UpdatePublisher(ctx context.Context, publisher Publisher) (string, error) {
	var empty = ""
	var err error
	var msg = "success"
	i := findPublisher(publisher.PublisherId)
	if i == -1 {
		return empty, err
	}
	publishers[i] = publisher
	return msg, nil
}
