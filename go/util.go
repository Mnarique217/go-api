package swagger

func isValidUpdate(current string, new string) string {
	if new != "" {
		return new
	}
	return current
}
