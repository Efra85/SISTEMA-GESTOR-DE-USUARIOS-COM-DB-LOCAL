package br.com;

// RuntimeException permite que você lance a exceção sem precisar
// colocar "throws" em todos os métodos.
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public static class InvalidUserDataException extends RuntimeException {
        public InvalidUserDataException(String message) {
            super(message);
        }
    }
}