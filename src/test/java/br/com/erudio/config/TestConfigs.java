package br.com.erudio.config;

public interface TestConfigs {

    int SERVER_PORT = 8888; // apontando os testes de integração para o container GTI Actions
    //int SERVER_PORT = 80; // apontando os testes de integração para o container local
    //  int SERVER_PORT = 8080; // apontando os testes de integração para o container local
    String HEADER_PARAM_AUTHORIZATION = "Authorization";
    String HEADER_PARAM_ORIGIN = "Origin";

    String ORIGIN_ERUDIO = "https://www.erudio.com.br";
    String ORIGIN_SEMERU = "https://www.semeru.com.br";
    String ORIGIN_LOCAL = "http://localhost:8080";
}
