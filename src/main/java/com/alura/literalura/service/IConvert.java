package com.alura.literalura.service;

public interface IConvert {
    <T> T getData(String json, Class<T> classe);
}
