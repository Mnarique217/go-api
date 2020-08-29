package swagger

type Message struct {
	Status  string `json:"status,omitempty"`
	Message string `json:"message,omitempty"`
}

func isValidUpdate(current string, new string) string {
	if new != "" {
		return new
	}
	return current
}
