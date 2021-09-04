CREATE SEQUENCE pessoa_seq;

CREATE TABLE pessoa (
	codigo BIGINT PRIMARY KEY DEFAULT NEXTVAL ('pessoa_seq'),
	nome VARCHAR(50) NOT NULL,
	logradouro VARCHAR(30),
	numero VARCHAR(30),
	complemento VARCHAR(30),
	bairro VARCHAR(30),
	cep VARCHAR(30),
	codigo_cidade BIGINT NOT NULL,
	ativo BOOLEAN NOT NULL,
	FOREIGN KEY (codigo_cidade) REFERENCES cidade(codigo)
	
);

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, codigo_cidade, ativo) values ('João Silva', 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-121', 2, true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, codigo_cidade, ativo) values ('Maria Rita', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-121', 4, true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, codigo_cidade, ativo) values ('Pedro Santos', 'Rua da Bateria', '23', null, 'Morumbi', '54.212-121', 5, true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, codigo_cidade, ativo) values ('Ricardo Pereira', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 1, true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, codigo_cidade, ativo) values ('Josué Mariano', 'Av Rio Branco', '321', null, 'Jardins', '56.400-121', 2, true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, codigo_cidade, ativo) values ('Pedro Barbosa', 'Av Brasil', '100', null, 'Tubalina', '77.400-121', 3, true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, codigo_cidade, ativo) values ('Henrique Medeiros', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-121', 8, true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, codigo_cidade, ativo) values ('Carlos Santana', 'Rua da Manga', '433', null, 'Centro', '31.400-121', 9, true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, codigo_cidade, ativo) values ('Leonardo Oliveira', 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 7, true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, codigo_cidade, ativo) values ('Isabela Martins', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-121', 6, true);