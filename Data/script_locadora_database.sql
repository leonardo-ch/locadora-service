--FILME
DROP table filme;
CREATE TABLE filme (
  id int(11) NOT NULL AUTO_INCREMENT,
  titulo varchar(255) NOT NULL,
  diretor varchar(255) NOT NULL,
  qnt_filmes int(10) default 50 not null,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into filme(titulo,diretor)
values("The Shawshank Redemption","Frank Darabont");

insert into filme(titulo,diretor)
values("The Godfather","Francis Ford Coppola");

--USUARIO
CREATE TABLE locadora.usuario (
  id int(11) NOT NULL AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  senha varchar(255) not null,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;