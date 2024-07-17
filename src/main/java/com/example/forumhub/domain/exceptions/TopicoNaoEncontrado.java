package com.example.forumhub.domain.exceptions;

public class TopicoNaoEncontrado extends RuntimeException{
    public TopicoNaoEncontrado(String message) {
        super(message);
    }
}
