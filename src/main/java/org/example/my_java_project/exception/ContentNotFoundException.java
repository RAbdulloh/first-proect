package org.example.my_java_project.exception;

public class ContentNotFoundException extends RuntimeException{
    public ContentNotFoundException(String format) {
        super(format);
    }
}
