package net.d4y2k.profileservice.utils.files;

public class FileNameIsEmptyException extends RuntimeException{
    public FileNameIsEmptyException() {
        super("File name could not be empty!");
    }
}
