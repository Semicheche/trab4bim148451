DROP DATABASE IF EXISTS db_trab4bim;
CREATE DATABASE db_trab4bim;
USE db_trab4bim;
CREATE TABLE tbl_usuario(

  id_usuario          INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT 'CÓDIGO DO USUÁRIO',
  login_usuario     VARCHAR(30) NOT NULL COMMENT 'LOGIN DO USUÁRIO PARA ACESSO AO SISTEMA',
  senha_usuario   VARCHAR(30) NOT NULL COMMENT 'SENHA DO USUÁRIO PARA ACESSO AO SISTEMA'

);

CREATE TABLE tbl_pessoa(

    id_pessoa               INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT 'CÓDIGO DA PESSOA',
    nome_pessoa          VARCHAR(70)  NOT NULL COMMENT 'NOME DA PESSOA',
    sexo_pessoa           CHAR(1)      NOT NULL COMMENT 'INFORMAR M OU F',
    cadastro_pessoa     DATETIME     NOT NULL COMMENT 'DATA DE CADASTRO DO REGISTRO',
    email_pessoa          VARCHAR(80)  NOT NULL COMMENT 'EMAIL DA PESSOA',
    endereco_pessoa     VARCHAR(200) NOT NULL COMMENT 'DESCRIÇÃO DO ENDEREÇO',
    origem_cad_pessoa  CHAR(1)      NOT NULL COMMENT 'ORIGEM DO CADASTRO (I) = INPUT OU (X) = XML',
    id_usuario_cadastro  INT      NOT NULL COMMENT  'USUÁRIO LOGADO QUE CADASTROU A PESSOA'

);

ALTER TABLE tbl_pessoa ADD FOREIGN KEY (id_usuario_cadastro) REFERENCES tbl_usuario(id_usuario);

INSERT INTO tbl_usuario (login_usuario,senha_usuario) VALUES('admin','123456');
