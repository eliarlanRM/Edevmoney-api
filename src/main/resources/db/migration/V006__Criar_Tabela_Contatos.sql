CREATE SEQUENCE contatos_seq;

CREATE TABLE contato (
    codigo BIGINT PRIMARY KEY DEFAULT NEXTVAL ('contatos_seq'),
    codigo_pessoa BIGINT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,	
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
);

INSERT INTO contato (codigo, codigo_pessoa, nome, email, telefone) values (1, 1, 'Eliarlan Rodrigues', 'eliarlan1990@gmail.com', '61 98224-2304');